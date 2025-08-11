package com.example.Genesis.domain.repository;

import com.example.Genesis.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByEmpresaId(Long empresa);
}
