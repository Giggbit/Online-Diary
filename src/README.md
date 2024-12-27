# Diary Service API

## Описание
Этот проект предоставляет сервис управления пользователями с использованием JPA и Hibernate. Реализованы CRUD-операции (создание, чтение, обновление и удаление пользователей).

---

## Стек технологий
- **Java** 22
- **Spring Boot**
    - `spring-boot-starter-data-jpa` — поддержка работы с базами данных через JPA (Java Persistence API).
    - `spring-boot-starter-security` — встроенная поддержка безопасности, включая аутентификацию и авторизацию.
    - `spring-boot-starter-thymeleaf` — интеграция с Thymeleaf для рендеринга HTML-шаблонов.
    - `spring-boot-starter-web` — поддержка разработки RESTful веб-сервисов.
    - `spring-boot-starter-test` — поддержка для тестирования Spring-приложений.
    - `spring-security-test` — утилиты для тестирования безопасности Spring-приложений.

- **MySQL** — реляционная база данных для хранения данных.
    - `mysql-connector-j` — драйвер для подключения к MySQL.

- **JWT (JSON Web Token)** — механизм аутентификации с использованием токенов.
    - `jjwt-api`, `jjwt-impl`, `jjwt-jackson` — библиотеки для работы с JWT.

- **Lombok** — библиотека для автоматической генерации кода (например, геттеров, сеттеров, конструкторов).
    - `lombok` — аннотации для упрощения написания Java-кода.

- **Thymeleaf Extras Spring Security** — интеграция Thymeleaf с Spring Security для использования аутентификации и авторизации в шаблонах.


---

## Структура проекта
```plaintext
src/
├── main/
│   ├── java/
│   │   └── com/diary.online_diary/
│   │       ├── config/
│   │       ├── controller/
│   │       ├── model/
│   │       ├── repository/
│   │       ├── service/
│   │       └── util/
│   └── resources/
│       ├── static/
│       ├── templates/
│       └── application.properties
└── test/
    └── java/
        └── com/diary.online_diary/
            └── OnlineDiaryApplicationTests