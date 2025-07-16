package com.example.Genesis.controller;

import com.example.Genesis.domain.dto.EmpresaDTO;
import com.example.Genesis.domain.dto.criarEmpresaDTO;
import com.example.Genesis.domain.service.EmpresaService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("empresa")
@AllArgsConstructor
public class EmpresaController {

    @Autowired
    private final EmpresaService eventoService;

    @PostMapping
    @Transactional
    public ResponseEntity criar(@RequestBody criarEmpresaDTO dados){
        eventoService.criarEmpresa(dados);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<EmpresaDTO>> listar(Pageable pageable){
        var page = eventoService.listarEmpresas(pageable);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        eventoService.deletarEmpresa(id);
        return ResponseEntity.ok("Empresa Deletada com sucesso!");
    }
}
