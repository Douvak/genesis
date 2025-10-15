package com.example.Genesis.domain.dto;

import com.example.Genesis.domain.entity.Arquivo;
import com.example.Genesis.domain.entity.OrdemDeServico;

import java.util.ArrayList;
import java.util.List;

public record NovaOrdemDeServicoDTO(
        Long id,
        String descricao,
        Float valor,
        Long pedidoID,
        Long empresaID,
        List<ArquivoDTO> arquivos,
        List<EtapasDTO>etapas,
        String observacoes,
        String material

) {

    public NovaOrdemDeServicoDTO(OrdemDeServico ordem){
        this(ordem.getId(), ordem.getDescricao(), ordem.getValor(),ordem.getPedido().getId(),ordem.getEmpresa().getId(), ordem.getArquivos().stream().map(ArquivoDTO::new).toList(), ordem.getEtapas() != null ? ordem.getEtapas().stream().map(EtapasDTO::new).toList(): new ArrayList<>() ,ordem.getObservacoes(), ordem.getMaterial());
    }
}
