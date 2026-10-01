export function AboutPage() {
  return (
    <section className="container my-5">
      <div className="row g-4 mb-5"><div className="col-md-4"><img src="/images/about1.jpg" className="img-fluid rounded shadow about-img" /></div><div className="col-md-4"><img src="/images/about2.jpg" className="img-fluid rounded shadow about-img" /></div><div className="col-md-4"><img src="/images/about3.jpg" className="img-fluid rounded shadow about-img" /></div></div>
      <div className="card bg-light border-0"><div className="card-body p-5"><h1 className="text-center mb-4"><i className="bi bi-building text-success"></i> О зоопарке</h1><h4><i className="bi bi-clock-history text-success"></i> Как появился зоопарк</h4><p>Всё началось больше ста лет назад, когда группа учёных и меценатов решила создать место, где горожане смогут увидеть диких животных.</p><h4 className="mt-4"><i className="bi bi-calendar-heart text-success"></i> Что происходит сейчас</h4><p>Сегодня наш зоопарк участвует в программах по сохранению редких видов и развивает образовательные онлайн-сервисы.</p><h4 className="mt-4"><i className="bi bi-laptop text-success"></i> Онлайн-зоопарк</h4><p>Онлайн-зоопарк — это возможность заглянуть к животным в любое время дня и ночи.</p><div className="alert alert-success mt-4"><i className="bi bi-star-fill"></i> Онлайн-зоопарк — это не замена прогулке. Заходите в гости. Мы всегда открыты.</div></div></div>
    </section>
  )
}
