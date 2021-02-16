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