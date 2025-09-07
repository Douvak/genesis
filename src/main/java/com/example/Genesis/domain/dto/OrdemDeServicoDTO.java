package com.example.Genesis.domain.dto;

import com.example.Genesis.domain.entity.OrdemDeServico;

public record OrdemDeServicoDTO(
        Long id,
        String descricao,
        Float valor

) {

    public OrdemDeServicoDTO(OrdemDeServico ordem){
        this(ordem.getId(), ordem.getDescricao(), ordem.getValor());
    }
}