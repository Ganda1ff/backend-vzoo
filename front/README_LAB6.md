# Лабораторная работа №6 — V-ZOO SPA на React

Проект выполнен на основе лабораторной работы №5 и примера из архива с лабораторной №6. Статические страницы V-ZOO перенесены в единую React SPA.

## Что реализовано

- SPA-маршрутизация без перезагрузки страницы: `/`, `/animals`, `/animals/edit`, `/animals/edit/:id`, `/tickets`, `/about`, `/contacts`.
- Компонентная структура: layout, страницы, карточка животного, форма животного, общая ссылка маршрутизации.
- CRUD для карточек животных через REST-запросы к `json-server`: создание, редактирование, удаление, отображение.
- Оформление покупки билета с сохранением заказа в `ticketOrders`.
- Оформление подписки на новости с сохранением в `subscriptions`.
- Бизнес-логика вынесена в кастомные хуки: `useAnimals`, `useAnimalForm`, `useTicketOrder`, `useSubscription`, `useRouter`.
- В хуках, взаимодействующих с сервером, используются `AbortController`, `status`, `error`; бинарные флаги объединены в статус `STATUS`.
- Серверная часть работает через пакет `json-server`, все операции выполняются по HTTP в стиле REST.

## Запуск

```bash
npm install
npm run start
```

Vite откроет приложение на `http://localhost:3000/`, json-server работает на `http://localhost:5174/`.

## Основные файлы

```text
src/App.tsx
src/app/layout/MainLayout.tsx
src/app/pages/*.tsx
src/components/animals/*.tsx
src/hooks/*.ts
src/api/*.ts
src/types/entities.ts
server/data.json
```
