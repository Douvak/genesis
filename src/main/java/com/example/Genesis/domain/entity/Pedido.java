package com.example.Genesis.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Pedido")
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float valorTotal;
    @Column(name = "previsao_entrega")
    private LocalDateTime previsaoDeEntrega;


    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "id", nullable = false)
    private Empresa empresa;
    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = true)
    private Cliente cliente;
    @OneToMany(mappedBy = "pedido")
    private List<OrdemDeServico> ordemDeServicos;
    @ManyToMany
    @JoinTable(
            name = "pedido_tipo_pagamento",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_pagamento_id")
    )
    private List<TipoPagamento> tiposPagamentos = new ArrayList<>();



    public Pedido(Empresa empresa) {
        this.empresa = empresa;
        this.previsaoDeEntrega = LocalDateTime.now().plusDays(15);
        this.ordemDeServicos = new ArrayList<>();
    }
}
