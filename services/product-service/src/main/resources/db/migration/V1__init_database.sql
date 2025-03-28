CREATE DATABASE IF NOT EXISTS `product_service`;

CREATE TABLE IF NOT EXISTS t_category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS t_product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    available_quantity DOUBLE NOT NULL,
    price DECIMAL(15, 2) NOT NULL,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES t_category(id)
);