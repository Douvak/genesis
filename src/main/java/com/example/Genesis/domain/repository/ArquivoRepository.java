package com.example.Genesis.domain.repository;

import com.example.Genesis.domain.entity.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
}
