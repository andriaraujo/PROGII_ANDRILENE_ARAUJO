CREATE TABLE tb_usuario(
   id_usuario SERIAL PRIMARY KEY,
   id_endereco int,
   id_perfil int,
   nome VARCHAR(50),
   sobrenome VARCHAR(50)
);
CREATE TABLE tb_endereco(
    id_endereco SERIAL PRIMARY KEY,
    logradouro VARCHAR(100),   
    complemento VARCHAR(30),
    bairro VARCHAR(30),
    numero VARCHAR(10),
    cep VARCHAR(25)
);
CREATE TABLE tb_perfil(
    id_perfil SERIAL PRIMARY KEY,
    nome VARCHAR(50),
    descricao VARCHAR(50)
);

alter table tb_usuario
ADD foreign key (id_endereco) references tb_endereco(id_endereco);

alter table tb_usuario
ADD foreign key (id_perfil) references tb_perfil(id_perfil);