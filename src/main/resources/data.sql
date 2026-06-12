-- Artists
INSERT INTO artist (name, genre, bio) VALUES ('The Rolling Stones', 'Rock', 'Legendary British rock band formed in London in 1962.');
INSERT INTO artist (name, genre, bio) VALUES ('Dua Lipa', 'Pop', 'English-Albanian singer and songwriter known for her disco-pop style.');
INSERT INTO artist (name, genre, bio) VALUES ('Tiësto', 'EDM', 'Dutch DJ and record producer, one of the biggest names in electronic dance music.');

-- Venues
INSERT INTO venue (name, city, capacity) VALUES ('AFAS Live', 'Amsterdam', 6000);
INSERT INTO venue (name, city, capacity) VALUES ('Ziggo Dome', 'Amsterdam', 17000);
INSERT INTO venue (name, city, capacity) VALUES ('Paradiso', 'Amsterdam', 1500);

-- Concerts (2 past, 4 future — some with limited seats for overbooking testing)
INSERT INTO concert (title, date, artist_id, venue_id, total_seats, available_seats, ticket_price) VALUES ('Stones Classic Night', '2025-06-15', 1, 2, 17000, 17000, 85.00);
INSERT INTO concert (title, date, artist_id, venue_id, total_seats, available_seats, ticket_price) VALUES ('Dua Lipa Club Show', '2025-11-20', 2, 3, 1500, 1500, 55.00);
INSERT INTO concert (title, date, artist_id, venue_id, total_seats, available_seats, ticket_price) VALUES ('Tiësto Summer Bash', '2026-07-10', 3, 1, 6000, 6000, 65.00);
INSERT INTO concert (title, date, artist_id, venue_id, total_seats, available_seats, ticket_price) VALUES ('Rolling Stones Farewell', '2026-09-05', 1, 1, 6000, 15, 120.00);
INSERT INTO concert (title, date, artist_id, venue_id, total_seats, available_seats, ticket_price) VALUES ('Dua Lipa Arena Tour', '2026-10-18', 2, 2, 17000, 10, 75.00);
INSERT INTO concert (title, date, artist_id, venue_id, total_seats, available_seats, ticket_price) VALUES ('Tiësto Intimate Set', '2026-12-31', 3, 3, 1500, 1500, 45.00);

-- Bookings (for demonstrating BUG-05 — bookings on different concerts)
INSERT INTO booking (customer_name, customer_email, concert_id, number_of_tickets, total_price, booking_date) VALUES ('Jan de Vries', 'jan@example.com', 3, 4, 260.00, '2026-02-01');
INSERT INTO booking (customer_name, customer_email, concert_id, number_of_tickets, total_price, booking_date) VALUES ('Sophie Bakker', 'sophie@example.com', 3, 2, 130.00, '2026-02-05');
INSERT INTO booking (customer_name, customer_email, concert_id, number_of_tickets, total_price, booking_date) VALUES ('Emma Jansen', 'emma@example.com', 4, 3, 360.00, '2026-02-10');
