package com.example.Genesis.domain.dto;

import com.example.Genesis.domain.entity.Empresa;

public record NovaEmpresaDTO(
        Long id,
        String nome,
        String cnpj,
        String telefone,
        String email,
        String endereco
) {
    public NovaEmpresaDTO(Empresa empresa){
        this(empresa.getId(), empresa.getNome(), empresa.getCnpj(), empresa.getTelefone(), empresa.getEmail(), empresa.getEndereco());
    }
}
