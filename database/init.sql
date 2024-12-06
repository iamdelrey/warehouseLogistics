CREATE SCHEMA IF NOT EXISTS inventory;

CREATE TABLE IF NOT EXISTS inventory.products (
                                                  id SERIAL PRIMARY KEY,
                                                  name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    price NUMERIC(10, 2) NOT NULL
    );

CREATE SCHEMA IF NOT EXISTS orders;

CREATE TABLE IF NOT EXISTS orders.order_details (
                                                    id SERIAL PRIMARY KEY,
                                                    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                    status VARCHAR(50) NOT NULL
    );

CREATE SCHEMA IF NOT EXISTS users;

CREATE TABLE IF NOT EXISTS users.accounts (
                                              id SERIAL PRIMARY KEY,
                                              username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
    );
