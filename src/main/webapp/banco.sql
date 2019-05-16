CREATE SCHEMA IF NOT EXISTS CMS;
USE CMS;
CREATE TABLE IF NOT EXISTS usuario(
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    usuario VARCHAR(15) NOT NULL,
    senha VARCHAR(15) NOT NULL,
    foto LONGBLOB NOT NULL,
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS teste(
    id INT NOT NULL AUTO_INCREMENT,
    imagem LONGBLOB NOT NULL,
    nome VARCHAR(30) NOT NULL,
    descrisao VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS filmes(
    id INT NOT NULL AUTO_INCREMENT,
    fileI LONGBLOB NOT NULL,
    fileV LONGBLOB NOT NULL,
    nome VARCHAR(30) NOT NULL,
    descrisao VARCHAR(100) NOT NULL,
    genero VARCHAR(15) NOT NULL,
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS home(
    id INT NOT NULL,
    nomeSite VARCHAR(40) NOT NULL,
    imagemFundo LONGBLOB NOT NULL,
    corHeader VARCHAR(10) NOT NULL,
    corFooter VARCHAR(10) NOT NULL,
    PRIMARY KEY(id)
);
