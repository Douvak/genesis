package com.example.Genesis.domain.entity;


import com.example.Genesis.domain.dto.criarEmpresaDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Empresa")
@Table(name = "empresa")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode( of = "id")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cnpj;
    private String telefone;
    private String email;
    private String endereco;

    public Empresa(criarEmpresaDTO dados) {
        nome = dados.nome();
        cnpj = dados.cnpj();
        email = dados.email();
        telefone = dados.telefone();
        endereco = dados.endereco();
    }
}
