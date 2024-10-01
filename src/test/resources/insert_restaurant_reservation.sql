INSERT INTO restaurants (id, period, name, address, cuisine, capacity)
VALUES (1, 'Almoço', 'Restaurante Italiano', 'Rua das Flores', 'Italiana', 50);

INSERT INTO customers (id, name, contact)
VALUES (1, 'Ângelo Lima', '(12) 98765-4321');

INSERT INTO restaurant_reservations (id, restaurant_id, customer_id, period, reservation_date)
VALUES (1, 1, 1, 'Almoço', '2024-12-25');
