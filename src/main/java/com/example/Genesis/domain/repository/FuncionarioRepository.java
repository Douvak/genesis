package com.example.Genesis.domain.repository;

import com.example.Genesis.domain.entity.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {


    List<Funcionario> findByEmpresaId(Long empresaID);

    List<Funcionario> findAllByEmpresaId(Long empresaID);
}
