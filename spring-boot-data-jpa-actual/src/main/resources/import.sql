INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (1,'Fayzal','Eljadue','fay.eljadue@gmail.com','2020-07-11','')
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (2,'Fayzal2','Eljadue2','fay2.eljadue@gmail.com','2020-07-11','')
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (3,'Fayzal','Eljadue','fay.eljadue@gmail.com','2020-07-11','')
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (4,'Fayzal2','Eljadue2','fay2.eljadue@gmail.com','2020-07-11','')
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (5,'Fayzal','Eljadue','fay.eljadue@gmail.com','2020-07-11','')
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (6,'Fayzal2','Eljadue2','fay2.eljadue@gmail.com','2020-07-11','')
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (7,'Fayzal','Eljadue','fay.eljadue@gmail.com','2020-07-11','')
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (8,'Fayzal2','Eljadue2','fay2.eljadue@gmail.com','2020-07-11','')
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (9,'Fayzal','Eljadue','fay.eljadue@gmail.com','2020-07-11','')
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (10,'Fayzal2','Eljadue2','fay2.eljadue@gmail.com','2020-07-11','')
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (11,'Fayzal','Eljadue','fay.eljadue@gmail.com','2020-07-11','')
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (12,'Fayzal2','Eljadue2','fay2.eljadue@gmail.com','2020-07-11','')
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (13,'Fayzal','Eljadue','fay.eljadue@gmail.com','2020-07-11','')
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (14,'Fayzal2','Eljadue2','fay2.eljadue@gmail.com','2020-07-11','')
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (15,'Fayzal','Eljadue','fay.eljadue@gmail.com','2020-07-11','')
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (16,'Fayzal2','Eljadue2','fay2.eljadue@gmail.com','2020-07-11','')
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (17,'Fayzal','Eljadue','fay.eljadue@gmail.com','2020-07-11','')
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (18,'Fayzal2','Eljadue2','fay2.eljadue@gmail.com','2020-07-11','')
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (19,'Fayzal','Eljadue','fay.eljadue@gmail.com','2020-07-11','')
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (20,'Fayzal2','Eljadue2','fay2.eljadue@gmail.com','2020-07-11','')
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (21,'Fayzal','Eljadue','fay.eljadue@gmail.com','2020-07-11','')
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (22,'Fayzal2','Eljadue2','fay2.eljadue@gmail.com','2020-07-11','')
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (23,'Fayzal','Eljadue','fay.eljadue@gmail.com','2020-07-11','')
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (24,'Fayzal2','Eljadue2','fay2.eljadue@gmail.com','2020-07-11','')
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto) VALUES (25,'Fayzal','Eljadue','fay.eljadue@gmail.com','2020-07-11','')

/* Populate tabla productos */
INSERT INTO productos (nombre, precio, create_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());

/* Creamos algunas facturas */
INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 7);

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(3, 2, 6);

/*Insercion en usuarios*/
INSERT INTO users(username,password,enable) values ('fay','$2a$10$4UBl/C6kbvjVZSYea4XrB.5BzORYeAuamAIWpXpwnyLsVT3J8zFwC',1);
INSERT INTO users(username,password,enable) values ('admin','$2a$10$rBVpzNmgesnJ/PmmA0g6UOWhmmvivoTSF8KhD7NTpCmhmcK8ut3bS',1);

/*Insercion de roles para los usuarios*/
INSERT INTO authorities(user_id,authority) values (1,'ROLE_USER');
INSERT INTO authorities(user_id,authority) values (2,'ROLE_ADMIN');
INSERT INTO authorities(user_id,authority) values (2,'ROLE_USER');
