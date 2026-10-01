export function Footer() {
  return (
    <footer className="bg-dark text-white py-4 mt-auto">
      <div className="container">
        <div className="row">
          <div className="col-md-3 col-sm-6 mb-3"><h5><i className="bi bi-telephone"></i> Телефон</h5><p className="text-secondary">+7 (495) 123-45-67</p></div>
          <div className="col-md-3 col-sm-6 mb-3"><h5><i className="bi bi-envelope"></i> Email</h5><p className="text-secondary">info@vzoo.ru</p></div>
          <div className="col-md-3 col-sm-6 mb-3"><h5><i className="bi bi-geo-alt"></i> Адрес</h5><p className="text-secondary">г. Москва, ул. Зоологическая, 12</p></div>
          <div className="col-md-3 col-sm-6 mb-3"><h5><i className="bi bi-clock"></i> Часы работы</h5><p className="text-secondary">Круглосуточно</p></div>
        </div>
        <div className="text-center mt-3">
          <a href="#" className="text-white me-3 social-icon"><i className="bi bi-telegram fs-5"></i></a>
          <a href="#" className="text-white me-3 social-icon"><i className="bi bi-vk fs-5"></i></a>
          <a href="#" className="text-white social-icon"><i className="bi bi-instagram fs-5"></i></a>
        </div>
      </div>
    </footer>
  )
}
