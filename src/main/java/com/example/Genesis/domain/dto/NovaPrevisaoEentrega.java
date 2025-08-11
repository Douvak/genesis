package com.example.Genesis.domain.dto;

import java.time.LocalDateTime;

public record NovaPrevisaoEentrega(
        Long pedidoID,
        LocalDateTime dataEntrega
) {
}
