import type { PropsWithChildren } from 'react'
import { Footer } from './Footer'
import { Header } from './Header'

type Props = PropsWithChildren<{ path: string; navigate: (path: string) => void }>

export function MainLayout({ path, navigate, children }: Props) {
  return (
    <div className="d-flex flex-column min-vh-100">
      <Header path={path} navigate={navigate} />
      <main className="flex-grow-1">{children}</main>
      <Footer />
    </div>
  )
}
