import { useState } from 'react'
import { STATUS } from '../../types/entities'
import { useSubscription } from '../../hooks/useSubscription'

export function ContactsPage() {
  const { status, error, subscribe } = useSubscription()
  const [form, setForm] = useState({ name: '', email: '' })
  const [formError, setFormError] = useState('')
  function change(event: React.ChangeEvent<HTMLInputElement>) {
    const { name, value } = event.target
    setForm((prev) => ({ ...prev, [name]: value }))
  }
  async function submit(event: React.FormEvent) {
    event.preventDefault()
    if (!form.name.trim() || !form.email.trim()) return setFormError('Введите имя и email')
    setFormError('')
    await subscribe(form)
  }
  return (
    <section className="container my-5">
      <h1 className="text-center mb-5"><i className="bi bi-envelope text-success"></i> Контакты</h1>
      <div className="row g-4"><div className="col-md-6"><div className="card shadow h-100"><div className="card-body p-4"><h4>Связь с нами</h4><p><i className="bi bi-telephone text-success"></i> +7 (495) 123-45-67</p><p><i className="bi bi-envelope text-success"></i> info@vzoo.ru</p><p><i className="bi bi-geo-alt text-success"></i> г. Москва, ул. Зоологическая, 12</p></div></div></div><div className="col-md-6"><div className="card shadow h-100"><div className="card-body p-4"><h4>Подписка на новости</h4>{error && <div className="alert alert-danger">{error}</div>}{formError && <div className="alert alert-danger">{formError}</div>}{status === STATUS.SUCCESS && <div className="alert alert-success">Подписка оформлена.</div>}<form onSubmit={submit}><div className="mb-3"><label className="form-label">Имя</label><input className="form-control" name="name" value={form.name} onChange={change} /></div><div className="mb-3"><label className="form-label">Email</label><input className="form-control" type="email" name="email" value={form.email} onChange={change} /></div><button className="btn btn-success" disabled={status === STATUS.SAVING}>{status === STATUS.SAVING ? 'Отправка...' : 'Подписаться'}</button></form></div></div></div></div>
    </section>
  )
}
