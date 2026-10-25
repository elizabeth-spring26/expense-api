-- Sample data loaded at startup so there's something to look at.
INSERT INTO expense (id, description, amount, category, date) VALUES (NEXT VALUE FOR expense_seq, 'Groceries', 64.20, 'food', '2026-10-03');
INSERT INTO expense (id, description, amount, category, date) VALUES (NEXT VALUE FOR expense_seq, 'Coffee', 5.75, 'food', '2026-10-07');
INSERT INTO expense (id, description, amount, category, date) VALUES (NEXT VALUE FOR expense_seq, 'Train pass', 90.00, 'transport', '2026-10-01');
INSERT INTO expense (id, description, amount, category, date) VALUES (NEXT VALUE FOR expense_seq, 'Textbook', 120.00, 'school', '2026-09-28');
