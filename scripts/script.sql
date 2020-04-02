CREATE SCHEMA EXEMPLOS;
USE EXEMPLOS;

CREATE TABLE TELEFONE (
  id INT NOT NULL AUTO_INCREMENT,
  codigoPais VARCHAR(2) NOT NULL,
  ddd VARCHAR(2) NOT NULL,
  numero VARCHAR(12) NOT NULL,
  movel INT(1) NOT NULL,
  ativo INT(1) NOT NULL,
  PRIMARY KEY (id));
  
  CREATE TABLE ENDERECO (
  id INT NOT NULL AUTO_INCREMENT,
  cep VARCHAR(8) NOT NULL,
  estado VARCHAR(255) NOT NULL,
  cidade VARCHAR(255) NOT NULL,
  bairro VARCHAR(255) NOT NULL,
  numero VARCHAR(100) NOT NULL,
  PRIMARY KEY (id));
  
ALTER TABLE ENDERECO 
ADD COLUMN rua VARCHAR(255) NOT NULL AFTER numero;

  
  CREATE TABLE CLIENTE (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  sobrenome VARCHAR(255) NOT NULL,
  cpf VARCHAR(11) NOT NULL,
  idENDERECO INT NOT NULL,
  PRIMARY KEY (id));

ALTER TABLE CLIENTE 
ADD INDEX id_idx (idENDERECO ASC);
ALTER TABLE CLIENTE 
ADD CONSTRAINT id
  FOREIGN KEY (idENDERECO)
  REFERENCES ENDERECO (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE TELEFONE 
ADD COLUMN idCLIENTE INT NULL AFTER ativo;

INSERT INTO ENDERECO (id,cep,estado,cidade,bairro,numero,rua) 
VALUES (1,'43294870','SC','São José','Centro','189','Rua 51');

ALTER TABLE CLIENTE ADD UNIQUE INDEX cpf_UNIQUE (cpf ASC);


INSERT INTO ENDERECO (id,cep,estado,cidade,bairro,numero,rua) VALUES (2,'38476242','SC','Lages','Centro','515','Rua  79');
INSERT INTO ENDERECO (id,cep,estado,cidade,bairro,numero,rua) VALUES (3,'78529663','SC','Florianópolis','Centro','804','Rua  81');
INSERT INTO ENDERECO (id,cep,estado,cidade,bairro,numero,rua) VALUES (4,'39152270','SC','Lages','Centro','640','Rua 28');
INSERT INTO ENDERECO (id,cep,estado,cidade,bairro,numero,rua) VALUES (5,'78015242','SC','Florianópolis','Centro','126','Rua  6');
INSERT INTO ENDERECO (id,cep,estado,cidade,bairro,numero,rua) VALUES (6,'41857751','SC','Chapecó','Centro','125','Rua 66');

INSERT INTO TELEFONE (id, codigopais, ddd, numero, movel, ativo, idcliente) VALUES (default, '55', '48','997788888', 1, 1, 1);
INSERT INTO TELEFONE (id, codigopais, ddd, numero, movel, ativo, idcliente) VALUES (default, '53', '47','997788895', 1, 0, 0);
INSERT INTO TELEFONE (id, codigopais, ddd, numero, movel, ativo, idcliente) VALUES (default, '53', '47','997788884', 1, 1, 2);
INSERT INTO TELEFONE (id, codigopais, ddd, numero, movel, ativo, idcliente) VALUES (default, '55', '41','997788885', 1, 1, 3);
INSERT INTO TELEFONE (id, codigopais, ddd, numero, movel, ativo, idcliente) VALUES (default, '53', '47','996488823', 1, 0, 0);

INSERT INTO CLIENTE (nome, sobrenome, cpf, idENDERECO) VALUES ('Arthur', 'Martins', '12345678901', 1);
INSERT INTO CLIENTE (nome, sobrenome, cpf, idENDERECO) VALUES ('Rodrigo', 'Mello', '12345678902', 2);
INSERT INTO CLIENTE (nome, sobrenome, cpf, idENDERECO) VALUES ('Adriano', 'Rosseto', '12345678903', 3);
INSERT INTO CLIENTE (nome, sobrenome, cpf, idENDERECO) VALUES ('Vitor', 'Ribeiro', '12345678904', 4);
INSERT INTO CLIENTE (nome, sobrenome, cpf, idENDERECO) VALUES ('Gustavo', 'Rodrigues', '12345678905', 5);

