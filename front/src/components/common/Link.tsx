import type { MouseEvent, PropsWithChildren } from 'react'

type Props = PropsWithChildren<{ to: string; className?: string; navigate: (path: string) => void }>

export function Link({ to, className = '', navigate, children }: Props) {
  function handleClick(event: MouseEvent<HTMLAnchorElement>) {
    event.preventDefault()
    navigate(to)
  }
  return <a href={to} className={className} onClick={handleClick}>{children}</a>
}
