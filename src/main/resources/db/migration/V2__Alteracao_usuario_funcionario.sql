
-- Remover FK e coluna de usuario_id da tabela funcionarios
ALTER TABLE funcionarios
DROP FOREIGN KEY funcionarios_ibfk_2; -- Nome da FK pode variar, veja observação abaixo
ALTER TABLE funcionarios
DROP COLUMN usuario_id;

-- Adicionar coluna funcionario_id à tabela usuarios
ALTER TABLE usuarios
ADD COLUMN funcionario_id INT UNIQUE;

-- Adicionar chave estrangeira para funcionarios
ALTER TABLE usuarios
ADD CONSTRAINT fk_usuario_funcionario
FOREIGN KEY (funcionario_id)
REFERENCES funcionarios(id);
