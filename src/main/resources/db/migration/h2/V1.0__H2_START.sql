CREATE TABLE users (
    id BIGINT NOT NULL IDENTITY(1, 1),
    username NVARCHAR(256) NOT NULL UNIQUE,
    password NVARCHAR(256) NOT NULL,
    CONSTRAINT category_pk PRIMARY KEY (id)
);

insert into users (username,password)
    values ('antonioTest','TuNnmJLmrFpbUT66f9Up02PXqWUIQhxdvUSwGzSc9RQ=')