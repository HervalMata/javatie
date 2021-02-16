set foreign_key_checks = 0;

delete from categoria;
delete from cidade;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from produto;
delete from usuario;
delete from usuario_grupo;

set foreign_key_checks = 1;

alter table categoria auto_increment = 1;
alter table cidade auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table produto auto_increment = 1;
alter table usuario auto_increment = 1;

INSERT INTO categoria (nome) Values ('Laços');
INSERT INTO categoria (nome) Values ('Tiaras');
INSERT INTO categoria (nome) Values ('Viseiras');
INSERT INTO categoria (nome) Values ('Faixas');

INSERT INTO estado (id, nome) Values (1, 'Bahia');
INSERT INTO estado (id, nome) Values (2, 'São Paulo');
INSERT INTO estado (id, nome) Values (3, 'Rio de Janeiro');

INSERT INTO cidade (id, nome, estado_id) Values (1, 'Vitória da Conquista', 1);
INSERT INTO cidade (id, nome, estado_id) Values (2, 'Itambé', 1);
INSERT INTO cidade (id, nome, estado_id) Values (3, 'Sorocaba', 2);
INSERT INTO cidade (id, nome, estado_id) Values (4, 'Osasco', 2);
INSERT INTO cidade (id, nome, estado_id) Values (5, 'Petropólis', 3);
INSERT INTO cidade (id, nome, estado_id) Values (6, 'Teresopólis', 3);

INSERT INTO forma_pagamento (id, descricao) Values (1, 'Cartão de Crédito');
INSERT INTO forma_pagamento (id, descricao) Values (2, 'Cartão de Dédito');
INSERT INTO forma_pagamento (id, descricao) Values (3, 'Dinheiro');

INSERT INTO permissao (id, nome, descricao) Values (1, 'CONSULTAR_CATEGORIAS' 'Permite consultsr categorias');
INSERT INTO permissao (id, nome, descricao) Values (2, 'EDITAR_CATEGORIAS', 'Permite editar categorias');