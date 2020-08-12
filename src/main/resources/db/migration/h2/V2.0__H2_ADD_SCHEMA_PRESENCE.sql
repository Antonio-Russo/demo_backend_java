CREATE TABLE presence (
    id BIGINT auto_increment NOT NULL,
    id_telegram BIGINT NOT NULL,
	data_creation DATETIME2 NOT NULL,
    name NVARCHAR(256) NOT NULL,
	date_of_presence DATETIME2 NOT NULL,
	working_hours SMALLINT NOT NULL,
	CONSTRAINT PK_PRESENCE PRIMARY KEY (id_telegram, date_of_presence)
);

insert into presence (id_telegram,data_creation,name,date_of_presence,working_hours)
values (111,CURRENT_TIMESTAMP(),'antonio','20200812',8);

insert into presence (id_telegram,data_creation,name,date_of_presence,working_hours)
values (111,CURRENT_TIMESTAMP(),'antonio','20200813',6);

insert into presence (id_telegram,data_creation,name,date_of_presence,working_hours)
values (222,CURRENT_TIMESTAMP(),'lorenzo','20200813',8);