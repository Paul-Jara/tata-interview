-- Precarga de datos para account-service
INSERT INTO client_replica (client_id, name)
VALUES (1, 'Jose Lema'),
       (2, 'Marianela Montalvo'),
       (3, 'Juan Osorio');

-- Cuentas
INSERT INTO account (account_number, account_type, initial_balance, status, client_id)
VALUES ('478758', 'SAVINGS', 2000.00, true, 1),
       ('225487', 'CHECKING', 100.00, true, 2),
       ('784514', 'SAVINGS', 0.00, true, 3),
       ('496825', 'SAVINGS', 540.00, true, 2);

-- Movimientos
INSERT INTO movement (movement_id, movement_type, amount, balance, date, account_number)
VALUES (1, 'CREDIT', 300.00, 1000.00, PARSEDATETIME('10/02/2025', 'MM/dd/yyyy'), '478758'),
       (2, 'DEBIT', -60.00, 1300.00, PARSEDATETIME('12/25/2025', 'MM/dd/yyyy'), '478758'),
       (3, 'CREDIT', 760.00, 1240.00, PARSEDATETIME('01/22/2026', 'MM/dd/yyyy'), '478758'),
       (4, 'DEBIT', -260.00, 1740.00, CURRENT_TIMESTAMP, '478758'),
       (5, 'DEBIT', -60.00, 70.00, PARSEDATETIME('09/20/2025', 'MM/dd/yyyy'), '225487'),
       (6, 'DEBIT', -10.00, 10.00, PARSEDATETIME('12/05/2025', 'MM/dd/yyyy'), '225487'),
       (7, 'CREDIT', 50.00, 0.00, CURRENT_TIMESTAMP, '225487'),
       (8, 'CREDIT', 50.00, 50.00, PARSEDATETIME('02/03/2026', 'MM/dd/yyyy'), '225487'),
       (9, 'CREDIT', 500.00, 200.00, PARSEDATETIME('08/05/2025', 'MM/dd/yyyy'), '784514'),
       (10, 'DEBIT', -400.00, 700.00, CURRENT_TIMESTAMP, '784514'),
       (11, 'DEBIT', -300.00, 300.00, PARSEDATETIME('02/01/2026', 'MM/dd/yyyy'), '784514'),
       (12, 'CREDIT', 60.00, 480.00, PARSEDATETIME('12/03/2025', 'MM/dd/yyyy'), '496825');

-- Sincronizar la secuencia de H2 con el id máximo insertado (12 + 1)
ALTER TABLE movement ALTER COLUMN movement_id RESTART WITH 13;
