package com.example.Genesis.domain.entity;

import com.example.Genesis.domain.dto.FuncionarioDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Funcionario")
@Table(name = "funcionarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cargo;
    private String email;
    private LocalDateTime data_admissao;


    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "id", nullable = false)
    private Empresa empresa;
    @OneToOne(mappedBy = "funcionario")
    private Usuario usuario;

    public Funcionario(FuncionarioDTO dados, Empresa empresa) {
        this.nome = dados.nome();
        this.cargo = dados.cargo();
        this.email = dados.email();
        this.empresa = empresa;
        this.data_admissao = LocalDateTime.now();
    }
}
