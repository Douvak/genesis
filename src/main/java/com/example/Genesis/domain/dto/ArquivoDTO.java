package com.example.Genesis.domain.dto;

import com.example.Genesis.domain.entity.Arquivo;

public record ArquivoDTO(
        Long id,
        String rota

) {
    public ArquivoDTO(Arquivo arquivo) {
        this(arquivo.getId(),
        arquivo.getRota());
    }
}
