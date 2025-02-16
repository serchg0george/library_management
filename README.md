# Library Management System

This is a Library Management System built using Spring Boot. It allows you to manage books, including adding, updating, deleting, and searching for books. Additionally, it includes basic authentication functionality for user registration and login.

---

## Technologies Used
- **Spring Boot**: Backend framework
- **Hibernate**: ORM for database operations
- **Lombok**: For reducing boilerplate code
- **JPA**: For database entity management
- **PostgreSQL Database**: In-memory database (can be replaced with any other database)
- **Spring Boot Security**: For endpoints protection

---

# API Endpoints

## Authentication

### Register a new user
**URL:** `/api/v1/auth/register`

**Method:** `POST`

**Request Body:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "password": "password123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### Authenticate a user
**URL:** `/api/v1/auth/authenticate`

**Method:** `POST`

**Request Body:**
```json
{
  "email": "john.doe@example.com",
  "password": "password123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

## Books

### Get all books
**URL:** `/api/v1/books`

**Method:** `GET`

**Response:**
```json
[
  {
    "id": 1,
    "title": "The Great Gatsby",
    "author": "F. Scott Fitzgerald",
    "publicationDate": "1925-04-10",
    "genre": "Fiction",
    "isbn": "9780743273565"
  }
]
```

### Get a book by ID
**URL:** `/api/v1/books/{id}`

**Method:** `GET`

**Response:**
```json
{
  "id": 1,
  "title": "The Great Gatsby",
  "author": "F. Scott Fitzgerald",
  "publicationDate": "1925-04-10",
  "genre": "Fiction",
  "isbn": "9780743273565"
}
```

### Add a new book
**URL:** `/api/v1/books`

**Method:** `POST`

**Request Body:**
```json
{
  "title": "1984",
  "author": "George Orwell",
  "publicationDate": "1949-06-08",
  "genre": "Dystopian",
  "isbn": "9780451524935"
}
```

**Response:**
```json
{
  "id": 2,
  "title": "1984",
  "author": "George Orwell",
  "publicationDate": "1949-06-08",
  "genre": "Dystopian",
  "isbn": "9780451524935"
}
```

### Update a book
**URL:** `/api/v1/books/{id}`

**Method:** `PUT`

**Request Body:**
```json
{
  "title": "1984",
  "author": "George Orwell",
  "publicationDate": "1949-06-08",
  "genre": "Dystopian",
  "isbn": "9780451524935"
}
```

**Response:**
```json
"Book successfully updated!"
```

### Delete a book
**URL:** `/api/v1/books/{id}`

**Method:** `DELETE`

**Response:**
```json
"Book successfully deleted!"
```

### Search for books
**URL:** `/api/v1/books/search`

**Method:** `POST`

**Request Body:**
```json
{
  "query": "Fitzgerald"
}
```

**Response:**
```json
[
  {
    "id": 1,
    "title": "The Great Gatsby",
    "author": "F. Scott Fitzgerald",
    "publicationDate": "1925-04-10",
    "genre": "Fiction",
    "isbn": "9780743273565"
  }
]
```

## Database Schema

### `t_books` Table

| Column Name        | Data Type | Constraints                      |
|--------------------|----------|---------------------------------|
| `id`              | BIGINT   | PRIMARY KEY, AUTO_INCREMENT    |
| `title`           | VARCHAR  | NOT NULL                        |
| `author`          | VARCHAR  | NOT NULL                        |
| `publication_date`| VARCHAR  |                                 |
| `genre`          | VARCHAR  |                                 |
| `isbn`           | VARCHAR  | NOT NULL, UNIQUE, LENGTH=13    |


### `t_users` Table

| Column Name  | Data Type | Constraints                      |
|-------------|----------|---------------------------------|
| `id`        | BIGINT   | PRIMARY KEY, AUTO_INCREMENT    |
| `first_name`| VARCHAR  | NOT NULL                        |
| `last_name` | VARCHAR  |                                 |
| `email`     | VARCHAR  | NOT NULL, UNIQUE               |
| `password`  | VARCHAR  | NOT NULL                        |
| `role`      | ENUM     | NOT NULL                        |


