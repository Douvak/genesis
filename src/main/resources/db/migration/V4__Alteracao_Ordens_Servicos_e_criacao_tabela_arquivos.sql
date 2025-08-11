ALTER TABLE ordens_servico DROP COLUMN arquivos;

CREATE TABLE arquivos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ordem_servico_id INT NOT NULL,
    link_arquivo TEXT NOT NULL,
    FOREIGN KEY (ordem_servico_id) REFERENCES ordens_servico(id) ON DELETE CASCADE
);
