import { useCallback, useEffect, useState } from 'react'
import { animalsApi, dietsApi, habitatsApi } from '../api/resources'
import { STATUS, type Animal, type Diet, type Habitat, type Status } from '../types/entities'

const emptyAnimal: Animal = { name: '', description: '', image: '', habitatId: '', dietId: '' }

export function useAnimalForm(animalId?: string) {
  const [animal, setAnimal] = useState<Animal>(emptyAnimal)
  const [habitats, setHabitats] = useState<Habitat[]>([])
  const [diets, setDiets] = useState<Diet[]>([])
  const [status, setStatus] = useState<Status>(STATUS.LOADING)
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    const controller = new AbortController()

    async function loadFormData() {
      try {
        setStatus(STATUS.LOADING)
        setError(null)
        const [habitatsData, dietsData, animalData] = await Promise.all([
          habitatsApi.getAll(controller.signal),
          dietsApi.getAll(controller.signal),
          animalId ? animalsApi.get(animalId, controller.signal) : Promise.resolve(emptyAnimal),
        ])
        setHabitats(habitatsData)
        setDiets(dietsData)
        setAnimal(animalData)
        setStatus(STATUS.SUCCESS)
      } catch (error_) {
        const err = error_ as Error
        if (err.name !== 'AbortError') {
          setError('Не удалось загрузить данные формы')
          setStatus(STATUS.ERROR)
        }
      }
    }

    void loadFormData()
    return () => controller.abort()
  }, [animalId])

  const saveAnimal = useCallback(async (animalToSave: Animal) => {
    const controller = new AbortController()
    try {
      setStatus(STATUS.SAVING)
      setError(null)
      const payload = animalId ? { ...animalToSave, id: animalId } : { ...animalToSave, id: undefined }
      await animalsApi.save(payload, controller.signal)
      setStatus(STATUS.SUCCESS)
    } catch (error_) {
      const err = error_ as Error
      if (err.name !== 'AbortError') {
        setError('Не удалось сохранить карточку животного')
        setStatus(STATUS.ERROR)
      }
      throw error_
    }
  }, [animalId])

  return { animal, habitats, diets, status, error, saveAnimal }
}
