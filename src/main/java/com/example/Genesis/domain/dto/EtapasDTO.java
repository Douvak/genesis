package com.example.Genesis.domain.dto;

import com.example.Genesis.domain.entity.Etapas;

import java.time.LocalDateTime;

public record EtapasDTO(
        Long id,
        String ordem,
        String etapa,
        String motivo,
        String funcionario,
        LocalDateTime inicio,
        LocalDateTime finalizado

) {

    public EtapasDTO(Etapas etapas){
        this(etapas.getId(),etapas.getOrdemDeServico().getDescricao(), etapas.getTipoEtapa().toString(),etapas.getMotivo(),etapas.getFuncionario() != null ? etapas.getFuncionario().getNome() : null,etapas.getInicio(),etapas.getFinalizado());
    }
}
