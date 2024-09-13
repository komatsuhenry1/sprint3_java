create table if not exists cliente(
    cliente_id bigint not null auto_increment,
    nome varchar(255),
    email varchar(255),
    endereco varchar(255),
    senha varchar(255),
    primary key (cliente_id)
);

create table if not exists categoria(
    categoria_id bigint not null auto_increment,
    nome varchar(255),
    primary key (categoria_id) 
);

create table if not exists produto(
    produto_id bigint not null auto_increment,
    nome varchar(255),
    descricao varchar(255),
    preco double,
    categoria_id bigint,
    primary key (produto_id),
    foreign key (categoria_id) references categoria(categoria_id)
);

create table if not exists pedido(
    pedido_id bigint not null auto_increment,
    data_pedido date,
    preco_total double,
    cliente_id bigint,
    primary key (pedido_id),
    foreign key (cliente_id) references cliente(cliente_id) 
);

create table if not exists items_pedido(
    items_id bigint not null auto_increment,
    quantidade int,
    preco double,
    pedido_id bigint,
    produto_id bigint,
    primary key (items_id),
    foreign key (pedido_id) references pedido(pedido_id),
    foreign key (produto_id) references produto(produto_id)
);