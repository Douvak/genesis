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
    @Column(name = "tipo_etapa")
    @Enumerated(EnumType.STRING)
    private Etapa tipoEtapa;
    private String motivo;
    private LocalDateTime inicio;
    private LocalDateTime finalizado;
    @Column(name = "pedido_id")
    private Long pedidoID;


    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    private Empresa empresa;
    @ManyToOne
    @JoinColumn(name = "ordem_id", referencedColumnName = "id", nullable = false)
    private OrdemDeServico ordemDeServico;
    @ManyToOne
    @JoinColumn(name = "funcionario_id", referencedColumnName = "id")
    private Funcionario funcionario;

    public Etapas(OrdemDeServico ordem, NovaEtapaDTO dados) {
    this.ordemDeServico = ordem;
    this.tipoEtapa = Etapa.valueOf(dados.etapa());
    this.inicio = LocalDateTime.now();
    this.motivo = dados.motivo();

    }


    public enum Etapa{
        MEDIR,CORTE,PRODUCAO,MONTAGEM,PAUSADO
    }
}
