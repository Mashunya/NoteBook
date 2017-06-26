CREATE DATABASE IF NOT EXISTS test_notebook;

USE test_notebook;

SET AUTOCOMMIT = 0;

BEGIN;

CREATE TABLE IF NOT EXISTS notebook (
	recordID INT NOT NULL AUTO_INCREMENT,
	recordText VARCHAR (255) NOT NULL,
	author VARCHAR (255),
	title VARCHAR (255),
	recordType VARCHAR (255),
	deadline DATE ,
	createdDate DATE NOT NULL,
	updatedDate DATE NOT NULL,
	PRIMARY KEY (recordID)
);

INSERT INTO notebook(recordText, author, title, recordType, deadline, createdDate, updatedDate)
VALUES ('test_text_1', 'test_author_1', 'test_title_1', 'test_type_1', '2017-09-30', '2017-06-26', '2017-06-26');

INSERT INTO notebook(recordText, author, title, recordType, deadline, createdDate, updatedDate)
VALUES ('test_text_2', 'test_author_2', 'test_title_2', 'test_type_2', '2017-09-30', '2017-06-26', '2017-06-26');

INSERT INTO notebook(recordText, author, title, recordType, deadline, createdDate, updatedDate)
VALUES ('test_text_3', 'test_author_3', 'test_title_3', 'test_type_3', '2017-09-30', '2017-06-26', '2017-06-26');

COMMIT;
