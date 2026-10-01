import type { BaseEntity } from '../types/entities'

const API_URL = 'http://localhost:8080/api/1.0'

export class BaseApi<T extends BaseEntity> {
  protected baseUrl: string

  constructor(resourcePath: string) {
    this.baseUrl = `${API_URL}/${resourcePath}`
  }

  protected async request<R>(url: string, options: RequestInit = {}): Promise<R> {
    const response = await fetch(url, options)
    if (!response.ok) throw new Error(`Ошибка HTTP: ${response.status}`)
    if (response.status === 204) return undefined as R
    return (await response.json()) as R
  }

  async getAll(signal?: AbortSignal): Promise<T[]> {
    return this.request<T[]>(this.baseUrl, { signal })
  }

  async get(id: string | number, signal?: AbortSignal): Promise<T> {
    return this.request<T>(`${this.baseUrl}/${id}`, { signal })
  }

  async save(item: T, signal?: AbortSignal): Promise<T> {
    const isEdit = item.id !== undefined && item.id !== null && item.id !== ''
    const url = isEdit ? `${this.baseUrl}/${item.id}` : this.baseUrl
    const method = isEdit ? 'PUT' : 'POST'
    return this.request<T>(url, {
      method,
      signal,
      body: JSON.stringify(item),
      headers: { 'Content-Type': 'application/json' },
    })
  }

  async delete(id: string | number, signal?: AbortSignal): Promise<void> {
    await this.request<void>(`${this.baseUrl}/${id}`, { method: 'DELETE', signal })
  }
}
