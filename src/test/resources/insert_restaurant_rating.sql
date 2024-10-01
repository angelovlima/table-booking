INSERT INTO restaurants (id, period, name, address, cuisine, capacity)
VALUES (1, 'Almoço', 'Restaurante Italiano', 'Rua das Flores', 'Italiana', 50);

INSERT INTO customers (id, name, contact)
VALUES (1, 'Ângelo Lima', '(12) 98765-4321');

INSERT INTO restaurant_ratings (id, stars, restaurant_id, customer_id)
VALUES (1, 5, 1, 1);
