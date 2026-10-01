import { useEffect, useState } from 'react'
import { STATUS, type Animal, type Diet, type Habitat, type Status } from '../../types/entities'

type Props = { initialAnimal: Animal; diets: Diet[]; habitats: Habitat[]; status: Status; onSave: (animal: Animal) => Promise<void>; onCancel: () => void }

export function AnimalForm({ initialAnimal, diets, habitats, status, onSave, onCancel }: Props) {
  const [formData, setFormData] = useState<Animal>(initialAnimal)
  const [formError, setFormError] = useState('')

  useEffect(() => {
    setFormData(initialAnimal)
    setFormError('')
  }, [initialAnimal])

  function handleChange(event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement>) {
    const { name, value } = event.target
    setFormData((prev) => ({ ...prev, [name]: value }))
  }

  async function handleSubmit(event: React.FormEvent) {
    event.preventDefault()
    if (!formData.name.trim()) return setFormError('Введите название животного')
    if (!formData.description.trim()) return setFormError('Введите описание животного')
    if (!formData.image.trim()) return setFormError('Укажите путь к изображению')
    if (!formData.habitatId || !formData.dietId) return setFormError('Выберите среду обитания и тип питания')
    setFormError('')
    await onSave({ ...formData, name: formData.name.trim(), description: formData.description.trim(), image: formData.image.trim() })
  }

  const isSaving = status === STATUS.SAVING

  return (
    <div className="card shadow">
      <div className="card-header bg-success text-white"><h5 className="mb-0">{formData.id ? 'Редактирование животного' : 'Добавление животного'}</h5></div>
      <div className="card-body">
        {formError && <div className="alert alert-danger">{formError}</div>}
        <form onSubmit={handleSubmit} noValidate>
          <div className="mb-3"><label className="form-label">Название животного</label><input className="form-control" name="name" value={formData.name} onChange={handleChange} /></div>
          <div className="mb-3"><label className="form-label">Описание</label><textarea className="form-control" name="description" rows={4} value={formData.description} onChange={handleChange} /></div>
          <div className="mb-3"><label className="form-label">Изображение</label><input className="form-control" name="image" value={formData.image} onChange={handleChange} placeholder="/images/animal1.png" /></div>
          <div className="row">
            <div className="col-md-6 mb-3"><label className="form-label">Среда обитания</label><select className="form-select" name="habitatId" value={formData.habitatId} onChange={handleChange}><option value="">Выберите среду</option>{habitats.map((habitat) => <option key={habitat.id} value={habitat.id}>{habitat.name}</option>)}</select></div>
            <div className="col-md-6 mb-3"><label className="form-label">Тип питания</label><select className="form-select" name="dietId" value={formData.dietId} onChange={handleChange}><option value="">Выберите питание</option>{diets.map((diet) => <option key={diet.id} value={diet.id}>{diet.name}</option>)}</select></div>
          </div>
          <div className="d-flex gap-2"><button type="submit" className="btn btn-success" disabled={isSaving}>{isSaving ? 'Сохранение...' : 'Сохранить'}</button><button type="button" className="btn btn-secondary" onClick={onCancel}>Отмена</button></div>
        </form>
      </div>
    </div>
  )
}
