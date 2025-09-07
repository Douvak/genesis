package com.example.Genesis.domain.dto;

import com.example.Genesis.domain.entity.Cliente;

public record NovoClienteDTO(
        Long id,
        String nome,
        String contato,
        Long empresaID
) {

    public NovoClienteDTO(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getContato(), cliente.getEmpresa().getId());
    }
}
