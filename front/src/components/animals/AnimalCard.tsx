import type { Animal } from '../../types/entities'

type Props = { animal: Animal; onEdit: (id: string) => void; onDelete: (id: string) => void; disabled?: boolean }

export function AnimalCard({ animal, onEdit, onDelete, disabled = false }: Props) {
  return (
    <div className="card shadow-sm h-100">
      <img src={animal.image || '/images/logo.PNG'} className="card-img-top animal-card-img" alt={animal.name} />
      <div className="card-body d-flex flex-column">
        <h5 className="card-title">{animal.name}</h5>
        <p className="card-text flex-grow-1">{animal.description}</p>
        <p className="card-text mb-1"><small className="text-muted"><strong>Среда:</strong> {animal.habitat?.name ?? 'Не указана'}</small></p>
        <p className="card-text"><small className="text-muted"><strong>Питание:</strong> {animal.diet?.name ?? 'Не указано'}</small></p>
        <div className="d-flex gap-2 mt-auto">
          <button className="btn btn-warning btn-sm" disabled={disabled} onClick={() => animal.id && onEdit(String(animal.id))}><i className="bi bi-pencil"></i> Редактировать</button>
          <button className="btn btn-danger btn-sm" disabled={disabled} onClick={() => animal.id && onDelete(String(animal.id))}><i className="bi bi-trash"></i> Удалить</button>
        </div>
      </div>
    </div>
  )
}
