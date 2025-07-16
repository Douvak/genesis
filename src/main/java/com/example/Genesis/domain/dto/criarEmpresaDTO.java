package com.example.Genesis.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record criarEmpresaDTO(
        @NotNull
        String nome,
        @NotNull
        @Size(min = 20, max = 20)
        String cnpj,
        String telefone,
        String email,
        String endereco
) {
}
