package com.example.Genesis.domain.service;

import com.example.Genesis.domain.dto.NovaOrdemDeServicoDTO;
import com.example.Genesis.domain.entity.Empresa;
import com.example.Genesis.domain.entity.OrdemDeServico;
import com.example.Genesis.domain.entity.Pedido;
import com.example.Genesis.domain.repository.OrdenDeServicoRepository;
import com.example.Genesis.domain.repository.PedidoRepository;
import com.example.Genesis.domain.service.components.Validacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdemDeServicoService {
    @Autowired
    private OrdenDeServicoRepository repository;
    @Autowired
    private Validacao validacao;
    @Autowired
    private ArquivoService arquivoService;
    @Autowired
    private PedidoRepository pedidoRepository;

    public NovaOrdemDeServicoDTO criarOrdem(NovaOrdemDeServicoDTO dados) {
        Empresa empresa = validacao.validarEmpresa(dados.empresaID());
        Pedido pedido = validacao.validarPedido(dados.pedidoID());

        OrdemDeServico ordem = new OrdemDeServico(dados, pedido, empresa);

        repository.save(ordem);
        if (ordem.getValor()!= null){
            Float valorTotalPedido = pedido.getValorTotal() + ordem.getValor();
            pedido.setValorTotal(valorTotalPedido);
            pedidoRepository.save(pedido);
        }

        return  new NovaOrdemDeServicoDTO(ordem);
    }


    public NovaOrdemDeServicoDTO atualizarOrdem(NovaOrdemDeServicoDTO dados){
        OrdemDeServico ordem = repository.getReferenceById(dados.id());
        Pedido pedido = pedidoRepository.getReferenceById(ordem.getPedido().getId());
        if (dados.valor() != null){
            Float valorPedido = pedido.getValorTotal() - ordem.getValor();

            Float novoValorPedido = valorPedido + dados.valor();

            pedido.setValorTotal(novoValorPedido);
            ordem.setValor(dados.valor());

        }
        if (dados.descricao() != null){
            ordem.setDescricao(dados.descricao());
        }
        if (dados.observacoes() != null){
            ordem.setObservacoes(dados.observacoes());
        }

        repository.save(ordem);
        pedidoRepository.save(pedido);
        return new NovaOrdemDeServicoDTO(ordem);
    }

    public void deletarOrdem(Long id) {

        OrdemDeServico ordem = repository.getReferenceById(id);

        Pedido pedido = pedidoRepository.getReferenceById(ordem.getPedido().getId());
        ordem.getArquivos().forEach(arquivo -> {arquivoService.deletarArquivo(arquivo.getId());});
        pedido.setValorTotal(pedido.getValorTotal() - ordem.getValor());

        pedidoRepository.save(pedido);
        repository.delete(ordem);
    }
}
