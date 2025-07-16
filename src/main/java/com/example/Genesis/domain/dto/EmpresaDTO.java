package com.example.Genesis.domain.dto;

import com.example.Genesis.domain.entity.Empresa;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EmpresaDTO(
        Long id,
        String nome,
        String cnpj,
        String telefone,
        String email,
        String endereco
) {
    public EmpresaDTO( Empresa empresa){
        this(empresa.getId(), empresa.getNome(), empresa.getCnpj(), empresa.getTelefone(), empresa.getEmail(), empresa.getEndereco());
    }
}
