package com.example.Genesis.infra.security;

import com.example.Genesis.domain.repository.UsuarioRepositiry;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class AutenticationService implements UserDetailsService {
    @Autowired
    private final UsuarioRepositiry usuarioRepositiry;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepositiry.findByUsername(username);
    }
}
