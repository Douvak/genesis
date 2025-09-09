package com.example.Genesis.domain.dto;

import com.example.Genesis.domain.entity.Pedido;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoDTO(
        Long id,
        String cliente,
        Float valorTotal,
        LocalDateTime previsaoDeEntrega,
        List<TipoPagamentoDTO> tipoPagamentos,
        List<NovaOrdemDeServicoDTO> ordens
) {
    public PedidoDTO(Pedido pedido) {
    this(pedido.getId(),
            pedido.getCliente() != null ? pedido.getCliente().getNome() : null,pedido.getValorTotal(),
            pedido.getPrevisaoDeEntrega(),
            pedido.getTiposPagamentos().stream().map(TipoPagamentoDTO::new).toList(),
            pedido.getOrdemDeServicos().stream().map(NovaOrdemDeServicoDTO::new).toList());



    }

}
