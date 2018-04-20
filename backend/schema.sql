CREATE SCHEMA IF NOT EXISTS kekker;

CREATE TABLE IF NOT EXISTS kekker.users(
  nickName VARCHAR(40) PRIMARY KEY ,
  email VARCHAR(100) NOT NULL ,
  password VARCHAR(255) NOT NULL ,
  firstName VARCHAR(120) NOT NULL ,
  lastName VARCHAR(120) NOT NULL ,
  avatar BYTEA NOT NULL ,
  bio VARCHAR(120) NOT NULL ,
  gender VARCHAR(15) NOT NULL

);

CREATE TABLE IF NOT EXISTS kekker.keks(
  Id INT PRIMARY KEY ,
  text VARCHAR(200),
  image BYTEA NULL,
  createDate TIMESTAMP NOT NULL ,
  owner VARCHAR(40) REFERENCES kekker.users(nickName)/*Meybe add FOREGIN KEY*/
);

CREATE TABLE IF NOT EXISTS kekker.reactions(
  id INT PRIMARY KEY ,
  type VARCHAR(50) NOT NULL ,
  user_id VARCHAR(40) REFERENCES kekker.users(nickName),
  kek_id INT REFERENCES kekker.keks(Id)/*Meybe add FOREGIN KEY*/
);

CREATE TABLE IF NOT EXISTS kekker.comments(
  id INT PRIMARY KEY ,
  text VARCHAR(150) NOT NULL ,
  creationDate TIMESTAMP NOT NULL ,
  owner_id VARCHAR(40) REFERENCES kekker.users(nickName) NOT NULL,
  FOREIGN KEY (kek_id) REFERENCES kekker.keks(Id)/*Meybe add FOREGIN KEY*/

);