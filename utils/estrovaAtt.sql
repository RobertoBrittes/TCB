DROP SCHEMA IF EXISTS estrovaRun;
CREATE SCHEMA estrovaRun DEFAULT CHARACTER SET utf8;
USE estrovaRun;

-- ============================
--  PESSOA (classe mãe)
-- ============================
CREATE TABLE pessoa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(45) NOT NULL,
    email VARCHAR(100) NOT NULL,
    data_nasc DATE NOT NULL
);

-- ============================
--  TREINADOR (subclasse)
-- ============================
CREATE TABLE treinador (
    id INT PRIMARY KEY,   -- mesmo id da tabela pessoa
    cref VARCHAR(10) NOT NULL,
    FOREIGN KEY (id) REFERENCES pessoa(id)
);

-- ============================
--  ALUNO (subclasse)
-- ============================
CREATE TABLE aluno (
    id INT PRIMARY KEY,   -- mesmo id da tabela pessoa
    plano_id INT NULL,
    FOREIGN KEY (id) REFERENCES pessoa(id)
);

-- ============================
--  PLANO DE TREINO
-- ============================
CREATE TABLE plano_treino (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dist_max_treino_longo FLOAT NOT NULL,
    duracao_plano INT NOT NULL,
    qtd_treino_semanal INT NOT NULL,
    treinador_id INT NOT NULL,
    is_ativo BOOLEAN NOT NULL,
    FOREIGN KEY (treinador_id) REFERENCES treinador(id)
);

-- aluno.aponta para plano_treino agora
ALTER TABLE aluno
    ADD CONSTRAINT fk_aluno_plano FOREIGN KEY (plano_id) 
        REFERENCES plano_treino(id);

-- ============================
--  TREINO
-- ============================
CREATE TABLE treino (
    id INT AUTO_INCREMENT PRIMARY KEY,
    duracao VARCHAR(45) NOT NULL,
    tipo VARCHAR(45) NOT NULL,
    descricao VARCHAR(300) NOT NULL
);

-- ============================
--  PLANO x TREINO  (N:N)
-- ============================
CREATE TABLE plano_treino_tem_treino (
    id INT AUTO_INCREMENT PRIMARY KEY,
    plano_id INT NOT NULL,
    treino_id INT NOT NULL,
    dia_semana ENUM('segunda', 'terca', 'quarta', 'quinta', 'sexta', 'sabado', 'domingo') NOT NULL,
    UNIQUE (plano_id, dia_semana),
    FOREIGN KEY (plano_id) REFERENCES plano_treino(id),
    FOREIGN KEY (treino_id) REFERENCES treino(id)
);

-- ============================
--  USUÁRIO (para login)
-- ============================
CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    tipo_usuario ENUM('aluno', 'treinador') NOT NULL,
    pessoa_id INT NOT NULL,

    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id),

    CHECK (
        tipo_usuario IN ('aluno', 'treinador')
    )
);
