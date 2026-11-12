CREATE DATABASE ctcontab;
USE ctcontab;              

CREATE TABLE usuarios (          
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    cargo VARCHAR(50) NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    idioma VARCHAR(10) DEFAULT 'pt_BR',
    telefone VARCHAR(30) NULL,
    Imagem BLOB,
    Permissao ENUM('NULL', 'ADM', 'SUPER_ADM') NOT NULL DEFAULT 'NULL',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    ultimo_acesso DATETIME NULL,
    CHECK (usuario REGEXP '^[a-zA-Z0-9]+$'),
    CHECK (senha REGEXP '^[^[:space:]]+$'),
    CHECK (cargo REGEXP '^[a-zA-Z]+$')
);

CREATE TABLE cliente (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    DataCadastro VARCHAR(255) NOT NULL,
    Nome VARCHAR(255) NOT NULL,
    TipoPessoa ENUM ('Fisica','Juridica','NI') NOT NULL,
    Email VARCHAR(255) NOT NULL,
    Servico TEXT NOT NULL,
    SituacaoServico ENUM ('Pendente','Em andamento','Concluido'),
    Celular VARCHAR(255) NOT NULL,
    Telefone VARCHAR (255) NOT NULL,
    Observacoes VARCHAR (555),
    usuario VARCHAR(255) NOT NULL,
    CONSTRAINT fk_usuarioCadastrante FOREIGN KEY (usuario) REFERENCES usuarios(usuario) ON UPDATE CASCADE
);

CREATE TABLE tarefa (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    NomeTarefa VARCHAR(255) NOT NULL,
    Descrição TEXT NOT NULL,
    StatusTarefa ENUM ('Pendente','Em andamento','Concluido') NOT NULL,
    DataCadastro DATE NOT NULL,
    Prioridade ENUM ('baixa','media','alta') NOT NULL,
    responsavel VARCHAR(255) NOT NULL,
    CONSTRAINT fk_responsavel FOREIGN KEY (responsavel) REFERENCES usuarios(usuario) ON UPDATE CASCADE
);

CREATE TABLE relatorio (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    NomeRelatorio VARCHAR(255) NOT NULL,
    Descrição TEXT NOT NULL,
    StatusRelatorio ENUM ('Pendente','Em andamento','Concluido') NOT NULL,
    DataCadastro DATE NOT NULL,
    usuario VARCHAR(255) NOT NULL,
    CONSTRAINT fk_relatorio_usuario FOREIGN KEY (usuario) REFERENCES usuarios(usuario) ON UPDATE CASCADE
);

CREATE TABLE agenda (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Evento VARCHAR(255) NOT NULL,
    DataInicio DATE NOT NULL,
    DataFinal DATE NOT NULL,
    HorarioInicial TIME NOT NULL,
    HorarioFinal TIME NOT NULL,
    setor VARCHAR(100) NULL,
    observacoes TEXT NULL;
    usuario VARCHAR(255) NOT NULL,
    google_event_id VARCHAR(255) NULL,
    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    sincronizado_em TIMESTAMP NULL,
    UNIQUE KEY uk_agenda_google_event (google_event_id),
    CONSTRAINT fk_usuario FOREIGN KEY (usuario) REFERENCES usuarios(usuario) ON UPDATE CASCADE
);

CREATE USER 'n8n_agenda'@'%' IDENTIFIED BY 'CTCONTAB-PASSWORD-CALENDAR';
GRANT SELECT, INSERT, UPDATE, DELETE ON ctcontab.agenda TO 'n8n_agenda'@'%';
GRANT SELECT ON ctcontab.usuarios TO 'n8n_agenda'@'%';

CREATE TABLE auditoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(100) NOT NULL,
    acao TEXT NOT NULL,
    data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE notificacoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_login VARCHAR(100),
    mensagem TEXT NOT NULL,
    lida BOOLEAN DEFAULT FALSE,
    data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    INDEX (usuario_login)
);