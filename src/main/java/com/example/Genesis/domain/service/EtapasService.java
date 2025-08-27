package com.example.Genesis.domain.service;

import com.example.Genesis.domain.dto.EtapasDTO;
import com.example.Genesis.domain.dto.NovaEtapaDTO;
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

@Service
public class EtapasService {
    @Autowired
    private EtapasRepository repository;
    @Autowired
    private Validacao validacao;
    @Autowired
    private OrdenDeServicoRepository ordenDeServicoRepository;


    public EtapasDTO iniciarEtapa(NovaEtapaDTO dados){
        Empresa empresa = validacao.validarEmpresa(dados.empresa());
        Funcionario funcionario= validacao.validarFuncionario(dados.funcionario());
        Etapas novaEtapa = new Etapas(empresa, funcionario, dados);
        repository.save(novaEtapa);

        return new EtapasDTO(novaEtapa);

    }
    public void finalizarEtapa(Long id){
        Etapas etapa = repository.getReferenceById(id);
        etapa.setFinalizado(LocalDateTime.now());
        repository.save(etapa);
    }

    public List<EtapasDTO> listaEtapas(Long ordemID){
        OrdemDeServico ordem = ordenDeServicoRepository.getReferenceById(ordemID);
        List<EtapasDTO> lista = repository.findByOrdemDeServico_Id(ordem.getId()).stream().map(EtapasDTO::new).toList();
        return lista;
    }
}
