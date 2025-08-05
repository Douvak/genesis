package com.example.Genesis.domain.dto;

import com.example.Genesis.domain.entity.Funcionario;

public record ListaFuncionariosDTO(
        Long id,
        String nome,
        String cargo,
        String email

) {
    public ListaFuncionariosDTO(Funcionario funcionario){
        this(funcionario.getId(), funcionario.getNome(), funcionario.getCargo(), funcionario.getEmail() );
    }

}
