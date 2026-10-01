import { BaseApi } from './base-api'
import type { Animal, Diet, Habitat, Subscription, TicketOrder, TicketType } from '../types/entities'

export const animalsApi = new BaseApi<Animal>('animals')
export const habitatsApi = new BaseApi<Habitat>('habitats')
export const dietsApi = new BaseApi<Diet>('diets')
export const ticketTypesApi = new BaseApi<TicketType>('ticketTypes')
export const ticketOrdersApi = new BaseApi<TicketOrder>('ticketOrders')
export const subscriptionsApi = new BaseApi<Subscription>('subscriptions')
