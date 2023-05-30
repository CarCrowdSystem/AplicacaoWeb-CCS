INSERT INTO ESTACIONAMENTO_ENTITY
(id, cep, nome_estacionamento, numero_endereco, status_estacionamento, telefone) VALUES
(1, '00000-001', 'estacionamento1', '001', 0, '99999-9991'),
(2, '00000-002', 'estacionamento2', '002', 1, '99999-9992'),
(3, '00000-003', 'estacionamento3', '003', 1, '99999-9993'),
(4, '00000-004', 'estacionamento4', '004', 0, '99999-9994');

INSERT INTO VAGA_ENTITY
(id, andar, numero, estacionamento_id) values
(1, 0, 1, 1),
(2, 0, 2, 1);

INSERT INTO VEICULO_ENTITY
(id, modelo, nome_cliente, placa, telefone_cliente) values
(1, 'bla1', 'diogo', 'aaa0001','11999871781'),
(2, 'bla2', 'romao', 'aaa0002','11999871782'),
(3, 'bla3', 'victoria', 'aaa0003','11999871783'),
(4, 'bla4', 'mary', 'aaa0004','11999871784');

INSERT INTO HISTORICO_ENTITY
(id, momento_registro, status_registro, valor_pago, vaga_id, veiculo_id) VALUES
(1, '2023-12-03T10:15:30', 'Entrada', 00.00, 1, 1),
(2, '2023-12-03T10:15:30', 'Entrada', 00.00, 2, 2),
(3, '2023-12-03T10:15:30', 'Saída', 25.00, 1, 1),
(4, '2023-12-03T10:15:30', 'Saída', 35.00, 2, 2);