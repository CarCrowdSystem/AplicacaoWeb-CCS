insert into funcionario_entity (id, nome, rg, cpf, email, telefone, senha, cargo, logado) values
(1,'Nome1','15.151.187-1','111.222.333-44','email@1email.com','11999222221','senha','gerente',false),
(2,'Nome2','15.151.187-2','112.222.333-44','email2@email.com','11999222222','senha','funcionario',false),
(3,'Nome3','15.151.187-3','113.222.333-44','email3@email.com','11999222223','senha','funcionario',false),
(4,'Nome4','15.151.187-4','114.222.333-44','email4@email.com','11999222224','senha','funcionario',false),
(5,'Nome5','15.151.187-5','115.222.333-44','email5@email.com','11999222225','senha','funcionario',false);

insert into historico_entity (
nome_cliente, modelo, placa, andar, vaga,
telefone, data, entrada, saida, valor_pago
) values
('Jorge1', 'Onix', 'AAAAAA', 'Térreo', 12, '(11)91111-1111', '14/02/2023', '09:55', '10:55', 15.00),
('Jorge2', 'Onix', 'AAAAAA', 'Térreo', 12, '(11)91111-1111', '14/02/2023', '09:55', '10:55', 15.00),
('Jorge3', 'Onix', 'AAAAAA', 'Térreo', 12, '(11)91111-1111', '14/02/2023', '09:55', '10:55', 15.00);