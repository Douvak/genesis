CREATE TABLE arquivos_ordem (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ordem_pedido_id INT NOT NULL,
    rota VARCHAR(500) NOT NULL,
    data_upload TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ordem_pedido_id) REFERENCES ordens_servico(id) ON DELETE CASCADE
);
