# V-ZOO — лабораторная работа № 1 (REST API / контроллеры)

Если нужно просто запустить проект, сначала откройте файл `START_HERE.txt`.

Проект продолжает V-ZOO из прошлого семестра. Вместо `json-server` теперь используется Spring Boot REST API.

## Структура

- `src/main/java/...` — backend Spring Boot.
- `src/main/resources/application.yaml` — настройки backend.
- `front/` — React/Vite frontend из прошлого семестра.
- `build.gradle`, `gradlew`, `gradlew.bat`, `gradle/` — Gradle-проект backend.

REST API работает с префиксом `http://localhost:8080/api/1.0`.
Frontend работает на `http://localhost:5173`.
Swagger UI: `http://localhost:8080/swagger-ui/index.html`.

## Что нужно установить

1. JDK 21 (подойдёт также JDK 25, как в примере к лекции).
2. Node.js + npm.
3. VS Code или IntelliJ IDEA. Структура проекта рассчитана на открытие корневой папки `vzoo_lab1_spring` целиком.
4. При первом запуске Gradle нужен интернет: wrapper скачает Gradle и зависимости Spring Boot/Springdoc.

## Запуск в VS Code на Windows

Откройте в VS Code **корневую папку `vzoo_lab1_spring`**, а не только `front`.

### 1. Backend

Откройте первый терминал в корне проекта и выполните:

```powershell
.\gradlew.bat bootRun
```

Если PowerShell запускает shell-скрипты нормально, можно использовать Gradle Task из расширения VS Code, но команда выше — самый прямой вариант.

После строки о запуске приложения backend доступен по адресу:

- API: `http://localhost:8080/api/1.0/animals`
- Swagger: `http://localhost:8080/swagger-ui/index.html`

Backend-терминал **не закрывать**, пока работает сайт.

### 2. Frontend

Откройте второй терминал:

```powershell
cd front
npm install
npm run dev
```

Откройте `http://localhost:5173` (Vite обычно откроет браузер сам).

Итого одновременно должны работать два процесса:

```text
Spring Boot -> localhost:8080
React/Vite  -> localhost:5173
```

`json-server` больше запускать не нужно.

## Запуск на Linux/macOS

Backend из корня:

```bash
./gradlew bootRun
```

Frontend во втором терминале:

```bash
cd front
npm install
npm run dev
```

## REST API

Для каждой сущности из старого `server/data.json` создан DTO и REST-контроллер с CRUD:

- `/api/1.0/animals`
- `/api/1.0/habitats`
- `/api/1.0/diets`
- `/api/1.0/ticketTypes`
- `/api/1.0/ticketOrders`
- `/api/1.0/subscriptions`

Для каждого ресурса доступны:

```text
GET    /resource
GET    /resource/{id}
POST   /resource
PUT    /resource/{id}
DELETE /resource/{id}
```

Все данные этой лабораторной хранятся **в памяти** в коллекциях контроллеров. После перезапуска Spring Boot изменения, сделанные через сайт или Swagger, сбрасываются к предопределённым данным. Это соответствует заданию лабораторной № 1; база данных здесь ещё не используется.

В старом `data.json` массив `subscriptions` был пустым. В backend добавлена одна тестовая подписка, чтобы коллекция этого контроллера тоже была заполнена предопределёнными данными согласно формулировке задания.

## Проверка через Swagger

1. Запустить backend.
2. Открыть `http://localhost:8080/swagger-ui/index.html`.
3. Раскрыть, например, `animal-controller`.
4. Проверить `GET /api/1.0/animals`.
5. Через `POST` создать животное.
6. Скопировать выданный `id` и проверить `GET`, `PUT`, `DELETE` для этого id.
7. Аналогично проверить остальные контроллеры.

## Важное отличие от прошлого семестра

Раньше frontend обращался к `http://localhost:5174` (`json-server`). Теперь в `front/src/api/base-api.ts` указан Spring Boot API:

```ts
const API_URL = 'http://localhost:8080/api/1.0'
```

CORS разрешён для Vite frontend на `http://localhost:5173`.
