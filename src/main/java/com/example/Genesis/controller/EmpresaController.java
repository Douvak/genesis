package com.example.Genesis.controller;

import com.example.Genesis.domain.dto.EmpresaDadosDTO;
import com.example.Genesis.domain.dto.NovaEmpresaDTO;
import com.example.Genesis.domain.dto.CriarEmpresaDTO;
import com.example.Genesis.domain.dto.NovaSenhaDTO;
import com.example.Genesis.domain.service.EmpresaService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
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
    private final EmpresaService empresaService;

    @PostMapping
    @Transactional
    public ResponseEntity criar(@RequestBody CriarEmpresaDTO dados){
        empresaService.criarEmpresa(dados);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<NovaEmpresaDTO>> listar(Pageable pageable){
        var page = empresaService.listarEmpresas(pageable);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        empresaService.deletarEmpresa(id);
        return ResponseEntity.ok("Empresa Deletada com sucesso!");
    }
    @GetMapping("{id}")
    public ResponseEntity<EmpresaDadosDTO>dadosEmpresa(@PathVariable Long id){
        var dadosEmpresa = empresaService.dadosEmpresa(id);
        return ResponseEntity.ok(dadosEmpresa);
    }
    @PostMapping("alterarSenha")
    @Transactional
    public ResponseEntity<Boolean>alterarSenhaEmpresa( @RequestBody NovaSenhaDTO dados){
        var senhaAlterada = empresaService.alterarSenhaEmpresa(dados);
        return ResponseEntity.ok(senhaAlterada);
    }

}
