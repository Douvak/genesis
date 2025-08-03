package com.example.Genesis.controller;

import com.example.Genesis.domain.dto.CriarUsuarioDTO;
import com.example.Genesis.domain.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuario")
@AllArgsConstructor
public class UsuarioController {
    @Autowired
    private final UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity criarUsuario(@RequestBody @Valid CriarUsuarioDTO dados){
        var novoUsuario = usuarioService.criarUsuario(dados);
        return ResponseEntity.ok(novoUsuario);
    }

}
