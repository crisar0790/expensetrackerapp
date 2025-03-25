INSERT INTO users (name, email) VALUES ('Alice Johnson', 'alice@example.com');
INSERT INTO users (name, email) VALUES ('Bob Smith', 'bob@example.com');
INSERT INTO users (name, email) VALUES ('Charlie Brown', 'charlie@example.com');
INSERT INTO users (name, email) VALUES ('Diana Prince', 'diana@example.com');
INSERT INTO users (name, email) VALUES ('Ethan Hunt', 'ethan@example.com');

INSERT INTO category (name, description) VALUES ('food', 'Expenses on meals, groceries, and dining out.');
INSERT INTO category (name, description) VALUES ('transport', 'Costs related to commuting, gas, and public transport.');
INSERT INTO category (name, description) VALUES ('entertainment', 'Expenses for movies, concerts, and leisure activities.');
INSERT INTO category (name, description) VALUES ('health', 'Medical bills, medicines, and gym memberships.');
INSERT INTO category (name, description) VALUES ('shopping', 'Clothing, accessories, and general shopping expenses.');
INSERT INTO category (name, description) VALUES ('education', 'Tuition fees, books, and learning materials.');
INSERT INTO category (name, description) VALUES ('bills', 'Monthly utility bills such as electricity, water, and internet.');
INSERT INTO category (name, description) VALUES ('other', 'Miscellaneous expenses that do not fit into any category.');

INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (1, 1, 15.50, '2025-03-20', 'food', 'Lunch at a restaurant');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (1, 2, 40.00, '2025-03-18', 'transport', 'Gas refill');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (1, 3, 20.00, '2025-03-15', 'entertainment', 'Movie ticket');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (1, 4, 50.00, '2025-03-12', 'health', 'Medical check-up');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (1, 5, 100.00, '2025-03-10', 'shopping', 'New shoes');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (1, 7, 30.00, '2025-03-05', 'bills', 'Electricity bill');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (1, 1, 12.75, '2025-03-22', 'food', 'Breakfast at café');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (1, 2, 45.30, '2025-03-19', 'transport', 'Gas for the car');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (1, 3, 22.00, '2025-03-17', 'entertainment', 'Concert ticket');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (1, 4, 55.00, '2025-03-16', 'health', 'Doctor visit');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (1, 5, 89.99, '2025-03-14', 'shopping', 'New jeans');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (1, 6, 120.00, '2025-03-12', 'education', 'Online course subscription');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (1, 7, 33.50, '2025-03-10', 'bills', 'Electricity bill');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (1, 8, 27.00, '2025-03-08', 'other', 'Birthday gift');

INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (2, 1, 25.00, '2025-03-21', 'food', 'Groceries');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (2, 2, 60.00, '2025-03-19', 'transport', 'Taxi ride');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (2, 6, 200.00, '2025-03-16', 'education', 'Online course');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (2, 5, 150.00, '2025-03-14', 'shopping', 'New phone accessories');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (2, 8, 20.00, '2025-03-09', 'other', 'Miscellaneous expenses');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (2, 4, 75.00, '2025-03-08', 'health', 'Gym membership');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (2, 1, 18.50, '2025-03-23', 'food', 'Lunch at a diner');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (2, 2, 65.00, '2025-03-20', 'transport', 'Train ticket');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (2, 3, 15.00, '2025-03-18', 'entertainment', 'Netflix subscription');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (2, 4, 95.00, '2025-03-17', 'health', 'Pharmacy purchases');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (2, 5, 210.00, '2025-03-15', 'shopping', 'New smartphone case');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (2, 6, 80.00, '2025-03-13', 'education', 'New textbooks');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (2, 7, 48.00, '2025-03-11', 'bills', 'Internet bill');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (2, 8, 30.00, '2025-03-09', 'other', 'Random shopping');

INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (3, 1, 10.00, '2025-03-20', 'food', 'Breakfast');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (3, 3, 50.00, '2025-03-18', 'entertainment', 'Concert ticket');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (3, 4, 120.00, '2025-03-15', 'health', 'Dental treatment');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (3, 6, 80.00, '2025-03-14', 'education', 'New books');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (3, 7, 40.00, '2025-03-12', 'bills', 'Internet bill');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (3, 8, 35.00, '2025-03-10', 'other', 'Unexpected repairs');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (3, 1, 14.90, '2025-03-22', 'food', 'Coffee and snacks');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (3, 2, 52.75, '2025-03-20', 'transport', 'Uber ride');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (3, 3, 29.99, '2025-03-18', 'entertainment', 'Concert ticket');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (3, 4, 40.00, '2025-03-17', 'health', 'New medicine');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (3, 5, 180.00, '2025-03-15', 'shopping', 'Clothing shopping');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (3, 6, 130.00, '2025-03-13', 'education', 'Online certification course');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (3, 7, 42.00, '2025-03-11', 'bills', 'Water bill');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (3, 8, 55.00, '2025-03-09', 'other', 'Household supplies');

INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (4, 2, 50.00, '2025-03-21', 'transport', 'Metro pass');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (4, 3, 30.00, '2025-03-20', 'entertainment', 'Theater ticket');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (4, 4, 90.00, '2025-03-18', 'health', 'Medicine purchase');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (4, 5, 200.00, '2025-03-17', 'shopping', 'Clothing shopping');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (4, 6, 150.00, '2025-03-15', 'education', 'Online training');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (4, 8, 25.00, '2025-03-13', 'other', 'Gifts');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (4, 1, 22.50, '2025-03-21', 'food', 'Dinner with friends');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (4, 2, 77.00, '2025-03-19', 'transport', 'Flight ticket');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (4, 3, 19.99, '2025-03-17', 'entertainment', 'Spotify premium');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (4, 4, 60.00, '2025-03-16', 'health', 'Doctor appointment');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (4, 5, 95.00, '2025-03-14', 'shopping', 'New handbag');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (4, 6, 140.00, '2025-03-12', 'education', 'Workshop fee');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (4, 7, 70.00, '2025-03-10', 'bills', 'Electricity and gas bill');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (4, 8, 20.00, '2025-03-08', 'other', 'Miscellaneous expenses');

INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (5, 1, 30.00, '2025-03-22', 'food', 'Dinner at a steakhouse');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (5, 2, 70.00, '2025-03-20', 'transport', 'Car rental');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (5, 3, 15.00, '2025-03-18', 'entertainment', 'Subscription to a streaming service');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (5, 4, 110.00, '2025-03-17', 'health', 'Physiotherapy session');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (5, 7, 90.00, '2025-03-14', 'bills', 'Gas and water bill');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (5, 8, 45.00, '2025-03-12', 'other', 'New home decorations');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (5, 1, 35.00, '2025-03-22', 'food', 'Dinner at a steakhouse');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (5, 2, 95.00, '2025-03-20', 'transport', 'Rental car fuel');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (5, 3, 27.50, '2025-03-18', 'entertainment', 'Movie tickets');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (5, 4, 80.00, '2025-03-17', 'health', 'Therapist appointment');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (5, 5, 220.00, '2025-03-15', 'shopping', 'Designer shoes');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (5, 6, 110.00, '2025-03-13', 'education', 'E-learning subscription');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (5, 7, 65.00, '2025-03-11', 'bills', 'Cable and internet bill');
INSERT INTO expense (id_user, id_category, amount, date, category, description) VALUES (5, 8, 48.00, '2025-03-09', 'other', 'Grocery store miscellaneous');
