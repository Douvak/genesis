package com.example.Genesis.domain.dto;

import com.example.Genesis.domain.entity.Funcionario;

public record EditarFuncionarioDTO(
        Long funcionarioID,
        Long empresaID,
        String nome,
        String cargo,
        String email

) {
    public EditarFuncionarioDTO(Funcionario funcionario){
        this(funcionario.getId(), funcionario.getId(), funcionario.getNome(), funcionario.getCargo(), funcionario.getEmail());
    }
}
