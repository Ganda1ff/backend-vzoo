import { useCallback, useState } from 'react'
import { subscriptionsApi } from '../api/resources'
import { STATUS, type Status, type Subscription } from '../types/entities'

export function useSubscription() {
  const [status, setStatus] = useState<Status>(STATUS.IDLE)
  const [error, setError] = useState<string | null>(null)

  const subscribe = useCallback(async (draft: Omit<Subscription, 'id' | 'createdAt'>) => {
    const controller = new AbortController()
    try {
      setStatus(STATUS.SAVING)
      setError(null)
      await subscriptionsApi.save({ ...draft, createdAt: new Date().toISOString() }, controller.signal)
      setStatus(STATUS.SUCCESS)
    } catch (error_) {
      const err = error_ as Error
      if (err.name !== 'AbortError') {
        setError('Не удалось оформить подписку')
        setStatus(STATUS.ERROR)
      }
    }
  }, [])

  return { status, error, subscribe }
}
