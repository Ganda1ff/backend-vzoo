import { Link } from '../../components/common/Link'

type Props = { navigate: (path: string) => void }
export function NotFoundPage({ navigate }: Props) {
  return <section className="container my-5 text-center"><h1>404</h1><p className="text-secondary">Страница не найдена.</p><Link to="/" navigate={navigate} className="btn btn-success">На главную</Link></section>
}
