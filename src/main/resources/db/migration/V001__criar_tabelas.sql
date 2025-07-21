CREATE TABLE pagamentos (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            pedido_id BINARY(16) NOT NULL,
                            valor DECIMAL(10,2) NOT NULL,
                            numero_cartao VARCHAR(16) NOT NULL,
                            status VARCHAR(20),
                            data_criacao DATETIME NOT NULL,
                            id_transacao VARCHAR(255)
);

CREATE INDEX idx_pedido_id ON pagamentos(pedido_id);

ALTER TABLE pagamentos
    ADD CONSTRAINT uk_pagamentos_pedidoId UNIQUE (pedido_id);
