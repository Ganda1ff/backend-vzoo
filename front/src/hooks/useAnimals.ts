import { useCallback, useEffect, useState } from 'react'
import { animalsApi, dietsApi, habitatsApi } from '../api/resources'
import { STATUS, type Animal, type Diet, type Habitat, type Status } from '../types/entities'

function attachRelations(animals: Animal[], habitats: Habitat[], diets: Diet[]) {
  return animals.map((animal) => ({
    ...animal,
    habitat: habitats.find((item) => item.id === animal.habitatId),
    diet: diets.find((item) => item.id === animal.dietId),
  }))
}

export function useAnimals() {
  const [animals, setAnimals] = useState<Animal[]>([])
  const [status, setStatus] = useState<Status>(STATUS.LOADING)
  const [error, setError] = useState<string | null>(null)

  const loadAnimals = useCallback(async (signal?: AbortSignal) => {
    try {
      setStatus(STATUS.LOADING)
      setError(null)
      const [animalsData, habitatsData, dietsData] = await Promise.all([
        animalsApi.getAll(signal),
        habitatsApi.getAll(signal),
        dietsApi.getAll(signal),
      ])
      setAnimals(attachRelations(animalsData, habitatsData, dietsData))
      setStatus(STATUS.SUCCESS)
    } catch (error_) {
      const err = error_ as Error
      if (err.name !== 'AbortError') {
        setError('Не удалось загрузить животных. Проверьте запуск Spring Boot сервера.')
        setStatus(STATUS.ERROR)
      }
    }
  }, [])

  const removeAnimal = useCallback(async (id: string) => {
    const controller = new AbortController()
    try {
      setStatus(STATUS.DELETING)
      setError(null)
      await animalsApi.delete(id, controller.signal)
      setAnimals((prev) => prev.filter((animal) => String(animal.id) !== id))
      setStatus(STATUS.SUCCESS)
    } catch (error_) {
      const err = error_ as Error
      if (err.name !== 'AbortError') {
        setError('Не удалось удалить карточку животного')
        setStatus(STATUS.ERROR)
      }
    }
    return () => controller.abort()
  }, [])

  useEffect(() => {
    const controller = new AbortController()
    void loadAnimals(controller.signal)
    return () => controller.abort()
  }, [loadAnimals])

  return { animals, status, error, isLoading: status === STATUS.LOADING, removeAnimal, reload: loadAnimals }
}
