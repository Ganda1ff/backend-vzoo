import { AnimalForm } from '../../components/animals/AnimalForm'
import { useAnimalForm } from '../../hooks/useAnimalForm'
import { STATUS } from '../../types/entities'

type Props = { animalId?: string; navigate: (path: string) => void }
export function AnimalEditPage({ animalId, navigate }: Props) {
  const { animal, diets, habitats, status, error, saveAnimal } = useAnimalForm(animalId)
  async function handleSave(nextAnimal: typeof animal) {
    await saveAnimal(nextAnimal)
    navigate('/animals')
  }
  return (
    <section className="container my-5">
      <button className="btn btn-link px-0 mb-3" onClick={() => navigate('/animals')}><i className="bi bi-arrow-left"></i> Назад к списку</button>
      {error && <div className="alert alert-danger">{error}</div>}
      {status === STATUS.LOADING ? <div className="alert alert-info">Загрузка формы...</div> : <AnimalForm initialAnimal={animal} diets={diets} habitats={habitats} status={status} onSave={handleSave} onCancel={() => navigate('/animals')} />}
    </section>
  )
}
