package com.example.Genesis.domain.dto;

import java.time.LocalDateTime;

public record EfetuarPagamentoDTO(
        Long pagamentoID,
        LocalDateTime data
) {
}
