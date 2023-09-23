CREATE DATABASE dbprojeto;

USE dbprojeto;

CREATE TABLE tbusuario (
    usuario_pk INT AUTO_INCREMENT PRIMARY KEY,
    nomeUso VARCHAR(60) NOT NULL,
    emailUso VARCHAR(40) NOT NULL UNIQUE,
    data_nascimentoUso varchar(10) NOT NULL,
    senhaUso VARCHAR(30) NOT NULL,
    ativoUso tinyint(1) NOT NULL
);

CREATE TABLE tbtreinos (
    treino_pk INT AUTO_INCREMENT PRIMARY KEY,
    nomeTre VARCHAR(100) NOT NULL,
    descricaoTre TEXT,
    dataTre VARCHAR(10),
    ativoTre TINYINT(1) NOT NULL
);

alter table tbusuario add imagenUso blob(5242880);

