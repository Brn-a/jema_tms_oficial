-- =========================================
-- ENDERECO
-- =========================================
INSERT INTO endereco(logradouro, bairro, cidade, uf, num, cep, complemento)VALUES('Rua das Flores', 'Centro', 'São Paulo', 'SP', 123, '01001000', 'Apartamento 45');

-- =========================================
-- CLIENTE
-- =========================================
INSERT INTO cliente(cpf, cnpj, numero, email, nome, sobrenome, fk_endereco)VALUES('12345678900', NULL, '11999999999', 'cliente@email.com', 'João', 'Silva', 1);

-- =========================================
-- CAMINHAO
-- =========================================
INSERT INTO caminhao(tipo, placa, modelo)VALUES('TRUCK', 'ABC1D23', 'Volkswagen Delivery');

-- =========================================
-- MOTORISTA
-- =========================================
INSERT INTO motorista(cpf, rg, nome, sobrenome, genero, cnh,telefone, telefone_responsavel,observacoes_saude, horario_disponivel,fk_caminhao)VALUES('98765432100', '123456789', 'Carlos', 'Souza','Masculino', '12345678900','11988887777', '11977776666','Nada a declarar', '08:00-18:00',1);

-- =========================================
-- MOTORISTA_DIAS_DISPONIVEIS
-- (tabela criada automaticamente pelo @ElementCollection)
-- =========================================
INSERT INTO motorista_dias_disponiveis(motorista_id, dias_disponiveis)VALUES(1, 'SEGUNDA'),(1, 'TERCA'),(1, 'QUARTA');

-- =========================================
-- PRODUTO
-- =========================================
INSERT INTO produto(nome, cor, sku, volumetria_total, preco)VALUES('Notebook Gamer', 'PRETO', 'SKU12345', 0.045, 5500.00);

-- =========================================
-- LISTA
-- =========================================
INSERT INTO lista(nome_rota, qtd_nfs, qtd_nfs_entregues,qtd_nfs_nao_entregues, peso_total,volumetria_total)VALUES('Rota Zona Sul', 1, 0, 1, 150.0, 12.5);

-- =========================================
-- NOTA FISCAL
-- =========================================
INSERT INTO nota_fiscal(numero, volume, peso, quem_recebe,valor_final, valor_frete,fk_cliente, fk_lista)VALUES(1001, 2.5, 150.0, 'Maria',6200.00, 350.00,1, 1);