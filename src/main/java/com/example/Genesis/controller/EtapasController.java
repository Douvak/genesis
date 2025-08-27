package com.example.Genesis.controller;

import com.example.Genesis.domain.dto.EtapasDTO;
import com.example.Genesis.domain.dto.NovaEtapaDTO;
import com.example.Genesis.domain.service.EtapasService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("etapa")
public class EtapasController {
    @Autowired
    private EtapasService service;

    @PostMapping("iniciar")
    @Transactional
    public ResponseEntity<EtapasDTO> iniciarEtapa(@RequestBody NovaEtapaDTO dados){

        var novaEtapa = service.iniciarEtapa(dados);
        return ResponseEntity.ok(novaEtapa);

    }
    @PostMapping("finalizar/{id}")
    public ResponseEntity<String> finalizarEtapa(@PathVariable Long id) {

        service.finalizarEtapa(id);
        return ResponseEntity.ok("finalizado");
    }

    @GetMapping("{id}")
    public ResponseEntity<List<EtapasDTO>> listarEtapas(@PathVariable Long id){
        var lista = service.listaEtapas(id);
        return ResponseEntity.ok(lista);
    }
}
