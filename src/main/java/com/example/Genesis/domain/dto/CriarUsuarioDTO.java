package com.example.Genesis.domain.dto;

public record CriarUsuarioDTO(
        Long empresa,
        String username,
        String senha,
        String papel,
        Long funcionario
) {
}
