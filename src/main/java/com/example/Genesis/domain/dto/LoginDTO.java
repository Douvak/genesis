package com.example.Genesis.domain.dto;

import jakarta.validation.constraints.NotNull;

public record LoginDTO(
        @NotNull
        String login,
        @NotNull
        String senha
) {
}
