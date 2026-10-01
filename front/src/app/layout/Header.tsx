import { Link } from '../../components/common/Link'

type Props = { path: string; navigate: (path: string) => void }
const isActive = (path: string, link: string) => path === link || (link !== '/' && path.startsWith(link))

export function Header({ path, navigate }: Props) {
  const navClass = (link: string) => `nav-link ${isActive(path, link) ? 'active' : ''}`
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-success">
      <div className="container">
        <Link to="/" navigate={navigate} className="navbar-brand d-flex align-items-center">
          <img src="/images/logo.PNG" alt="Logo" width="50" height="45" className="me-2" />
          <span className="fw-bold">Онлайн-зоопарк для всей семьи</span>
        </Link>
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav ms-auto">
            <li className="nav-item"><Link to="/" navigate={navigate} className={navClass('/')}><i className="bi bi-house-door"></i> Главная</Link></li>
            <li className="nav-item"><Link to="/animals" navigate={navigate} className={navClass('/animals')}><i className="bi bi-paw"></i> Животные</Link></li>
            <li className="nav-item"><Link to="/tickets" navigate={navigate} className={navClass('/tickets')}><i className="bi bi-ticket"></i> Билеты</Link></li>
            <li className="nav-item"><Link to="/about" navigate={navigate} className={navClass('/about')}><i className="bi bi-info-circle"></i> О нас</Link></li>
            <li className="nav-item"><Link to="/contacts" navigate={navigate} className={navClass('/contacts')}><i className="bi bi-envelope"></i> Контакты</Link></li>
          </ul>
        </div>
      </div>
    </nav>
  )
}
