package com.example.Genesis.domain.dto;

public record NovaSenhaDTO(
        Long empresaID,
        String senhaAtual,
        String novaSenha
) {
}
