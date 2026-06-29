-- Add a new user
INSERT INTO users (name, email)
VALUES ('Eve Martinez', 'eve@email.com');

-- Add a new product
INSERT INTO products (name, price, stock)
VALUES ('Desk Lamp', 24.99, 80);

-- Add a new order
INSERT INTO orders (user_id, product_id, quantity)
VALUES (5, 6, 1);


-- 1. Get all users
SELECT * FROM users;

-- 2. Get all products with stock > 50
SELECT name, price, stock
FROM products
WHERE stock > 50
ORDER BY price ASC;

-- 3. JOIN - Get orders with user names and product names
SELECT
    o.order_id,
    u.name        AS customer,
    p.name        AS product,
    o.quantity,
    (p.price * o.quantity) AS total_price,
    o.order_date
FROM orders o
JOIN users    u ON o.user_id    = u.user_id
JOIN products p ON o.product_id = p.product_id
ORDER BY o.order_date DESC;

-- 4. Aggregate - Total spent per user
SELECT
    u.name,
    COUNT(o.order_id)              AS total_orders,
    SUM(p.price * o.quantity)      AS total_spent
FROM users u
LEFT JOIN orders   o ON u.user_id    = o.user_id
LEFT JOIN products p ON o.product_id = p.product_id
GROUP BY u.user_id, u.name
ORDER BY total_spent DESC;

-- 5. Find orders for a specific user (Alice)
SELECT
    p.name   AS product,
    o.quantity,
    p.price
FROM orders o
JOIN products p ON o.product_id = p.product_id
WHERE o.user_id = 1;

-- Update a product's price
UPDATE products
SET price = 34.99
WHERE product_id = 1;

-- Update stock after an order is fulfilled
UPDATE products
SET stock = stock - 2
WHERE product_id = 1;

-- Update a user's email
UPDATE users
SET email = 'alice.j@email.com'
WHERE user_id = 1;

-- Delete a specific order
DELETE FROM orders
WHERE order_id = 6;

-- Delete a user 
DELETE FROM users
WHERE user_id = 5; 

-- Confirm deletions
SELECT * FROM users;
SELECT * FROM orders;