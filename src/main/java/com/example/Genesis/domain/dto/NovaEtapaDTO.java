package com.example.Genesis.domain.dto;

public record NovaEtapaDTO(
        Long funcionario,
        Long ordem,
        String etapa,
        String motivo
) {
}
