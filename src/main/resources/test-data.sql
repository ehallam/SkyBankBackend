INSERT INTO `customer` (`first_name`, `last_name`, `email`, `password`, `sort_code`, `account_number`, `balance`)
VALUES
    ('John', 'Johnson', 'john@email.com', 'testPassword', '654321', '87654321', 200),
    ('Bob', 'Smith', 'bob@email.com', 'secure', '654322', '87654322', 200);


INSERT INTO `payee` (`first_name`, `last_name`, `sort_code`, `account_number`, `customer_email`)
VALUES
    ('Sarah', 'Smith', '132456', '13245768', 'john@email.com'),
    ('Charlie', 'Mann', '555555', '88888888', 'john@email.com'),
    ('Eleanor', 'Holden', '22222', '33333333', 'bob@email.com');


INSERT INTO `transactions` (`description`, `transaction_date`, `amount_in`, `amount_out`, `customer_email`, `payee_account_number`, `payee_sort_code`)
VALUES
    ('Test transaction 1', '2024-08-15', null, '15.52', 'john@email.com', '13245768', '132456'),
    ('Test transaction 2', '2024-08-15', null, '56', 'bob@email.com', '87654321', '654321'),
    ('Test transaction 3', '2024-08-15', '17', null, 'bob@email.com', '33333333', '22222');



