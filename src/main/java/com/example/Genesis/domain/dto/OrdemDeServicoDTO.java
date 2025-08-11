package com.example.Genesis.domain.dto;

import com.example.Genesis.domain.entity.OrdemDeServico;

public record OrdemDeServicoDTO(
        Long id,
        String descricao,
        Float valor,
        Long empresaID,
        Long pedidoID
) {

    public OrdemDeServicoDTO(OrdemDeServico ordem){
        this(ordem.getId(), ordem.getDescricao(), ordem.getValor(),ordem.getEmpresa().getId(),ordem.getPedido().getId());
    }
}
