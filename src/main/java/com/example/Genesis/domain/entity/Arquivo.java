package com.example.Genesis.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Arquivo")
@Table(name = "arquivos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Arquivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String arquivoLink;

    @ManyToOne
    @JoinColumn(name = "ordem_servico_id", referencedColumnName = "id", nullable = false)
    private OrdemDeServico ordemDeServico;
}
