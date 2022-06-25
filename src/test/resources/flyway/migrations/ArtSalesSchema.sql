DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS art;
DROP TABLE IF EXISTS users;




CREATE TABLE users (
  user_id int NOT NULL AUTO_INCREMENT,
  user_name varchar(40) NOT NULL,
  user_password varchar(20) NOT NULL,
  first_name varchar(45) NOT NULL, 
  last_name varchar(45) NOT NULL,
  email varchar(50) NOT NULL,
  PRIMARY KEY (user_id)
);

CREATE TABLE art (
  art_id int NOT NULL AUTO_INCREMENT,
  artist_name varchar(50) NOT NULL,
  art_period varchar (30) NOT NULL,
  art_medium varchar(50) NOT NULL,
  creation_year int NOT NULL,
  art_stock int NOT NULL,
  price decimal(9, 2) NOT NULL,
  title varchar(256),
  PRIMARY KEY (art_id)
);

CREATE TABLE orders (
  order_id int NOT NULL AUTO_INCREMENT,
  user_id int NOT NULL,
  art_id int NOT NULL,
  price decimal(9, 2) NOT NULL,
  order_date TIMESTAMP,
  PRIMARY KEY (order_id),
  FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE

);


  



