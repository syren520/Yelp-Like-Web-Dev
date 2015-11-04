CREATE TABLE business (
  businessid VARCHAR(50) NOT NULL,
  state VARCHAR(30) NOT NULL,
  city VARCHAR(30) NOT NULL,
  type VARCHAR(100) NOT NULL,
  businessname VARCHAR(100) NOT NULL,
  addresss VARCHAR(200) NOT NULL,
  latitude FLOAT NOT NULL,
  longtitude FLOAT NOT NULL,
  PRIMARY KEY (businessid));