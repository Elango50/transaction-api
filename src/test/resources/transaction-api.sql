--This script is used for unit test cases, DO NOT CHANGE!

DROP TABLE IF EXISTS User;

CREATE TABLE User (UserId LONG PRIMARY KEY AUTO_INCREMENT NOT NULL,
 UserName VARCHAR(30) NOT NULL,
 EmailAddress VARCHAR(30) NOT NULL);

CREATE UNIQUE INDEX idx_ue on User(UserName,EmailAddress);

INSERT INTO User (UserName, EmailAddress) VALUES ('test1','test1@gmail.com');
INSERT INTO User (UserName, EmailAddress) VALUES ('test2','test2@gmail.com');
INSERT INTO User (UserName, EmailAddress) VALUES ('test3','test3@gmail.com');

DROP TABLE IF EXISTS Account;

CREATE TABLE Account (AccountId LONG PRIMARY KEY AUTO_INCREMENT NOT NULL,
UserName VARCHAR(30),
Balance DECIMAL(19,4),
CurrencyCode VARCHAR(30)
);

CREATE UNIQUE INDEX idx_acc on Account(UserName,CurrencyCode);

INSERT INTO Account (UserName,Balance,CurrencyCode) VALUES ('test1',100.00,'USD');
INSERT INTO Account (UserName,Balance,CurrencyCode) VALUES ('test2',200.00,'USD');
INSERT INTO Account (UserName,Balance,CurrencyCode) VALUES ('test1',500.00,'EUR');
INSERT INTO Account (UserName,Balance,CurrencyCode) VALUES ('test2',500.00,'EUR');
INSERT INTO Account (UserName,Balance,CurrencyCode) VALUES ('test1',500.00,'GBP');
INSERT INTO Account (UserName,Balance,CurrencyCode) VALUES ('test2',500.00,'GBP');
