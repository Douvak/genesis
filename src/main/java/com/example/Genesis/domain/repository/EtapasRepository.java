package com.example.Genesis.domain.repository;

import com.example.Genesis.domain.dto.EtapasDTO;
import com.example.Genesis.domain.entity.Etapas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtapasRepository extends JpaRepository<Etapas, Long> {
    List<Etapas> findByOrdemDeServico_Id(Long ordemID);
}
