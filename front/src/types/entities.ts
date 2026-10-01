export type Status = 'idle' | 'loading' | 'saving' | 'deleting' | 'success' | 'error'

export const STATUS = {
  IDLE: 'idle',
  LOADING: 'loading',
  SAVING: 'saving',
  DELETING: 'deleting',
  SUCCESS: 'success',
  ERROR: 'error',
} as const

export interface BaseEntity { id?: string | number | null }
export interface Habitat extends BaseEntity { id: string; name: string }
export interface Diet extends BaseEntity { id: string; name: string }

export interface Animal extends BaseEntity {
  id?: string
  name: string
  description: string
  image: string
  habitatId: string
  dietId: string
  habitat?: Habitat
  diet?: Diet
}

export interface TicketType extends BaseEntity {
  id: string
  title: string
  price: number
  description: string
}

export interface TicketOrder extends BaseEntity {
  id?: string
  visitorName: string
  email: string
  ticketTypeId: string
  quantity: number
  visitDate: string
  total: number
  createdAt: string
}

export interface Subscription extends BaseEntity {
  id?: string
  name: string
  email: string
  createdAt: string
}
