package com.example.Genesis.domain.dto;

import com.example.Genesis.domain.entity.Cliente;
import com.example.Genesis.domain.entity.Pedido;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoDTO(
        Long id,
        ClienteDTO cliente,
        Float valorTotal,
        LocalDateTime previsaoDeEntrega,
        List<TipoPagamentoDTO> tipoPagamentos,
        List<NovaOrdemDeServicoDTO> ordens,
        List<EtapasDTO> pausado,
        String status
) {
    public PedidoDTO(Pedido pedido) {

    this(pedido.getId(),
            new ClienteDTO(pedido.getCliente()),pedido.getValorTotal(),
            pedido.getPrevisaoDeEntrega(),
            pedido.getTiposPagamentos().stream().map(TipoPagamentoDTO::new).toList(),
            pedido.getOrdemDeServicos().stream().map(NovaOrdemDeServicoDTO::new).toList(),
            pedido.getOrdemDeServicos().stream().
                    flatMap( ordem -> ordem.getEtapas().stream()
                            .filter(etapas -> etapas.getTipoEtapa().toString().equals("PAUSADO"))
                            .filter( etapas -> etapas.getFinalizado() == null))
                    .map(EtapasDTO::new).toList(), pedido.getStatus());

    ;



    }

}
