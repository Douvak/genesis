package com.example.Genesis.controller;

import com.example.Genesis.domain.dto.NovaOrdemDeServicoDTO;
import com.example.Genesis.domain.service.OrdemDeServicoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ordemDeServico")
public class OrdenDeServicoController {

    @Autowired
    private OrdemDeServicoService service;

    @Transactional
    @PostMapping
    public ResponseEntity<NovaOrdemDeServicoDTO> novaOrdem(@RequestBody NovaOrdemDeServicoDTO dados){
        NovaOrdemDeServicoDTO ordem = service.criarOrdem(dados);
        return ResponseEntity.ok(ordem);
    }

    @Transactional
    @PutMapping("editarOrdem")
    public ResponseEntity<NovaOrdemDeServicoDTO>editarOrdem(@RequestBody NovaOrdemDeServicoDTO dados){
        NovaOrdemDeServicoDTO ordemATT = service.atualizarOrdem(dados);
        return ResponseEntity.ok(ordemATT);
    }

    @Transactional
    @DeleteMapping("{id}")
   public ResponseEntity<String>deletarORdem(@PathVariable Long id) {
        service.deletarOrdem(id);
        return ResponseEntity.ok("Ordem deletada");
    }


}
