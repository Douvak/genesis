package com.example.Genesis.domain.repository;

import com.example.Genesis.domain.entity.Empresa;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Empresa findByCnpj(@NotNull @Size(min = 20, max = 20) String cnpj);
}
