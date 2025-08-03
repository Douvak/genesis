package com.example.Genesis.controller;

import com.example.Genesis.domain.dto.DadosAutenticacao;
import com.example.Genesis.domain.entity.Usuario;
import com.example.Genesis.domain.service.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    @PostMapping
    @Transactional
    public ResponseEntity autenticacao(@RequestBody @Valid DadosAutenticacao dados){
        var token = new UsernamePasswordAuthenticationToken(dados.Login(), dados.Senha());
        var authentication = authenticationManager.authenticate(token);
        tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok().build();
    }
}
