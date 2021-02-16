create table forma_pagamento (
	id bigint not null auto_increment,
	descricao varchar(60) not null,
	
	primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table grupo (
	id bigint not null auto_increment,
	nome varchar(60) not null,
	
	primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table grupo (
	id bigint not null auto_increment,
	nome varchar(60) not null,
	descricao varchar(60) not null,
	
	primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table grupo_permissao (
	grupo_id bigint not null,
	permissao_id bigint not null,
	
	primary key (grupo_id, permissao_id)
) engine=InnoDB default charset=utf8mb4;

create table produto (
	id bigint not null auto_increment,
	categoria_id bigint not null,
	nome varchar(60) not null,
	descricao varchar(60) not null,
	preco decomal(10, 2) not null,
	ativo tinyint(1) not null,
	
	primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table usuario (
	id bigint not null auto_increment,
	nome varchar(60) not null,
	email varchar(255) not null,
	senha varchar(255) not null,
	data_cadastro datetime not null,
	
	primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table usuario_grupo (
	usuario_id bigint not null,
	grupo_id bigint not null,
	
	primary key (usuario_id, grupo_id)
) engine=InnoDB default charset=utf8mb4;

alter table grupo_permissao add constraint fk_grupo_permissao_permissao
foreign key (permissao_id) references permissao (id);

alter table grupo_permissao add constraint fk_grupo_permissao_grupo
foreign key (grupo_id) references grupo (id);

alter table produto add constraint fk_produto_categoria
foreign key (categoria_id) references categoria (id);

alter table usuario_grupo add constraint fk_usuario_grupo_grupo
foreign key (grupo_id) references grupo (id);

alter table usuario_grupo add constraint fk_usuario_grupo_usuario
foreign key (usuario_id) references usuario (id);
