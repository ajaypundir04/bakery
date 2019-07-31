INSERT INTO PRODUCT(ID, NAME, CODE, CREATED_AT) VALUES
(1, 'Vegemite Scroll', 'VS5', NOW()),
(2, 'Blueberry Muffin', 'MB11', NOW()),
(3, 'Croissant', 'CF', NOW());


INSERT INTO INVENTORY(ID, PRODUCT_ID, QUANTITY, CREATED_AT) VALUES
(1, 1, 30, NOW()),
(2, 2, 30, NOW()),
(3, 3, 30, NOW());

INSERT INTO PACKS(ID, PRODUCT_ID, QUANTITY, PRICE, CREATED_AT) VALUES
(1, 1, 3, 6.99, NOW()),
(2, 1, 5, 8.99, NOW()),
(3, 2, 2, 9.95, NOW()),
(4, 2, 5, 16.95, NOW()),
(5, 2, 8, 24.95, NOW()),
(6, 3, 3, 5.95, NOW()),
(7, 3, 5, 9.95, NOW()),
(8, 3, 9, 16.99, NOW());

