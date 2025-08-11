package com.example.Genesis.domain.repository;

import com.example.Genesis.domain.entity.TipoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentosRepository extends JpaRepository<TipoPagamento , Long> {

}
