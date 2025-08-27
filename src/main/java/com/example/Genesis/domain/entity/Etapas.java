package com.example.Genesis.domain.entity;

import com.example.Genesis.domain.dto.NovaEtapaDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Etapas")
@Table(name = "etapas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Etapas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tipo_entrada")
    @Enumerated(EnumType.STRING)
    private Etapa tipoEtapa;
    private LocalDateTime inicio;
    private LocalDateTime finalizado;


    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "id",nullable = false)
    private Empresa empresa;
    @ManyToOne
    @JoinColumn(name = "ordem_id", referencedColumnName = "id", nullable = false)
    private OrdemDeServico ordemDeServico;
    @ManyToOne
    @JoinColumn(name = "funcionario_id", referencedColumnName = "id")
    private Funcionario funcionario;

    public Etapas(Empresa empresa, Funcionario funcionario, NovaEtapaDTO dados) {
    this.empresa = empresa;
    this.funcionario = funcionario;
    this.tipoEtapa = Etapa.valueOf(dados.etapa());
    this.inicio = LocalDateTime.now();

    }


    public enum Etapa{
        MEDIR,CORTE,PRODUCAO,MONTAGEM
    }
}
