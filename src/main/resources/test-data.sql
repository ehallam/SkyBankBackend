INSERT INTO `customer` (`first_name`, `last_name`, `email`, `password`, `sort_code`, `account_number`, `balance`)
VALUES
    ('John', 'Johnson', 'john@email.com', 'testPassword', '654321', '87654321', 200);


INSERT INTO `payee` (`first_name`, `last_name`, `sort_code`, `account_number`, `customer_email`)
VALUES
    ('Sarah', 'Smith', '132456', '13245768', 'john@email.com');

