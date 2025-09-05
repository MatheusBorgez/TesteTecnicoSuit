INSERT INTO costumer (name, credit_limit, vip) VALUES ( 'João Silva', 2500.00, false);
INSERT INTO costumer (name, credit_limit, vip) VALUES ( 'Maria Souza', 2000.00, false);
INSERT INTO costumer (name, credit_limit, vip) VALUES ( 'Carlos Lima', 10000.00, true);


INSERT INTO credit_limit_history (customer_id, old_limit, new_limit, changed_by, changed_at)
VALUES (1, 1000.00, 1500.00, 'admin', '2024-09-01T10:00:00');

INSERT INTO credit_limit_history (customer_id, old_limit, new_limit, changed_by, changed_at)
VALUES ( 1, 1500.00, 2500.00, 'sistema', '2024-09-02T11:30:00');
