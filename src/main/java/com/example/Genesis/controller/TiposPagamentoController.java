package com.example.Genesis.controller;

import com.example.Genesis.domain.dto.EfetuarPagamentoDTO;
import com.example.Genesis.domain.dto.TipoPagamentoDTO;
import com.example.Genesis.domain.entity.TipoPagamento;
import com.example.Genesis.domain.service.PagamentosService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("pagamentos")
public class TiposPagamentoController {

    @Autowired
    private PagamentosService service;

    @Transactional
    @PostMapping
    public ResponseEntity<List<TipoPagamentoDTO>>criarPagamentos(@RequestBody List<TipoPagamentoDTO> dados){
        List<TipoPagamentoDTO> pagamentos = service.criarPagamentos(dados);
        return ResponseEntity.ok(pagamentos);
    }

    @PostMapping("pagamento")
    public ResponseEntity<Boolean>efetuarPagamento(@RequestBody EfetuarPagamentoDTO dados){
        service.efetuarPagamento(dados);
        return ResponseEntity.ok(true);
    }

}
