package com.example.Genesis.controller;

import com.example.Genesis.domain.dto.NovoClienteDTO;
import com.example.Genesis.domain.dto.ListaClientesDTO;
import com.example.Genesis.domain.service.ClienteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    @Transactional
    public ResponseEntity<NovoClienteDTO> criarCliente(@RequestBody NovoClienteDTO dados){
        NovoClienteDTO cliente = service.criarCliente(dados);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("lista/{empresaID}")
    public ResponseEntity<List<ListaClientesDTO>> listaClientes(@PathVariable Long empresaID){
        var listaDeClientes = service.listaDeClientes(empresaID);
        return ResponseEntity.ok(listaDeClientes);
    }

    @GetMapping("{id}")
    public ResponseEntity<NovoClienteDTO>dadosCliente(@PathVariable Long id){
        NovoClienteDTO cliente = service.dadosCliente(id);
        return ResponseEntity.ok(cliente);
    }

    @Transactional
    @PutMapping("{id}")
    public ResponseEntity<NovoClienteDTO>atualizarCliente(@PathVariable Long id, @RequestBody NovoClienteDTO dados){
        NovoClienteDTO atualizado = service.atualizarCliente(id, dados);
        return ResponseEntity.ok(atualizado);
    }
    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity<String>deletarCliente(@PathVariable Long id){
        service.deletarCliente(id);
        return ResponseEntity.ok("Cliente Deletado com sucesso");
    }
}
