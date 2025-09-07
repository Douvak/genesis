package com.example.Genesis.domain.dto;

import com.example.Genesis.domain.entity.OrdemDeServico;

public record NovaOrdemDeServicoDTO(
        Long id,
        String descricao,
        Float valor,
        Long pedidoID,
        Long empresaID

) {

    public NovaOrdemDeServicoDTO(OrdemDeServico ordem){
        this(ordem.getId(), ordem.getDescricao(), ordem.getValor(),ordem.getPedido().getId(),ordem.getEmpresa().getId());
    }

}
