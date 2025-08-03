package com.example.Genesis.domain.repository;

import com.example.Genesis.domain.entity.Empresa;
import com.example.Genesis.domain.entity.Funcionario;
import com.example.Genesis.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepositiry extends JpaRepository<Usuario, Long> {
    UserDetails findByUsername(String username);



    Usuario findByUsernameAndEmpresaAndFuncionario(String username, Empresa empresa, Funcionario funcionario);
}
