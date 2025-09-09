package com.example.Genesis.domain.dto;

import com.example.Genesis.domain.entity.Pedido;
import com.example.Genesis.domain.entity.TipoPagamento;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoDTO(
        Long id,
        String cliente,
        String contato,
        Float valorTotal,
        LocalDateTime previsaoDeEntrega,
        List<TipoPagamentoDTO> tipoPagamentos,
        List<OrdemDeServicoDTO> ordens
) {
    public PedidoDTO(Pedido pedido) {
    this(pedido.getId(),
            pedido.getCliente() != null ? pedido.getCliente().getNome() : null,
            pedido.getCliente() != null ? pedido.getCliente().getContato(): null,
            pedido.getValorTotal(),
            pedido.getPrevisaoDeEntrega(),
            pedido.getTiposPagamentos().stream().map(TipoPagamentoDTO::new).toList(),
            pedido.getOrdemDeServicos().stream().map(OrdemDeServicoDTO::new).toList());



    }

}
