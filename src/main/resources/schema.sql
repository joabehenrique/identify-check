DROP TABLE IF EXISTS cpfs;

create table cpfs (
    id bigserial not null,
    cpf varchar(255),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    primary key (id)
);

-- INSERT INTO cpfs (cpf) VALUES ('987.654.321-09');
-- INSERT INTO cpfs (cpf) VALUES ('111.222.333-44');
-- INSERT INTO cpfs (cpf) VALUES ('555.666.777-88');
-- INSERT INTO cpfs (cpf) VALUES ('999.888.777-66');
-- INSERT INTO cpfs (cpf) VALUES ('444.333.222-11');
-- INSERT INTO cpfs (cpf) VALUES ('000.111.222-33');
-- INSERT INTO cpfs (cpf) VALUES ('777.888.999-00');
-- INSERT INTO cpfs (cpf) VALUES ('222.333.444-55');
-- INSERT INTO cpfs (cpf) VALUES ('666.555.444-33');