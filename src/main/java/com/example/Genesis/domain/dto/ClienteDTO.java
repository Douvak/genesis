package com.example.Genesis.domain.dto;

import com.example.Genesis.domain.entity.Cliente;
import com.example.Genesis.domain.entity.Usuario;

public record ClienteDTO(
        Long id,
        String nome,
        String contato
) {

    public ClienteDTO(Cliente cliente){
        this(   cliente.getId(),
                cliente.getNome(),
                cliente.getContato());
    }
}
