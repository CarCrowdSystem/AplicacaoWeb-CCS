INSERT INTO estacionamento
(cep, nome_estacionamento, cnpj, numero_endereco, status_estacionamento, telefone) VALUES
('00000-001', 'estacionamento1', '12345678912345', '001', 1, '99999-9991'),
('00000-002', 'estacionamento2', '12345678912345', '002', 1, '99999-9992'),
('00000-003', 'estacionamento3', '12345678912345', '003', 0, '99999-9993'),
('00000-004', 'estacionamento4', '12345678912345', '004', 0, '99999-9994');

INSERT INTO vaga
(andar, numero, fk_estacionamento) values
(0, 1, 1),
(0, 2, 1),
(0, 1, 2),
(0, 2, 2);

INSERT INTO cliente
(nome, telefone, cpf, email, senha) values
('tiozinho', '11111111111', '11122233344', 'tio@gmail.com', '12345678');

INSERT INTO veiculo
(modelo, marca, placa, fk_cliente) values
('bla1', 'bli', 'aaa0001', 1),
('bla1', 'bli', 'aaa0002', 1),
('bla1', 'bli', 'aaa0003', 1);

INSERT INTO historico
(momento_registro, status_registro, valor_pago, fk_vaga, fk_veiculo) VALUES
('2023-12-03T10:15:30', 0, 00.00, 1, 1),
('2023-12-03T10:15:35', 0, 00.00, 2, 2),
('2023-12-03T11:18:30', 1, 45.00, 1, 1),
('2023-12-03T10:19:30', 1, 35.00, 2, 2),
('2023-12-04T10:12:30', 0, 00.00, 2, 2),
('2023-12-03T10:15:30', 0, 00.00, 3, 3),
('2023-12-03T10:17:30', 1, 35.00, 3, 3),
('2023-12-04T10:12:30', 0, 00.00, 3, 3);

INSERT INTO funcionario
(cpf, email, nome_funcionario, senha, fk_estacionamento) VALUES
('11111111111', 'joao@gmail.com', 'joao', '12345', 1),
('22222222222', 'maria@gmail.com', 'maria', '12345', 1),
('33333333333', 'marcos@gmail.com', 'marcos', '12345', 1);

INSERT INTO valor_estacionamento
(diaria, hora_adicional, primeira_hora, fk_estacionamento) VALUES
(40.0, 5.0, 15.0, 1),
(50.0, 6.0, 20.0, 2);