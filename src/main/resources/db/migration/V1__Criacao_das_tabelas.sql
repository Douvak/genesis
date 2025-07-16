-- V1__create_schema.sql

-- Tabela EMPRESA
CREATE TABLE IF NOT EXISTS empresa (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100),
  cnpj VARCHAR(20) UNIQUE,
  telefone VARCHAR(20),
  email VARCHAR(100),
  endereco VARCHAR(255)
);

-- Tabela USUÁRIOS
CREATE TABLE IF NOT EXISTS usuarios (
  id INT PRIMARY KEY AUTO_INCREMENT,
  empresa_id INT,
  username VARCHAR(50) UNIQUE NOT NULL,
  senha VARCHAR(255) NOT NULL,
  papel ENUM('ADMIN','VENDAS','FINANCEIRO','PRODUCAO') DEFAULT 'VENDAS',
  ativo BOOLEAN DEFAULT TRUE,
  FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);

-- Tabela FUNCIONÁRIOS
CREATE TABLE IF NOT EXISTS funcionarios (
  id INT PRIMARY KEY AUTO_INCREMENT,
  empresa_id INT,
  usuario_id INT UNIQUE,
  nome VARCHAR(100),
  cargo VARCHAR(50),
  email VARCHAR(100),
  data_admissao DATE,
  FOREIGN KEY (empresa_id) REFERENCES empresa(id),
  FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Tabela CLIENTES
CREATE TABLE IF NOT EXISTS clientes (
  id INT PRIMARY KEY AUTO_INCREMENT,
  empresa_id INT,
  nome VARCHAR(100),
  contato VARCHAR(50),
  FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);

-- Tabela PEDIDOS
CREATE TABLE IF NOT EXISTS pedidos (
  id INT PRIMARY KEY AUTO_INCREMENT,
  empresa_id INT,
  cliente_id INT,
  valor_total DECIMAL(10,2),
  previsao_entrega DATE,
  FOREIGN KEY (empresa_id) REFERENCES empresa(id),
  FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

-- Tabela ORDENS DE SERVIÇO
CREATE TABLE IF NOT EXISTS ordens_servico (
  id INT PRIMARY KEY AUTO_INCREMENT,
  empresa_id INT,
  pedido_id INT,
  descricao TEXT,
  status VARCHAR(50),
  valor DECIMAL(10,2),
  arquivos TEXT,
  FOREIGN KEY (empresa_id) REFERENCES empresa(id),
  FOREIGN KEY (pedido_id) REFERENCES pedidos(id)
);

-- Tabela TIPOS DE PAGAMENTO
CREATE TABLE IF NOT EXISTS tipos_pagamento (
  id INT PRIMARY KEY AUTO_INCREMENT,
  empresa_id INT,
  tipo VARCHAR(50),
  valor DECIMAL(10,2),
  status_pagamento VARCHAR(20),
  data_pagamento DATE,
  FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);

-- Tabela RELACIONAL: Pedido x Tipo de Pagamento (N:N)
CREATE TABLE IF NOT EXISTS pedido_tipo_pagamento (
  pedido_id INT,
  tipo_pagamento_id INT,
  PRIMARY KEY (pedido_id, tipo_pagamento_id),
  FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
  FOREIGN KEY (tipo_pagamento_id) REFERENCES tipos_pagamento(id)
);

-- Tabela ETAPAS
CREATE TABLE IF NOT EXISTS etapas (
  id INT PRIMARY KEY AUTO_INCREMENT,
  empresa_id INT,
  ordem_id INT,
  funcionario_id INT,
  tipo_etapa ENUM('MEDIR','CORTE','PRODUCAO','MONTAGEM'),
  inicio DATETIME,
  finalizado BOOLEAN,
  FOREIGN KEY (empresa_id) REFERENCES empresa(id),
  FOREIGN KEY (ordem_id) REFERENCES ordens_servico(id),
  FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id)
);

-- Tabela PAUSADO
CREATE TABLE IF NOT EXISTS pausado (
  id INT PRIMARY KEY AUTO_INCREMENT,
  empresa_id INT,
  ordem_id INT,
  inicio DATETIME,
  finalizado DATETIME,
  motivo TEXT,
  FOREIGN KEY (empresa_id) REFERENCES empresa(id),
  FOREIGN KEY (ordem_id) REFERENCES ordens_servico(id)
);

-- Tabela FINALIZADO
CREATE TABLE IF NOT EXISTS finalizado (
  id INT PRIMARY KEY AUTO_INCREMENT,
  empresa_id INT,
  ordem_id INT,
  data_finalizacao DATE,
  FOREIGN KEY (empresa_id) REFERENCES empresa(id),
  FOREIGN KEY (ordem_id) REFERENCES ordens_servico(id)
);
