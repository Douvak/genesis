package com.example.Genesis.domain.entity;

import com.example.Genesis.domain.dto.ClienteDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Cliente")
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String contato;
    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "id", nullable = false)
    private Empresa empresa;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    public Cliente(ClienteDTO dados, Empresa empresa) {
        this.contato = dados.contato();
        this.nome = dados.nome();
        this.empresa = empresa;
    }
}
