-- Inserts para a tabela cliente
insert into cliente (nome, email, endereco, senha) values 
('João Silva', 'joao.silva@email.com', 'Rua A, 123', 'senha123'),
('Maria Oliveira', 'maria.oliveira@email.com', 'Rua B, 456', 'senha456');

-- Inserts para a tabela categoria
insert into categoria (nome) values 
('Eletrônicos'),
('Roupas');

-- Inserts para a tabela produto
insert into produto (nome, descricao, preco, categoria_id) values 
('Smartphone', 'Smartphone Android', 1500.00, 1),
('Notebook', 'Notebook 15 polegadas', 3500.00, 1),
('Camiseta', 'Camiseta 100% algodão', 50.00, 2),
('Calça Jeans', 'Calça jeans azul', 100.00, 2),
('Fone de Ouvido', 'Fone de ouvido bluetooth', 200.00, 1),
('Televisão', 'Televisão 42 polegadas', 2200.00, 1),
('Tênis', 'Tênis esportivo', 300.00, 2),
('Jaqueta', 'Jaqueta de couro', 400.00, 2);

-- Inserts para a tabela pedido
insert into pedido (data_pedido, preco_total, cliente_id) values 
('2024-09-10', 2000.00, 1),
('2024-09-11', 4200.00, 2);

-- Inserts para a tabela items_pedido
insert into items_pedido (quantidade, preco, pedido_id, produto_id) values 
(1, 1500.00, 1, 1),
(1, 500.00, 1, 5),
(1, 3500.00, 2, 2),
(1, 700.00, 2, 6);
