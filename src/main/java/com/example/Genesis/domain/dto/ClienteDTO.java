package com.example.Genesis.domain.dto;

import com.example.Genesis.domain.entity.Cliente;

public record ClienteDTO(
        Long id,
        String nome,
        String contato,
        Long empresaID
) {

    public ClienteDTO(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getContato(), cliente.getEmpresa().getId());
    }
}
