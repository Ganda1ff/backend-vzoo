import { useCallback, useEffect, useMemo, useState } from 'react'
import { ticketOrdersApi, ticketTypesApi } from '../api/resources'
import { STATUS, type Status, type TicketOrder, type TicketType } from '../types/entities'

export function useTicketOrder() {
  const [ticketTypes, setTicketTypes] = useState<TicketType[]>([])
  const [status, setStatus] = useState<Status>(STATUS.LOADING)
  const [error, setError] = useState<string | null>(null)
  const [lastOrder, setLastOrder] = useState<TicketOrder | null>(null)

  useEffect(() => {
    const controller = new AbortController()
    async function loadTicketTypes() {
      try {
        setStatus(STATUS.LOADING)
        setError(null)
        setTicketTypes(await ticketTypesApi.getAll(controller.signal))
        setStatus(STATUS.SUCCESS)
      } catch (error_) {
        const err = error_ as Error
        if (err.name !== 'AbortError') {
          setError('Не удалось загрузить типы билетов')
          setStatus(STATUS.ERROR)
        }
      }
    }
    void loadTicketTypes()
    return () => controller.abort()
  }, [])

  const createOrder = useCallback(async (draft: Omit<TicketOrder, 'id' | 'total' | 'createdAt'>) => {
    const controller = new AbortController()
    const ticketType = ticketTypes.find((item) => item.id === draft.ticketTypeId)
    if (!ticketType) throw new Error('Выберите тип билета')
    const order: TicketOrder = { ...draft, total: ticketType.price * draft.quantity, createdAt: new Date().toISOString() }
    try {
      setStatus(STATUS.SAVING)
      setError(null)
      const saved = await ticketOrdersApi.save(order, controller.signal)
      setLastOrder(saved)
      setStatus(STATUS.SUCCESS)
    } catch (error_) {
      const err = error_ as Error
      if (err.name !== 'AbortError') {
        setError('Не удалось оформить покупку билета')
        setStatus(STATUS.ERROR)
      }
      throw error_
    }
  }, [ticketTypes])

  return useMemo(() => ({ ticketTypes, status, error, lastOrder, createOrder }), [ticketTypes, status, error, lastOrder, createOrder])
}
