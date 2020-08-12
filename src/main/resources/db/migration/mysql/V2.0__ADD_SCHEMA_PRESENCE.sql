CREATE TABLE presence (
    id BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
    id_telegram BIGINT NOT NULL,
	data_creation DATETIME NOT NULL COLLATE 'utf8_general_ci',
    name NVARCHAR(256) NOT NULL,
	date_of_presence DATE NOT NULL COLLATE 'utf8_general_ci',
	working_hours TINYINT NOT NULL,
    CONSTRAINT presence_pk PRIMARY KEY (id_telegram,date_of_presence)
);