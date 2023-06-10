INSERT INTO ESTACIONAMENTO_ENTITY
(cep, nome_estacionamento, cnpj, numero_endereco, status_estacionamento, telefone) VALUES
('00000-001', 'estacionamento1', '12345678912345', '001', 1, '99999-9991'),
('00000-002', 'estacionamento2', '12345678912345', '002', 1, '99999-9992'),
('00000-003', 'estacionamento3', '12345678912345', '003', 0, '99999-9993'),
('00000-004', 'estacionamento4', '12345678912345', '004', 0, '99999-9994');

INSERT INTO VAGA_ENTITY
(andar, numero, estacionamento_id) values
(0, 1, 1),
(0, 2, 1),
(0, 1, 2),
(0, 2, 2);

INSERT INTO VEICULO_ENTITY
(modelo, nome_cliente, placa, telefone_cliente) values
('bla1', 'diogo', 'aaa0001','11999871781'),
('bla2', 'romao', 'aaa0002','11999871782'),
('bla3', 'victoria', 'aaa0003','11999871783'),
('fantasma', 'fantasma', 'aaa0000','11999871784');

INSERT INTO HISTORICO_ENTITY
(momento_registro, status_registro, valor_pago, vaga_id, veiculo_id) VALUES
('2023-12-03T10:15:30', 0, 00.00, 1, 1),
('2023-12-03T10:15:35', 0, 00.00, 2, 2),
('2023-12-03T11:18:30', 1, 45.00, 1, 1),
('2023-12-03T10:19:30', 1, 35.00, 2, 2),
('2023-12-04T10:12:30', 0, 00.00, 2, 2),
('2023-12-03T10:15:30', 0, 00.00, 3, 3),
('2023-12-03T10:17:30', 1, 35.00, 3, 3),
('2023-12-04T10:12:30', 0, 00.00, 3, 3);

INSERT INTO FUNCIONARIO_ENTITY
(cpf, email, id_estacionamento, nome, senha, estacionamento_id) VALUES
('11111111111', 'joao@gmail.com', 1, 'joao', '12345', 1),
('22222222222', 'maria@gmail.com', 2, 'maria', '12345', 2),
('33333333333', 'marcos@gmail.com', 1, 'marcos', '12345', 1);

INSERT INTO VALOR_ESTACIONAMENTO_ENTITY
(diaria, hora_adicional, primeira_hora, estacionamento_id) VALUES
(40.0, 5.0, 15.0, 1),
(50.0, 6.0, 20.0, 2);