package com.example.Genesis.domain.dto;

import com.example.Genesis.domain.entity.Cliente;
import com.example.Genesis.domain.entity.Empresa;

public record ListaClientesDTO(
        Long id,
        String nome
) {
    public ListaClientesDTO(Cliente cliente){
        this(cliente.getId(), cliente.getNome());
    }


}
