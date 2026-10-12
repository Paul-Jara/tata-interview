-- Precarga de datos para customer-service (Personas y Clientes)
INSERT INTO person (person_id, address, age, gender, identification, name, phone) 
VALUES (1, 'Otavalo sn y principal', 35, 'MALE', '1515151515', 'Jose Lema', '098254785'),
       (2, 'Amazonas y NNUU', 35, 'FEMALE', '1515151544', 'Marianela Montalvo', '097548965'),
       (3, 'Otavalo sn y principal', 35, 'MALE', '151515165', 'Juan Osorio', '098874587');

INSERT INTO client (client_id, password, status) 
VALUES (1, '1234', true),
       (2, '5678', true),
       (3, '1245', true);
