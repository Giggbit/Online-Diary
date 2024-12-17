# Diary Service API

## Описание
Этот проект предоставляет сервис управления пользователями с использованием JPA и Hibernate. Реализованы CRUD-операции (создание, чтение, обновление и удаление пользователей).

---

## Стек технологий
- **Java** 11/17
- **JPA** с **Hibernate**
- **JUnit 5** для тестирования
- **Mockito** для мокирования
- **H2 Database** (в памяти для тестов)

---

## Структура проекта
```plaintext
src/
├── main/
│   ├── java/
│   │   └── com/diary/
│   │       ├── controllers/
│   │       ├── models/
│   │       └── services/
│   └── resources/
│       └── META-INF/
│           └── application.properties
└── test/
    └── java/
        └── com/diary/services/
            ├── UserServiceTest.java
            └── DiaryServiceTest.java