package com.example.Genesis.domain.entity;

import com.example.Genesis.domain.dto.CriarUsuarioDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String senha;
    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "id", nullable = true)
    private Empresa empresa;
    @Enumerated(EnumType.STRING)
    private TipoDeConta papel;
    private Boolean ativo;
    @OneToOne
    @JoinColumn(name = "funcionario_id",referencedColumnName = "id", nullable = true)
    private Funcionario funcionario;

    public Usuario(@Valid CriarUsuarioDTO dados, Empresa empresa, Funcionario funcionario, String senhaHASH) {

        this.username = dados.username();
        this.senha = senhaHASH;
        this.empresa = empresa;
        this.papel = TipoDeConta.valueOf(dados.papel());
        this.ativo = true;
        this.funcionario = funcionario;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }


    public enum TipoDeConta{
        PRODUCAO,ADMIN,VENDAS,FINANCEIRO
    }
}
