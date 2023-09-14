INSERT INTO product (id, name, last_name) VALUES (1, 'N1', 'LN1');
INSERT INTO product (id, name, last_name) VALUES (2, 'N2', 'LN2');
ALTER TABLE product ALTER COLUMN ID RESTART WITH 3;