package com.example.Genesis.domain.dto;

import com.example.Genesis.domain.entity.Etapas;

import java.time.LocalDateTime;

public record EtapasDTO(
        Long id,
        String etapa,

        String funcionario,
        LocalDateTime inicio,
        LocalDateTime finalizado

) {

    public EtapasDTO(Etapas etapas){
        this(etapas.getId(), etapas.getTipoEtapa().toString(), etapas.getFuncionario().getNome(),etapas.getInicio(),etapas.getFinalizado());
    }
}
