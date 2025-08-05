package com.example.Genesis.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CriarEmpresaDTO(
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
