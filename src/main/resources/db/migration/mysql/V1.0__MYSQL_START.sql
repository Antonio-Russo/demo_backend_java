CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username NVARCHAR(256) NOT NULL UNIQUE,
    password NVARCHAR(256) NOT NULL,
    CONSTRAINT category_pk PRIMARY KEY (id)
);

insert into users (username,password)
        values ('antonio','TuNnmJLmrFpbUT66f9Up02PXqWUIQhxdvUSwGzSc9RQ=')