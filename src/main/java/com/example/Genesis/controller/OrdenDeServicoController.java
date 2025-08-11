package com.example.Genesis.controller;

import com.example.Genesis.domain.dto.OrdemDeServicoDTO;
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
    public ResponseEntity<OrdemDeServicoDTO> novaOrdem(@RequestBody OrdemDeServicoDTO dados){
        OrdemDeServicoDTO ordem = service.criarOrdem(dados);
        return ResponseEntity.ok(ordem);
    }

    @PutMapping("editarOrdem")
    public ResponseEntity<OrdemDeServicoDTO>editarOrdem(@RequestBody OrdemDeServicoDTO dados){
        OrdemDeServicoDTO ordemATT = service.atualizarOrdem(dados);
        return ResponseEntity.ok(ordemATT);
    }
}
