DROP SCHEMA IF EXISTS estrovarun;
CREATE SCHEMA estrovaRun DEFAULT CHARACTER SET utf8;
USE estrovaRun;

-- TREINADOR
CREATE TABLE treinador (
    cref VARCHAR(45) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(45) NOT NULL,
    email VARCHAR(100) NOT NULL,
    data_nasc DATE NOT NULL
);

-- PLANO DE TREINO
CREATE TABLE plano_treino (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dist_max_treino_longo FLOAT NOT NULL,
    duracao_plano INT NOT NULL,
    qtd_treino_semanal INT NOT NULL,
    treinador_cref VARCHAR(45) NOT NULL,
    is_ativo TINYINT NOT NULL,
    FOREIGN KEY (treinador_cref) REFERENCES treinador(cref)
);

-- ALUNO
CREATE TABLE aluno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(45) NOT NULL,
    email VARCHAR(100) NOT NULL,
    data_nasc DATE NOT NULL,
    plano_id INT NOT NULL,
    FOREIGN KEY (plano_id) REFERENCES plano_treino(id)
);

-- TREINO
CREATE TABLE treino (
    id INT AUTO_INCREMENT PRIMARY KEY,
    duracao VARCHAR(45) NOT NULL,
    tipo VARCHAR(45) NOT NULL,
    descricao VARCHAR(300) NOT NULL
);

-- RELAÇÃO PLANO x TREINO (N:N)
CREATE TABLE plano_treino_tem_treino (
    id INT AUTO_INCREMENT PRIMARY KEY,
    plano_id INT NOT NULL,
    treino_id INT NOT NULL,
    dia_semana ENUM('segunda', 'terca', 'quarta', 'quinta', 'sexta', 'sabado', 'domingo') NOT NULL,
    UNIQUE (plano_id, dia_semana),
    FOREIGN KEY (plano_id) REFERENCES plano_treino(id),
    FOREIGN KEY (treino_id) REFERENCES treino(id)
);
