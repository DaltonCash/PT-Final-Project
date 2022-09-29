DROP TABLE IF EXISTS orders_art;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS art;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS sellers;


CREATE TABLE users (
  user_id int NOT NULL AUTO_INCREMENT,
  user_name varchar(40) NOT NULL,
  user_password varchar(20) NOT NULL,
  first_name varchar(45) NOT NULL, 
  last_name varchar(45) NOT NULL,
  email varchar(50) NOT NULL,
  PRIMARY KEY (user_id)
);

CREATE TABLE sellers (
	seller_id int NOT NULL AUTO_INCREMENT,
    seller_name varchar(40) NOT NULL,
    seller_password varchar(20) NOT NULL,
    first_name varchar(45) NOT NULL,
    last_name varchar(45) NOT NULL,
    email varchar(50) NOT NULL,
    PRIMARY KEY (seller_id)
    );

CREATE TABLE art (
  art_id int NOT NULL AUTO_INCREMENT,
  seller_id int NOT NULL,
  title varchar(256) NOT NULL,
  artist_name varchar(50) NOT NULL,
  art_period varchar (100) NOT NULL,
  art_medium varchar(50) NOT NULL,
  creation_year int,
  art_stock int NOT NULL CHECK (art_stock >= 0),
  price decimal(9, 2) NOT NULL,
  PRIMARY KEY (art_id),
  FOREIGN KEY (seller_id) REFERENCES sellers (seller_id) ON DELETE CASCADE
);

CREATE TABLE orders (
  order_id int NOT NULL AUTO_INCREMENT,
  user_id int NOT NULL,
  price decimal(9, 2) DEFAULT 0.00,
  order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  message varchar(200) DEFAULT 'Default Message',
  PRIMARY KEY (order_id),
  FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE orders_art (
orders_art_id INT NOT NULL AUTO_INCREMENT,
art_id INT NOT NULL,
order_id INT NOT NULL,
PRIMARY KEY (orders_art_id),
FOREIGN KEY (art_id) REFERENCES art (art_id) ON DELETE CASCADE,
FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE
);