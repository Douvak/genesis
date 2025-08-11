ALTER TABLE ordens_servico
ADD COLUMN pedido BIGINT,
ADD CONSTRAINT ordem_pedido_id FOREIGN KEY (pedido_id) REFERENCES pedidos(id);
