package com.example.Genesis.domain.dto;

public record NovoPedidoDTO(
        Long empresaID,
        Long clienteID,
        String nome,
        String contato
) {
}
