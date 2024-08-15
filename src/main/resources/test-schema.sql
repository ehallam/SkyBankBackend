DROP TABLE IF EXISTS `customer` CASCADE;
DROP TABLE IF EXISTS `payee` CASCADE;
DROP TABLE IF EXISTS `transactions` CASCADE;

CREATE TABLE `customer` (
    `first_name` VARCHAR(255),
    `last_name` VARCHAR(255),
    `email` VARCHAR(255) PRIMARY KEY,
    `password` VARCHAR(255),
    `sort_code` INTEGER,
    `account_number` INTEGER,
    `balance` DOUBLE
);

CREATE TABLE `payee` (
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(255),
    `last_name` VARCHAR(255),
    `sort_code` INTEGER,
    `account_number` INTEGER,
    `customer_email` VARCHAR(255),
    FOREIGN KEY (`customer_email`) REFERENCES `customer` (`email`) ON DELETE CASCADE
);

CREATE TABLE `transactions` (
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `description` VARCHAR(255),
    `transaction_date` DATE,
    `amount_in` DOUBLE,
    `amount_out` DOUBLE,
    `customer_email` VARCHAR(255),
    `payee_account_number` INTEGER,
    `payee_sort_code` INTEGER,
    FOREIGN KEY (`customer_email`) REFERENCES `customer` (`email`) ON DELETE CASCADE
)