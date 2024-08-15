INSERT INTO `customer` (`first_name`, `last_name`, `email`, `password`, `sort_code`, `account_number`, `balance`)
VALUES
    ('John', 'Johnson', 'john@email.com', 'testPassword', '654321', '87654321', 200),
    ('Bob', 'Smith', 'bob@email.com', 'secure', '654322', '87654322', 200);


INSERT INTO `payee` (`first_name`, `last_name`, `sort_code`, `account_number`, `customer_email`)
VALUES
    ('Sarah', 'Smith', '132456', '13245768', 'john@email.com'),
    ('Charlie', 'Mann', '555555', '88888888', 'john@email.com'),
    ('Eleanor', 'Holden', '22222', '33333333', 'bob@email.com');

