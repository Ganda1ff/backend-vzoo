import { STATUS } from '../../types/entities'
import { useAnimals } from '../../hooks/useAnimals'
import { AnimalCard } from '../../components/animals/AnimalCard'

type Props = { navigate: (path: string) => void }
export function AnimalsPage({ navigate }: Props) {
  const { animals, status, error, isLoading, removeAnimal } = useAnimals()
  const disabled = status === STATUS.DELETING
  return (
    <section className="container my-5">
      <div className="d-flex justify-content-between align-items-center mb-5 flex-wrap gap-3"><h1 className="mb-0"><i className="bi bi-tree text-success"></i> Наши животные</h1><button className="btn btn-success" onClick={() => navigate('/animals/edit')}><i className="bi bi-plus-circle"></i> Добавить животное</button></div>
      {error && <div className="alert alert-danger">{error}</div>}
      {isLoading && <div className="alert alert-info">Загрузка карточек животных...</div>}
      {!isLoading && animals.length === 0 && <div className="alert alert-info">Животные не найдены. Добавьте первую карточку.</div>}
      <div className="row g-4">{animals.map((animal) => <div className="col-lg-4 col-md-6" key={animal.id}><AnimalCard animal={animal} disabled={disabled} onEdit={(id) => navigate(`/animals/edit/${id}`)} onDelete={(id) => void removeAnimal(id)} /></div>)}</div>
    </section>
  )
}
