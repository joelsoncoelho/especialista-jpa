insert into produto (id, nome, preco, descricao) values ( 1, 'Notebook Gamer Alienware', 14498.00, 'Experimente jogos de alto desempenho em notebooks com designs icônicos e projetados com inteligência.');
insert into produto (id, nome, preco, descricao) values (3, 'Notebook Acer Nitro 5', 4399.12, 'A fase avançada do seu jogo chegou! Esteja preparado com o notebook gamer Acer Aspire Nitro 5 . Com ele, não existe game difícil, existe game brutal!');

insert into cliente (id, nome) values (1, 'Zelda');
insert into cliente (id,nome) values (2, 'Malala');

insert into pedido (id, cliente_id, data_criacao, total, status) values (1, 1, sysdate(), 100.0, 'AGUARDANDO');

insert into item_pedido (id, pedido_id, produto_id, preco_produto, quantidade) values (1, 1, 1, 5.0, 2);

insert into categoria (id, nome, categoria_pai_id) values (1, 'Eletrônicos', 1);