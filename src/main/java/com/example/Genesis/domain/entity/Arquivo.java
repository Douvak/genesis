package com.example.Genesis.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Arquivo")
@Table(name = "arquivos_ordem")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Arquivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rota;

    @ManyToOne
    @JoinColumn(name = "ordem_pedido_id", referencedColumnName = "id", nullable = false)
    private OrdemDeServico ordemDeServico;

    public Arquivo(String caminho, OrdemDeServico ordem) {

        this.ordemDeServico = ordem;
        this.rota = caminho;
    }
}
