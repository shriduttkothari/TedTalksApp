DROP TABLE ted_talk IF EXISTS;

CREATE TABLE ted_talk  (
    ted_talk_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    title VARCHAR(100),
    author VARCHAR(100),
	date VARCHAR(40),
	views VARCHAR(100),
	likes VARCHAR(100),
	link VARCHAR(300)
);