import { useCallback, useEffect, useMemo, useState } from 'react'

function normalizePath(path: string) {
  return path.length > 1 ? path.replace(/\/$/, '') : path
}

export function useRouter() {
  const [path, setPath] = useState(() => normalizePath(window.location.pathname))

  useEffect(() => {
    const onPopState = () => setPath(normalizePath(window.location.pathname))
    window.addEventListener('popstate', onPopState)
    return () => window.removeEventListener('popstate', onPopState)
  }, [])

  const navigate = useCallback((nextPath: string) => {
    const normalized = normalizePath(nextPath)
    window.history.pushState({}, '', normalized)
    setPath(normalized)
    window.scrollTo({ top: 0 })
  }, [])

  return useMemo(() => ({ path, navigate }), [path, navigate])
}
