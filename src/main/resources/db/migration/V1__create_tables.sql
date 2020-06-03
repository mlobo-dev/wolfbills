DROP TABLE IF EXISTS tbl_usuario CASCADE;
CREATE TABLE tbl_usuario(
    COD_USUARIO bigint GENERATED BY DEFAULT AS IDENTITY primary key,
    NOME VARCHAR(255),
    CPF VARCHAR(255),
    EMAIL VARCHAR(255),
    SENHA VARCHAR(255),
    LOGIN VARCHAR(255),
    PERFIL VARCHAR(255)
);

DROP TABLE IF EXISTS tbl_lancamento CASCADE;
CREATE TABLE tbl_lancamento(
    cod_lancamento bigint GENERATED BY DEFAULT AS IDENTITY primary key,
    descricao VARCHAR(255),
    valor VARCHAR(255),
    tipo VARCHAR(255),
    status VARCHAR(255),
    data_cadastro TIMESTAMP,
    data_vencimento TIMESTAMP,
    cod_usuario bigint,
    foreign key (COD_USUARIO) references tbl_usuario(COD_USUARIO)
);

DROP TABLE IF EXISTS tbl_folha_faturamento CASCADE;
CREATE TABLE tbl_folha_faturamento(
    cod_folha_faturamento bigint GENERATED BY DEFAULT AS IDENTITY primary key,
    mes VARCHAR(255),
    status VARCHAR(255),
    tipo VARCHAR(255),
    cod_usuario bigint,
    foreign key (COD_USUARIO) references tbl_usuario(COD_USUARIO)
);

drop table if exists tbl_folha_lancamentos;
create table tbl_folha_lancamentos(
    cod_folha_faturamento bigint,
    cod_lancamento bigint,
    foreign key (cod_folha_faturamento) references tbl_folha_faturamento(cod_folha_faturamento),
    foreign key (cod_lancamento) references tbl_lancamento(cod_lancamento)
);