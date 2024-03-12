set foreign_key_checks = 0;

lock tables sequential_code write,
    payment_method write, ingredient write,
    ingredient_stock write, product_ingredient write,
	`group` write, group_permission write, permission write,
	product write, snack_shop write, snack_shop_payment_method write,
	snack_shop_user_admin write, `user` write, user_group write,
	`order` write, order_item write, product_image write;

delete from sequential_code;
delete from payment_method;
delete from ingredient;
delete from product_ingredient;
delete from ingredient_stock;
delete from `group` ;
delete from group_permission;
delete from permission;
delete from product;
delete from snack_shop;
delete from snack_shop_payment_method;
delete from snack_shop_user_admin;
delete from `user`;
delete from user_group;
delete from `order`;
delete from order_item;
delete from product_image;

set foreign_key_checks = 1;

alter table payment_method auto_increment = 1;
alter table ingredient auto_increment = 1;
alter table product_ingredient auto_increment = 1;
alter table ingredient_stock auto_increment = 1;
alter table `group` auto_increment = 1;
alter table permission auto_increment = 1;
alter table product auto_increment = 1;
alter table snack_shop auto_increment = 1;
alter table `user` auto_increment = 1;
alter table `order` auto_increment = 1;
alter table order_item auto_increment = 1;


insert into snack_shop (id, name, registration_date, update_date, active) values (1, 'Snack Shop Delivery', utc_timestamp, utc_timestamp, true);

insert into payment_method (id, description) values (1, 'Cartão de Crédito');
insert into payment_method (id, description) values (2, 'Cartão de Débito');
insert into payment_method (id, description) values (3, 'Dinheiro');
insert into payment_method (id, description) values (4, 'Mercado Pago');

insert into permission (id, name, description) values (1, 'EDITAR_FORMAS_PAGAMENTO', 'Permite criar ou editar formas de pagamento');
insert into permission (id, name, description) values (2, 'CONSULTAR_USUARIOS_GRUPOS_PERMISSOES', 'Permite consultar usuários, grupos e permissões');
insert into permission (id, name, description) values (3, 'EDITAR_USUARIOS_GRUPOS_PERMISSOES', 'Permite criar ou editar usuários, grupos e permissões');
insert into permission (id, name, description) values (4, 'EDITAR_LANCHONETES', 'Permite criar, editar ou gerenciar lanchonetes');
insert into permission (id, name, description) values (5, 'CONSULTAR_PEDIDOS', 'Permite consultar pedidos');
insert into permission (id, name, description) values (6, 'GERENCIAR_PEDIDOS', 'Permite gerenciar pedidos');
insert into permission (id, name, description) values (7, 'GERAR_RELATORIOS', 'Permite gerar relatórios');

insert into snack_shop_payment_method (snack_shop_id, payment_method_id) values (1, 1), (1, 2), (1, 3), (1, 4);

INSERT INTO product (name, description, price, category, active, snack_shop_id) VALUES
('Sanduíche de Frango Grelhado', 'Pão macio recheado com peito de frango grelhado, alface, tomate e maionese.', 12.50, 'SNACK', 1, 1),
('X-Tudo Especial', 'Um mega sanduíche com carne, queijo, presunto, ovo, alface, tomate e maionese.', 15.99, 'SNACK', 1, 1),
('Refrigerante Cola Classic', 'Bebida gaseificada refrescante, com sabor clássico de cola.', 4.00, 'DRINK', 1, 1),
('Suco de Laranja Natural', 'Suco fresco e natural de laranja recém-espremida.', 6.50, 'DRINK', 1, 1),
('Sundae de Chocolate', 'Sorvete cremoso de baunilha coberto com calda de chocolate, chantilly e granulado.', 8.75, 'DESSERT', 1, 1),
('Salada Caesar com Frango', 'Salada clássica Caesar com tiras de frango grelhado, croutons e molho Caesar.', 10.99, 'SIDE_DISH', 1, 1),
('Água Mineral Sem Gás', 'Água purificada, sem gás, para uma opção de bebida leve.', 3.50, 'DRINK', 1, 1),
('Brownie de Chocolate com Nozes', 'Brownie rico em chocolate com pedaços de nozes, servido quente.', 7.25, 'DESSERT', 1, 1),
('Sanduíche Vegetariano', 'Pão integral recheado com vegetais frescos, queijo e molho especial.', 11.50, 'SNACK', 1, 1),
('Milkshake de Morango', 'Milkshake cremoso de morango, perfeito para adoçar seu paladar.', 9.00, 'DESSERT', 1, 1);

insert into `group` (id, name) values (1, 'Gerente'), (2, 'Vendedor'), (3, 'Secretária'), (4, 'Cadastrador');

