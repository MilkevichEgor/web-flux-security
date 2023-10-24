CREATE TABLE users (
    id         SERIAL PRIMARY KEY,
    username   VARCHAR(64)   NOT NULL UNIQUE,
    password   VARCHAR(2048) NOT NULL,
    role       VARCHAR(32)   NOT NULL,
    first_name VARCHAR(64)   NOT NULL,
    last_name  VARCHAR(64)   NOT NULL,
    enabled    BOOLEAN       NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE books (
    id         SERIAL PRIMARY KEY,
    title      VARCHAR(64)   NOT NULL,
    author     VARCHAR(64)   NOT NULL,
    description VARCHAR(2048)
);

CREATE TABLE book_users_id (
    book_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    PRIMARY KEY (book_id, user_id),
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE comments (
    id         SERIAL PRIMARY KEY,
    text       VARCHAR(2048) NOT NULL
);