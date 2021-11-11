CREATE DATABASE BDFOTOLITO;

CREATE USER 'carton'@'%' IDENTIFIED BY '2574';

GRANT ALL ON *.* TO 'carton'@'%' WITH GRANT OPTION;


flush privileges;


USE BDFOTOLITO;

/***** TABELA FILME DE FACA *****/
CREATE TABLE tb_filme_faca (
  id int auto_increment primary key,
  rp varchar (15),
  cliente varchar (30),
  fichaTecnica int (6),
  setor varchar (30),
  turno varchar (10),
  ocorrencia varchar (30),
  obs varchar (100)
);

/***** TABELA FILME DE HOT *****/
CREATE TABLE tb_filme_hot (
  id int auto_increment primary key,
  rp varchar (15),
  cliente varchar (30),
  fichaTecnica int (6),
  faca varchar (15),
  turno varchar (10),
  ocorrencia varchar (30),
  obs varchar (100)
  );
  
  /***** TABELA CLIENTES *****/
CREATE TABLE tb_clientes (
  id int auto_increment primary key,
  nomeCliente varchar (30)
  );
  
  /***** TABELA SETOR *****/
CREATE TABLE tb_setor (
  id int auto_increment primary key,
  nomeSetor varchar (30)
  );
  
  /***** TABELA TURNO *****/
CREATE TABLE tb_turno (
  id int auto_increment primary key,
  nomeTurno varchar (10)
  );