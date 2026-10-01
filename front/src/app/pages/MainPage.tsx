import { Link } from '../../components/common/Link'

type Props = { navigate: (path: string) => void }
export function MainPage({ navigate }: Props) {
  return (
    <>
      <section className="hero position-relative overflow-hidden">
        <img src="/images/index.jpg" className="w-100 h-100" alt="Зоопарк" />
        <div className="position-absolute top-0 start-0 w-100 h-100 d-flex align-items-center">
          <div className="container"><h1 className="display-1 text-white fw-bold text-shadow">ДОБРО<br />ПОЖАЛОВАТЬ!</h1><div className="bg-dark bg-opacity-75 p-4 rounded hero-text"><p className="lead text-white mb-0"><b>Онлайн-зоопарк V-ZOO</b> — загляните к животным в гости в любое время</p></div></div>
        </div>
      </section>
      <section className="container my-5"><div className="row g-4"><div className="col-md-6"><div className="card shadow h-100"><div className="card-body p-4"><h2 className="h4"><i className="bi bi-tree text-success"></i> Наши животные</h2><p className="text-secondary">Просматривайте, добавляйте, редактируйте и удаляйте карточки животных.</p><Link to="/animals" navigate={navigate} className="btn btn-success"><i className="bi bi-paw"></i> Перейти к животным</Link></div></div></div><div className="col-md-6"><div className="card shadow h-100"><div className="card-body p-4"><h2 className="h4"><i className="bi bi-ticket text-success"></i> Билеты</h2><p className="text-secondary">Оформите покупку билета через форму, которая сохраняет заказ на сервер.</p><Link to="/tickets" navigate={navigate} className="btn btn-outline-success">Купить билет</Link></div></div></div></div></section>
    </>
  )
}
