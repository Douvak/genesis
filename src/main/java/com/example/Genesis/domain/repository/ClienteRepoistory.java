package com.example.Genesis.domain.repository;

import com.example.Genesis.domain.dto.ListaClientesDTO;
import com.example.Genesis.domain.entity.Cliente;
import com.example.Genesis.domain.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepoistory extends JpaRepository<Cliente, Long> {
    List<Cliente> findByEmpresaId(Long id);
}
