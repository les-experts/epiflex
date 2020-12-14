DROP TABLE Product;
DROP TABLE Category;
DROP TABLE User;
DROP TABLE Role;
DROP TABLE Message;
DROP TABLE Comment;


CREATE TABLE Product (
  PRO_id INTEGER PRIMARY KEY,
  PRO_title VARCHAR(30),
  PRO_description TEXT,
  USR_id,
  PRO_price FLOAT,
  CAT_id,
  PRO_date DATE,
  FOREIGN KEY(USR_id) REFERENCES User(USR_id),
  FOREIGN KEY(CAT_id) REFERENCES Category(CAT_id));

CREATE TABLE Category (
  CAT_id INTEGER PRIMARY KEY,
  CAT_label VARCHAR(30));

CREATE TABLE User (
  USR_id INTEGER PRIMARY KEY,
  ROL_id,
  USR_lastname VARCHAR(30),
  USR_firstname VARCHAR(30),
  USR_email VARCHAR(30),
  USR_address VARCHAR(50),
  USR_password VARCHAR(30),
  USR_pseudo VARCHAR(30),
  FOREIGN KEY(ROL_id) REFERENCES Role(ROL_id));

CREATE TABLE Role (
  ROL_id INTEGER PRIMARY KEY,
  ROL_label VARCHAR(30));

CREATE TABLE Message (
  MSG_id INTEGER PRIMARY KEY,
  USR_sender,
  USR_receiver,
  MSG_content TEXT,
  MSG_date DATE,
  FOREIGN KEY(USR_sender) REFERENCES User(USR_id),
  FOREIGN KEY(USR_receiver) REFERENCES User(USR_id));

CREATE TABLE Comment (
  COM_id INTEGER PRIMARY KEY,
  PRO_id,
  USR_id,
  COM_rating INTEGER,
  COM_content TEXT,
  COM_date DATE,
  FOREIGN KEY(PRO_id) REFERENCES Product(PRO_id),
  FOREIGN KEY(USR_id) REFERENCES User(USR_id));
