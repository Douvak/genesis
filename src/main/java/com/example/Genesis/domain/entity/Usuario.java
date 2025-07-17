package com.example.Genesis.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String senha;
    @OneToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "id", nullable = true)
    private Empresa empresa;
    private TipoDeConta papel;
    private Boolean ativo;
    @OneToOne
    @JoinColumn(name = "funcionario_id",referencedColumnName = "id", nullable = true)
    private Funcionario funcionario;



    public enum TipoDeConta{
        PRODUCAO,ADMIN,VENDAS,FINANCEIRO
    }
}
