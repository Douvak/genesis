package com.example.Genesis.domain.dto;

import com.example.Genesis.domain.entity.Cliente;
import com.example.Genesis.domain.entity.Empresa;

import java.util.List;

public record EmpresaDadosDTO(
        Long id,
        String nome,
        List<ClienteDTO> clientes,
        List<PedidoDTO> pedidos
) {

    public EmpresaDadosDTO(Empresa empresa){
        this(empresa.getId(),
                empresa.getNome(),
                empresa.getClientes().stream().map(ClienteDTO::new).toList(),
                empresa.getPedidos().stream().map(PedidoDTO::new).toList()
        );
    }
}
