INSERT INTO gift (name) VALUES
  ('Telescope'),
  ('10 lb Weights'),
  ('Chocolate'),
  ('Big Gift'),
  ('Tarot Card Deck');

INSERT INTO gifter (first_name, last_name) VALUES
  ('Levi-Ann', 'McDonnell'),
  ('Dee-Ann', 'Miner-Evans'),
  ('Terrence', 'McDonnell');

INSERT INTO  gifting_event (date, favorite, occasion, gift_id) VALUES
  ('2022-12-25', true, 'Christmas', 1),
  ('2022-12-25', false, 'Christmas', 2),
  ('2022-12-25', false, 'Christmas', 3),
  ('2022-12-25', true, 'Christmas', 4),
  ('2022-12-25', true, 'Christmas', 5);

INSERT INTO gifting_event_givers (gifting_event_id, givers_id) VALUES
(1, 2),
(2, 2),
(3, 1),
(4, 1),
(4, 2),
(5, 2);

INSERT INTO gifting_event_receivers (gifting_event_id, receivers_id) VALUES
(1, 3),
(2, 3),
(3, 3),
(4, 3),
(5, 1);
