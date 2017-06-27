create database notebook;

use notebook;

create table notebook (
	recordID int NOT NULL AUTO_INCREMENT, 
	recordText varchar(255) NOT NULL,
	author varchar(255),
	title varchar(255),
	recordType varchar(255),
	deadline date,
	crearedDate date NOT NULL,
	updatedDate date NOT NULL,
	PRIMARY KEY (recordID)
);