package com.example.Genesis.domain.dto;

import com.example.Genesis.domain.entity.TipoPagamento;

import java.time.LocalDateTime;

public record TipoPagamentoDTO(
        Long id,
        Float valor,
        String tipo,
        Boolean status,
        LocalDateTime dataPagamento,
        Long pedidoID,
        Long empresaID

) {

    public TipoPagamentoDTO(TipoPagamento tipoPagamento){
        this(tipoPagamento.getId(),
                tipoPagamento.getValor(),
                String.valueOf(tipoPagamento.getTipo()),
                tipoPagamento.getStatus(),tipoPagamento.getDataPagamento(),
                tipoPagamento.getPedido().get(0).getId(),
                tipoPagamento.getEmpresa().getId());
    }
}
