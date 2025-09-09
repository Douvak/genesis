package com.example.Genesis.domain.service;

import com.example.Genesis.domain.dto.NovaOrdemDeServicoDTO;
import com.example.Genesis.domain.entity.Empresa;
import com.example.Genesis.domain.entity.OrdemDeServico;
import com.example.Genesis.domain.entity.Pedido;
import com.example.Genesis.domain.repository.OrdenDeServicoRepository;
import com.example.Genesis.domain.service.components.Validacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdemDeServicoService {
    @Autowired
    private OrdenDeServicoRepository repository;
    @Autowired
    private Validacao validacao;

    public NovaOrdemDeServicoDTO criarOrdem(NovaOrdemDeServicoDTO dados) {
        Empresa empresa = validacao.validarEmpresa(dados.empresaID());
        Pedido pedido = validacao.validarPedido(dados.pedidoID());

        OrdemDeServico ordem = new OrdemDeServico(dados, pedido, empresa);

        repository.save(ordem);
        return  new NovaOrdemDeServicoDTO(ordem);
    }


    public NovaOrdemDeServicoDTO atualizarOrdem(NovaOrdemDeServicoDTO dados){
        OrdemDeServico ordem = repository.getReferenceById(dados.id());
        if (dados.valor() != null){
            ordem.setValor(dados.valor());
        }
        if (dados.descricao() != null){
            ordem.setDescricao(dados.descricao());
        }
        repository.save(ordem);
        return new NovaOrdemDeServicoDTO(ordem);
    }

    public void deletarOrdem(Long id) {

        OrdemDeServico ordem = repository.getReferenceById(id);
        repository.delete(ordem);
    }
}
