package com.example.Genesis.infra.security;

import com.example.Genesis.domain.repository.UsuarioRepositiry;
import com.example.Genesis.domain.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private UsuarioRepositiry usuarioRepositiry;
    @Autowired
    private TokenService tokenService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("caiu no filtro");
        var tokenKWT = recuperarToken(request);
        if (tokenKWT != null){
            var subject = tokenService.validarToken(tokenKWT);
            var usuario = usuarioRepositiry.findByUsername(subject);
            System.out.println("token: "+ tokenKWT);
            var auth = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(auth);
            System.out.println("subject =>" + subject);

        }


        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {

        var authorizationHeadder = request.getHeader("Authorization");
        if (authorizationHeadder != null){
            return authorizationHeadder.replace("Bearer ","");

        }
        return null;
    }


}
