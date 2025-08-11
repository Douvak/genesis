package com.example.Genesis.controller;

import com.example.Genesis.domain.dto.NovaPrevisaoEentrega;
import com.example.Genesis.domain.dto.NovoPedidoDTO;
import com.example.Genesis.domain.dto.PedidoDTO;
import com.example.Genesis.domain.service.PedidoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @Transactional
    @PostMapping
    public ResponseEntity<PedidoDTO>criarPedido(@RequestBody NovoPedidoDTO dados){
        PedidoDTO pedido = service.criarPedido(dados);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping("lista/{empresaID}")
    public ResponseEntity<List<PedidoDTO>>listaDePedidos(@PathVariable Long empresaID){
        List<PedidoDTO> pedidos = service.listaDePedidos(empresaID);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("{id}")
    public ResponseEntity<PedidoDTO>pedidoDetalhado(@PathVariable Long id){
        PedidoDTO pedido = service.detalharPedido(id);
        return ResponseEntity.ok(pedido);
    }
    @Transactional
    @PutMapping
    public ResponseEntity<PedidoDTO> atualizarPrevisaoEntrega(@RequestBody NovaPrevisaoEentrega dados){
        PedidoDTO pedidoATT = service.atualizarPrevisaoEntrega(dados);
        return ResponseEntity.ok(pedidoATT);
    }
    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity<String>deletarPedido(@PathVariable Long id){
        service.deletarPedido(id);
        return ResponseEntity.ok("Pedido deletado com Sucesso!");
    }


}
