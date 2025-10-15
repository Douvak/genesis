package com.example.Genesis.domain.service;

import com.example.Genesis.domain.dto.EtapasDTO;
import com.example.Genesis.domain.dto.NovaEtapaDTO;
import com.example.Genesis.domain.dto.NovaOrdemDeServicoDTO;
import com.example.Genesis.domain.dto.OrdemDeServicoDTO;
import com.example.Genesis.domain.entity.Empresa;
import com.example.Genesis.domain.entity.Etapas;
import com.example.Genesis.domain.entity.Funcionario;
import com.example.Genesis.domain.entity.OrdemDeServico;
import com.example.Genesis.domain.repository.EtapasRepository;
import com.example.Genesis.domain.repository.OrdenDeServicoRepository;
import com.example.Genesis.domain.service.components.Validacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class EtapasService {
    @Autowired
    private EtapasRepository repository;
    @Autowired
    private Validacao validacao;
    @Autowired
    private OrdenDeServicoRepository ordenDeServicoRepository;


    public EtapasDTO iniciarEtapa(NovaEtapaDTO dados){
        OrdemDeServico ordem = ordenDeServicoRepository.getReferenceById(dados.ordem());
        Etapas novaEtapa = new Etapas(ordem, dados);
        if (dados.funcionario() != null){
            Funcionario funcionario= validacao.validarFuncionario(dados.funcionario());
            novaEtapa.setFuncionario(funcionario);

        }
        if (Objects.equals(dados.etapa(), "PAUSADO"))
            novaEtapa.setPedidoID(ordem.getPedido().getId());
        repository.save(novaEtapa);

        return new EtapasDTO(novaEtapa);

    }
    public NovaOrdemDeServicoDTO finalizarEtapa(Long id){
        Etapas etapa = repository.getReferenceById(id);
        etapa.setFinalizado(LocalDateTime.now());
        repository.save(etapa);
        OrdemDeServico ordem = ordenDeServicoRepository.getReferenceById(etapa.getOrdemDeServico().getId());
        return new NovaOrdemDeServicoDTO(ordem);
    }

    public List<EtapasDTO> listaEtapas(Long ordemID){
        OrdemDeServico ordem = ordenDeServicoRepository.getReferenceById(ordemID);
        List<EtapasDTO> lista = repository.findByOrdemDeServico_Id(ordem.getId()).stream().map(EtapasDTO::new).toList();
        return lista;
    }
}
