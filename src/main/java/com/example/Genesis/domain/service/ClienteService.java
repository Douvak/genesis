package com.example.Genesis.domain.service;

import com.example.Genesis.domain.dto.NovoClienteDTO;
import com.example.Genesis.domain.dto.ListaClientesDTO;
import com.example.Genesis.domain.entity.Cliente;
import com.example.Genesis.domain.entity.Empresa;
import com.example.Genesis.domain.repository.ClienteRepoistory;
import com.example.Genesis.domain.service.components.Validacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepoistory repoistory;
    @Autowired
    private Validacao validacao;

    public NovoClienteDTO criarCliente(NovoClienteDTO dados) {
        Empresa empresa = validacao.validarEmpresa(dados.empresaID());

        Cliente novoCliente = new Cliente(dados,empresa);
        repoistory.save(novoCliente);
        return new NovoClienteDTO(novoCliente);
    }

    public List<ListaClientesDTO> listaDeClientes(Long empresaID) {
        Empresa empresa = validacao.validarEmpresa(empresaID);
        List<ListaClientesDTO> listaDeClientes = repoistory.findByEmpresaId(empresa.getId()).stream().map(ListaClientesDTO::new).toList();

        return listaDeClientes;
    }

    public NovoClienteDTO atualizarCliente(Long id, NovoClienteDTO dados) {
        Cliente cliente = repoistory.getReferenceById(id);
        if (dados.nome() != null){
            cliente.setNome(dados.nome());
        }
        if (dados.contato() != null){
            cliente.setContato(dados.contato());
        }
        repoistory.save(cliente);
        return new NovoClienteDTO(cliente);
    }

    public void deletarCliente(Long id) {
        Cliente cliente = repoistory.getReferenceById(id);
        repoistory.delete(cliente);
    }

    public NovoClienteDTO dadosCliente(Long id) {

        Cliente cliente = repoistory.getReferenceById(id);

        return new NovoClienteDTO(cliente);
    }
}
