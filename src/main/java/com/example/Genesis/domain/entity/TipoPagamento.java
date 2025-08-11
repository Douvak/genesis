package com.example.Genesis.domain.entity;

import com.example.Genesis.domain.dto.TipoPagamentoDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity(name = "TipoPagamento")
@Table(name = "tipos_pagamento")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TipoPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float valor;
    @Column(name = "status_pagamento")
    private Boolean status;
    private LocalDateTime dataPagamento;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @ManyToMany(mappedBy = "tiposPagamentos")
    private List<Pedido> pedido = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "id", nullable = false)
    private Empresa empresa;

    public TipoPagamento(TipoPagamentoDTO dados, Pedido pedido, Empresa empresa) {
        this.dataPagamento = dados.dataPagamento();
        this.valor = dados.valor();
        this.status = dados.status();
        this.tipo = Tipo.valueOf(dados.tipo());
        this.pedido = new ArrayList<>();
        this.pedido.add(pedido);
        this.empresa = empresa;

    }


    private enum Tipo{
        PIX,DINHEIRO,CHEQUE,CARTAO
    }

}


