import { MainLayout } from './app/layout/MainLayout'
import { AboutPage } from './app/pages/AboutPage'
import { AnimalEditPage } from './app/pages/AnimalEditPage'
import { AnimalsPage } from './app/pages/AnimalsPage'
import { ContactsPage } from './app/pages/ContactsPage'
import { MainPage } from './app/pages/MainPage'
import { NotFoundPage } from './app/pages/NotFoundPage'
import { TicketsPage } from './app/pages/TicketsPage'
import { useRouter } from './hooks/useRouter'

function renderPage(path: string, navigate: (path: string) => void) {
  if (path === '/') return <MainPage navigate={navigate} />
  if (path === '/animals') return <AnimalsPage navigate={navigate} />
  if (path === '/animals/edit') return <AnimalEditPage navigate={navigate} />
  const editMatch = path.match(/^\/animals\/edit\/([^/]+)$/)
  if (editMatch) return <AnimalEditPage animalId={editMatch[1]} navigate={navigate} />
  if (path === '/tickets') return <TicketsPage />
  if (path === '/about') return <AboutPage />
  if (path === '/contacts') return <ContactsPage />
  return <NotFoundPage navigate={navigate} />
}

export default function App() {
  const { path, navigate } = useRouter()
  return <MainLayout path={path} navigate={navigate}>{renderPage(path, navigate)}</MainLayout>
}
