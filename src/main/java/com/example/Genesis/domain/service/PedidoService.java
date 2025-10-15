package com.example.Genesis.domain.service;

import com.example.Genesis.domain.dto.NovaPrevisaoEentrega;
import com.example.Genesis.domain.dto.NovoPedidoDTO;
import com.example.Genesis.domain.dto.PedidoDTO;
import com.example.Genesis.domain.entity.Cliente;
import com.example.Genesis.domain.entity.Empresa;
import com.example.Genesis.domain.entity.Pedido;
import com.example.Genesis.domain.repository.ClienteRepoistory;
import com.example.Genesis.domain.repository.PedidoRepository;
import com.example.Genesis.domain.service.components.Validacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;
    @Autowired
    private ClienteRepoistory clienteRepoistory;
    @Autowired
    private Validacao validacao;

    public PedidoDTO criarPedido(NovoPedidoDTO dados) {
        Empresa empresa = validacao.validarEmpresa(dados.empresaID());
        Pedido novoPedido = new Pedido(empresa);
        if (dados.clienteID() != null) {
            Cliente cliente = clienteRepoistory.getReferenceById(dados.clienteID());
            novoPedido.setCliente(cliente);
        }
        repository.save(novoPedido);
        return new PedidoDTO(novoPedido);
    }

    public List<PedidoDTO> listaDePedidos(Long empresaID) {
        Empresa empresa = validacao.validarEmpresa(empresaID);

        List<PedidoDTO> lista = repository.findByEmpresaId(empresa.getId(), Sort.by(Sort.Direction.ASC, "previsaoDeEntrega")).stream().map(PedidoDTO::new).toList();
        return lista;
    }

    public PedidoDTO atualizarPrevisaoEntrega(NovaPrevisaoEentrega dados) {
        Pedido pedido = repository.getReferenceById(dados.pedidoID());
        pedido.setPrevisaoDeEntrega(dados.dataEntrega());
        repository.save(pedido);
        return new PedidoDTO(pedido);
    }
    public void deletarPedido(Long id){
        Pedido pedido = repository.getReferenceById(id);
        repository.delete(pedido);
    }


    public PedidoDTO detalharPedido(Long id) {
        Pedido pedido = repository.getReferenceById(id);
        return new PedidoDTO(pedido);
    }

    public void colocarEmPRoducao(Long id) {
        Pedido pedido = repository.getReferenceById(id);
        pedido.setStatus("EM PRODUÇÃO");
        repository.save(pedido);


    }
}
