package com.example.Genesis.domain.dto;

import com.example.Genesis.domain.entity.Funcionario;

public record FuncionarioDTO(
        Long empresaID,
        String nome,
        String cargo,
        String email

) {
    public FuncionarioDTO(Funcionario funcionario){
        this(funcionario.getId(), funcionario.getNome(), funcionario.getCargo(), funcionario.getEmail());
    }
}
