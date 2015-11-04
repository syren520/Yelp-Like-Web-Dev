CREATE TABLE user (
  userid  INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  PRIMARY KEY (userid),
  UNIQUE (username),
  UNIQUE (email));