 -- INSERT Users
INSERT INTO users (name, email) VALUES
    ('Alice Johnson',  'alice@email.com'),
    ('Bob Smith',      'bob@email.com'),
    ('Carol White',    'carol@email.com'),
    ('David Brown',    'david@email.com');

-- INSERT Products
INSERT INTO products (name, price, stock) VALUES
    ('Wireless Mouse',   29.99, 100),
    ('Mechanical Keyboard', 79.99, 50),
    ('USB-C Hub',        39.99, 75),
    ('Monitor Stand',    49.99, 30),
    ('Webcam HD',        59.99, 60);

-- INSERT Orders
INSERT INTO orders (user_id, product_id, quantity) VALUES
    (1, 1, 2),
    (1, 3, 1),   
    (2, 2, 1),   
    (3, 5, 1),   
    (4, 4, 3),   
    (2, 1, 1);