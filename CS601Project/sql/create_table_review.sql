CREATE TABLE review (
  reviewid INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  businessid VARCHAR(50) NOT NULL,
  rating ENUM('1', '2', '3', '4', '5') NOT NULL,
  description TEXT NOT NULL,
  imagepath VARCHAR(100) NULL,
  PRIMARY KEY (reviewid));