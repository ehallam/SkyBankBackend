DROP TABLE IF EXISTS `customer` CASCADE;
DROP TABLE IF EXISTS `payee` CASCADE;
DROP TABLE IF EXISTS `bank_transaction` CASCADE;

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
    `first_name` VARCHAR(255),
    `last_name` VARCHAR(255),
    `sort_code` INTEGER,
    `account_number` INTEGER,
    `customer_email` VARCHAR(255),
    FOREIGN KEY (`customer_email`) REFERENCES `customer` (`email`) ON DELETE CASCADE,
    PRIMARY KEY (`account_number`, `sort_code`)
);