-- Adiciona todas as permissoes no grupo do gerente
insert into group_permission (group_id, permission_id)
select 1, id from permission;

-- Adiciona permissoes no grupo do vendedor
insert into group_permission (group_id, permission_id)
select 2, id from permission where name like 'CONSULTAR_%';

insert into group_permission (group_id, permission_id)
select 2, id from permission where name = 'EDITAR_LANCHONETES';

-- Adiciona permissoes no grupo do auxiliar
insert into group_permission (group_id, permission_id)
select 3, id from permission where name like 'CONSULTAR_%';

-- Adiciona permissoes no grupo cadastrador
insert into group_permission (group_id, permission_id)
select 4, id from permission where name like '%_LANCHONETES';

INSERT INTO `user` (id, name, cpf, email, password, registration_date) VALUES
(1, 'João Silva', '123.456.789-01', 'joao.silva@email.com', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', '2024-01-23 10:30:00'),
(2, 'Maria Oliveira', '987.654.321-02', 'maria.oliveira@email.com', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', '2024-01-23 11:15:00'),
(3, 'Carlos Santos', '456.789.012-03', 'carlos.santos@email.com', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', '2024-01-23 12:00:00'),
(4, 'Amanda Pereira', '789.012.345-04', 'amanda.pereira@email.com', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', '2024-01-23 14:45:00'),
(5, 'Lucas Souza', '234.567.890-05', 'lucas.souza@email.com', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', '2024-01-23 15:30:00'),
(6, 'Anônimo', null , 'totem@email.com', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', '2024-01-23 15:30:00');

insert into user_group (user_id, group_id) values (1, 1), (1, 2), (2, 2), (3, 3), (4, 4), (6, 2);

insert into snack_shop_user_admin (snack_shop_id, user_id) values (1, 4);
insert into snack_shop_user_admin (snack_shop_id, user_id) values (1, 5);


-- Pedido 1
INSERT INTO `order` (id, code, total, snack_shop_id, user_client_id, payment_method_id, status, reception_date, preparation_date, confirmation_date, finalization_date)
VALUES (1, 1, 42.50, 1, 2, 3, 'IN_PROGRESS', '2024-01-23 10:30:00', '2024-01-23 10:33:00', NULL, NULL);

-- Item do Pedido 1
INSERT INTO order_item (id, quantity, unit_price, total_price, observation, order_id, product_id)
VALUES (1, 2, 12.50, 25.00, 'Sem cebola', 1, 1);



-- Pedido 2
INSERT INTO `order` (id, code, total, snack_shop_id, user_client_id, payment_method_id, status, reception_date, preparation_date, confirmation_date, finalization_date)
VALUES (2, 2, 26.75, 1, 4, 1, 'RECEIVED', '2024-01-23 11:45:00', NULL, NULL, NULL);

-- Item do Pedido 2
INSERT INTO order_item (id, quantity, unit_price, total_price, observation, order_id, product_id)
VALUES (2, 1, 26.75, 26.75, 'Adicionar queijo extra', 2, 2);



-- Pedido 3
INSERT INTO `order` (id, code, total, snack_shop_id, user_client_id, payment_method_id, status, reception_date, preparation_date, confirmation_date, finalization_date)
VALUES (3, 3, 15.00, 1, 1, 2, 'DELIVERED', '2024-01-23 13:20:00', '2024-01-23 13:40:00', '2024-01-23 14:00:00', '2024-01-23 14:30:00');

-- Item do Pedido 3
INSERT INTO order_item (id, quantity, unit_price, total_price, observation, order_id, product_id)
VALUES (3, 3, 5.00, 15.00, NULL, 3, 5);



-- Pedido 4
INSERT INTO `order` (id, code, total, snack_shop_id, user_client_id, payment_method_id, status, reception_date, preparation_date, confirmation_date, finalization_date)
VALUES (4, 4, 38.90, 1, 5, 3, 'RECEIVED', '2024-01-23 15:00:00', NULL, NULL, NULL);

-- Item do Pedido 4
INSERT INTO order_item (id, quantity, unit_price, total_price, observation, order_id, product_id)
VALUES (4, 1, 38.90, 38.90, 'Sem maionese', 4, 2);



-- Pedido 5
INSERT INTO `order` (id, code, total, snack_shop_id, user_client_id, payment_method_id, status, reception_date, preparation_date, confirmation_date, finalization_date)
VALUES (5, 5, 19.99, 1, 3, 1, 'DELIVERED', '2024-01-23 16:30:00', '2024-01-23 16:45:00', '2024-01-23 17:00:00', '2024-01-23 17:30:00');

-- Item do Pedido 5
INSERT INTO order_item (id, quantity, unit_price, total_price, observation, order_id, product_id)
VALUES (5, 2, 9.99, 19.98, 'Adicionar ketchup', 5, 1);

-- Populando a tabela de ingredientes
INSERT INTO ingredient (name, description) VALUES
('Pão', 'Ingrediente básico para sanduíches.'),
('Peito de Frango Grelhado', 'Peito de frango grelhado, cortado em fatias finas.'),
('Alface', 'Folhas frescas de alface.'),
('Tomate', 'Tomates frescos e maduros.'),
('Maionese', 'Maionese cremosa e saborosa.'),
('Carne', 'Carne de qualidade selecionada.'),
('Queijo', 'Queijo derretido e delicioso.'),
('Presunto', 'Presunto defumado e fatiado.'),
('Ovo', 'Ovo fresco e saboroso.'),
('Sorvete de Baunilha', 'Sorvete cremoso de baunilha.'),
('Calda de Chocolate', 'Calda de chocolate rico e encorpado.'),
('Chantilly', 'Creme chantilly leve e aerado.'),
('Granulado', 'Granulado colorido e crocante.'),
('Tiras de Frango Grelhado', 'Tiras suculentas de frango grelhado.'),
('Croutons', 'Croutons crocantes e dourados.'),
('Nozes', 'Nozes inteiras e frescas.'),
('Vegetais Frescos', 'Vegetais frescos e coloridos.'),
('Sorvete de Morango', 'Sorvete cremoso de morango.'),
('Leite', 'Leite fresco e nutritivo.'),
('Morangos Frescos', 'Morangos vermelhos e suculentos.');

-- Populando a tabela de estoque de ingredientes
INSERT INTO ingredient_stock (ingredient_id, stock_quantity) VALUES
(1, 100),   -- Pão
(2, 50),    -- Peito de Frango Grelhado
(3, 100),   -- Alface
(4, 100),   -- Tomate
(5, 100),   -- Maionese
(6, 50),    -- Carne
(7, 100),   -- Queijo
(8, 100),   -- Presunto
(9, 100),   -- Ovo
(10, 50),   -- Sorvete de Baunilha
(11, 100),  -- Calda de Chocolate
(12, 100),  -- Chantilly
(13, 100),  -- Granulado
(14, 50),   -- Tiras de Frango Grelhado
(15, 100),  -- Croutons
(16, 100),  -- Nozes
(17, 100),  -- Vegetais Frescos
(18, 50),   -- Sorvete de Morango
(19, 100),  -- Leite
(20, 100);  -- Morangos Frescos

-- Populando a tabela de produtos e ingredientes
INSERT INTO product_ingredient (quantity, product_id, ingredient_id) VALUES
(1, 1, 1),    -- Pão para Sanduíche de Frango Grelhado
(1, 1, 2),    -- Peito de Frango Grelhado para Sanduíche de Frango Grelhado
(1, 1, 3),    -- Alface para Sanduíche de Frango Grelhado
(1, 1, 4),    -- Tomate para Sanduíche de Frango Grelhado
(1, 1, 5),    -- Maionese para Sanduíche de Frango Grelhado
(1, 2, 6),    -- Carne para X-Tudo Especial
(1, 2, 7),    -- Queijo para X-Tudo Especial
(1, 2, 8),    -- Presunto para X-Tudo Especial
(1, 2, 9),    -- Ovo para X-Tudo Especial
(1, 2, 3),    -- Alface para X-Tudo Especial
(1, 2, 4),    -- Tomate para X-Tudo Especial
(1, 2, 5),    -- Maionese para X-Tudo Especial
(1, 5, 10),   -- Sorvete de Baunilha para Sundae de Chocolate
(1, 5, 11),   -- Calda de Chocolate para Sundae de Chocolate
(1, 5, 12),   -- Chantilly para Sundae de Chocolate
(1, 5, 13),   -- Granulado para Sundae de Chocolate
(1, 6, 14),   -- Tiras de Frango Grelhado para Salada Caesar com Frango
(1, 6, 3),    -- Alface para Salada Caesar com Frango
(1, 6, 15),   -- Croutons para Salada Caesar com Frango
(1, 6, 16),   -- Nozes para Brownie de Chocolate com Nozes
(1, 7, 17),   -- Vegetais Frescos para Sanduíche Vegetariano
(1, 9, 18),   -- Sorvete de Morango para Milkshake de Morango
(1, 9, 19),   -- Leite para Milkshake de Morango
(1, 9, 20);   -- Morangos Frescos para Milkshake de Morango

-- Populando a tabela de sequenciais
INSERT INTO sequential_code (code, category) VALUES
(6, "USERS"),
(5, "ORDERS");

unlock tables;