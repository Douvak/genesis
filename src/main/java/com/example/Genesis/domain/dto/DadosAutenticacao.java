package com.example.Genesis.domain.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAutenticacao(
        @NotNull
        String Login,
        @NotNull
        String Senha
) {
}
