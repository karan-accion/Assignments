-- Drop tables if they already exist (clean slate)
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;

-- USERS TABLE
CREATE TABLE users (
    user_id     INT           PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(100)  NOT NULL,
    email       VARCHAR(150)  NOT NULL UNIQUE,
    created_at  DATETIME      DEFAULT CURRENT_TIMESTAMP
);

-- PRODUCTS TABLE
CREATE TABLE products (
    product_id   INT           PRIMARY KEY AUTO_INCREMENT,
    name         VARCHAR(150)  NOT NULL,
    price        DECIMAL(10,2) NOT NULL,
    stock        INT           DEFAULT 0,
    created_at   DATETIME      DEFAULT CURRENT_TIMESTAMP
);

-- ORDERS TABLE
CREATE TABLE orders (
    order_id    INT       PRIMARY KEY AUTO_INCREMENT,
    user_id     INT       NOT NULL,
    product_id  INT       NOT NULL,
    quantity    INT       NOT NULL DEFAULT 1,
    order_date  DATETIME  DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_user    FOREIGN KEY (user_id)    REFERENCES users(user_id)    ON DELETE CASCADE,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE
);