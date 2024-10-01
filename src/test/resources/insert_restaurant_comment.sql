-- Inserindo restaurante
INSERT INTO restaurants (id, period, name, address, cuisine, capacity)
VALUES (1, 'Almoço', 'Restaurante Italiano', 'Rua das Flores', 'Italiana', 50);

-- Inserindo cliente
INSERT INTO customers (id, name, contact)
VALUES (1, 'Ângelo Lima', '(12) 98765-4321');

-- Inserindo comentário do restaurante
INSERT INTO restaurant_comments (id, comment, restaurant_id, customer_id)
VALUES (1, 'Ótimo restaurante!', 1, 1);
