package com.example.Genesis.domain.entity;

import com.example.Genesis.domain.dto.OrdemDeServicoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "OrdemDeServico")
@Table(name = "ordens_servico")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class OrdemDeServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @Column(name = "status")
    private String statusAtual;
    private Float valor;


    @ManyToOne
    @JoinColumn(name = "empresa_id",referencedColumnName = "id",nullable = false)
    private Empresa empresa;
    @ManyToOne
    @JoinColumn(name = "pedido",referencedColumnName = "id",nullable = false)
    private Pedido pedido;

    @OneToMany(mappedBy = "ordemDeServico")
    private List<Arquivo> arquivos;


    public OrdemDeServico(OrdemDeServicoDTO dados, Pedido pedido, Empresa empresa) {
        this.descricao = dados.descricao();
        this.statusAtual = "Nova Ordem";
        this.valor = dados.valor();
        this.empresa = empresa;
        this.pedido = pedido;
    }
}
