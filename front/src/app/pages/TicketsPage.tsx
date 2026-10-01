import { useState } from 'react'
import { STATUS } from '../../types/entities'
import { useTicketOrder } from '../../hooks/useTicketOrder'

export function TicketsPage() {
  const { ticketTypes, status, error, lastOrder, createOrder } = useTicketOrder()
  const [form, setForm] = useState({ visitorName: '', email: '', ticketTypeId: '', quantity: 1, visitDate: '' })
  const [formError, setFormError] = useState('')
  function change(event: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) {
    const { name, value } = event.target
    setForm((prev) => ({ ...prev, [name]: name === 'quantity' ? Number(value) : value }))
  }
  async function submit(event: React.FormEvent) {
    event.preventDefault()
    if (!form.visitorName.trim() || !form.email.trim() || !form.ticketTypeId || !form.visitDate) return setFormError('Заполните все поля заказа')
    setFormError('')
    await createOrder(form)
  }
  return (
    <section className="container my-5">
      <h1 className="text-center mb-5"><i className="bi bi-ticket-perforated text-success"></i> Стоимость билетов и покупка</h1>
      {error && <div className="alert alert-danger">{error}</div>}
      {formError && <div className="alert alert-danger">{formError}</div>}
      {lastOrder && <div className="alert alert-success">Заказ оформлен. Сумма: {lastOrder.total} ₽.</div>}
      <div className="row g-4">
        <div className="col-lg-6"><div className="card shadow h-100"><div className="card-body p-4"><h2 className="h4 mb-3">Тарифы</h2>{status === STATUS.LOADING ? <p>Загрузка тарифов...</p> : <table className="table table-hover"><thead className="table-success"><tr><th>Категория</th><th>Описание</th><th>Цена</th></tr></thead><tbody>{ticketTypes.map((ticket) => <tr key={ticket.id}><td>{ticket.title}</td><td>{ticket.description}</td><td>{ticket.price} ₽</td></tr>)}</tbody></table>}<div className="alert alert-info mt-3"><i className="bi bi-info-circle"></i> Дети до 7 лет — бесплатно</div></div></div></div>
        <div className="col-lg-6"><div className="card shadow h-100"><div className="card-body p-4"><h2 className="h4 mb-3">Оформление покупки</h2><form onSubmit={submit}><div className="mb-3"><label className="form-label">Имя посетителя</label><input className="form-control" name="visitorName" value={form.visitorName} onChange={change} /></div><div className="mb-3"><label className="form-label">Email</label><input className="form-control" name="email" type="email" value={form.email} onChange={change} /></div><div className="mb-3"><label className="form-label">Тип билета</label><select className="form-select" name="ticketTypeId" value={form.ticketTypeId} onChange={change}><option value="">Выберите билет</option>{ticketTypes.map((ticket) => <option key={ticket.id} value={ticket.id}>{ticket.title} — {ticket.price} ₽</option>)}</select></div><div className="row"><div className="col-md-6 mb-3"><label className="form-label">Количество</label><input className="form-control" type="number" min="1" name="quantity" value={form.quantity} onChange={change} /></div><div className="col-md-6 mb-3"><label className="form-label">Дата посещения</label><input className="form-control" type="date" name="visitDate" value={form.visitDate} onChange={change} /></div></div><button className="btn btn-success" disabled={status === STATUS.SAVING}>{status === STATUS.SAVING ? 'Оформление...' : 'Купить билет'}</button></form></div></div></div>
      </div>
    </section>
  )
}
