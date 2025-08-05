package com.example.Genesis.domain.service;

import com.example.Genesis.domain.dto.CriarUsuarioDTO;
import com.example.Genesis.domain.entity.Empresa;
import com.example.Genesis.domain.entity.Funcionario;
import com.example.Genesis.domain.entity.Usuario;
import com.example.Genesis.domain.repository.EmpresaRepository;
import com.example.Genesis.domain.repository.FuncionarioRepository;
import com.example.Genesis.domain.repository.UsuarioRepositiry;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {
    @Autowired
    private final UsuarioRepositiry usuarioRepositiry;
    @Autowired
    private final EmpresaRepository empresaRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final FuncionarioRepository funcionarioRepository;

    public Usuario criarUsuario(@Valid CriarUsuarioDTO dados) {
        Funcionario funcionario = null;
        Empresa empresa = empresaRepository.findById(dados.empresa())
                .orElseThrow(() -> new RuntimeException("Empresa nao Cadastrada!"));
        if (dados.funcionario() != null) {
             funcionario = funcionarioRepository.getReferenceById(dados.funcionario());
        }
        Usuario verificarususario = usuarioRepositiry.findByUsernameAndEmpresaAndFuncionario(dados.username(), empresa, funcionario);
        if (verificarususario != null) {
            throw new RuntimeException("Usernaem já cadastrado!");
        }
        var senhaHASH = passwordEncoder.encode(dados.senha());

        Usuario novoUsuario = new Usuario(dados, empresa , funcionario, senhaHASH);
        usuarioRepositiry.save(novoUsuario);
        return novoUsuario;

    }
}
