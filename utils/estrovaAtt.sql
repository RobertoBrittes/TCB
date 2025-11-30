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
    data_nasc DATE NOT NULL
);

-- ============================
--  TREINADOR (subclasse)
-- ============================
CREATE TABLE treinador (
    id INT PRIMARY KEY,   -- mesmo id da tabela pessoa
    cref VARCHAR(10) UNIQUE NOT NULL,
    FOREIGN KEY (id) REFERENCES pessoa(id) ON DELETE CASCADE
);

-- ============================
--  ALUNO (subclasse)
-- ============================
CREATE TABLE aluno (
    id INT PRIMARY KEY,   -- mesmo id da tabela pessoa
    treinador_id INT,
    FOREIGN KEY (id) REFERENCES pessoa(id) ON DELETE CASCADE,
    FOREIGN KEY (treinador_id) REFERENCES treinador(id) ON DELETE SET NULL
);

-- ============================
--  PLANO DE TREINO
-- ============================
CREATE TABLE plano_treino (
    id INT AUTO_INCREMENT PRIMARY KEY,
    aluno_id INT NOT NULL,
    treinador_id INT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    objetivo VARCHAR(100) NOT NULL,
    descricao VARCHAR(300),
    dataInicio DATE NOT NULL,
    dataFim DATE NOT NULL,
    duracao_plano INT NOT NULL,
    qtd_treino_semanal INT NOT NULL,
    is_ativo BOOLEAN NOT NULL,
    FOREIGN KEY (aluno_id) REFERENCES aluno(id) ON DELETE CASCADE,
    FOREIGN KEY (treinador_id) REFERENCES treinador(id) ON DELETE CASCADE
);

-- ============================
--  TREINO (treinos-base reutilizáveis)
-- ============================
CREATE TABLE treino (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    duracao VARCHAR(45) NOT NULL,
    tipo ENUM('longão', 'regenerativo', 'tiro', 'fartlek', 'progressivo', 'rodagem') NOT NULL,
    descricao VARCHAR(300) NOT 
    isTreinoPronto BOOLEAN NOT NULL
);

-- ============================
--  RELAÇÃO PLANO x TREINO  (N:N)
-- ============================
CREATE TABLE plano_treino_tem_treino (
    id INT AUTO_INCREMENT PRIMARY KEY,
    plano_id INT NOT NULL,
    treino_id INT NOT NULL,
    dia_semana ENUM('segunda','terca','quarta','quinta','sexta','sabado','domingo') NOT NULL,
    UNIQUE (plano_id, dia_semana),
    FOREIGN KEY (plano_id) REFERENCES plano_treino(id) ON DELETE CASCADE,
    FOREIGN KEY (treino_id) REFERENCES treino(id)
);

-- ============================
--  USUÁRIO (para login)
-- ============================
CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    tipo_usuario ENUM('aluno','treinador') NOT NULL,
    pessoa_id INT NOT NULL,
    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id) ON DELETE CASCADE,
    CHECK (tipo_usuario IN ('aluno','treinador'))
);
