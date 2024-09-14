package com.nts.aicommerce.auth;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.nts.aicommerce.cliente.Cliente;
import com.nts.aicommerce.cliente.ClienteRepository;

@Service
public class TokenService {

    private ClienteRepository clienteRepository;
    private Algorithm algorithm;

    public TokenService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
        this.algorithm = Algorithm.HMAC256("secretTokenAlgorith58256421");
    }

    public Token create(Credentials credentials) {
        var expiresAt = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.ofHours(-3));
        var token = JWT.create()
                .withSubject(credentials.email())
                .withExpiresAt(expiresAt)
                .sign(algorithm);
        return new Token(token, credentials.email());
    }

    public Cliente getClienteFromToken(String token) {
        var email = JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();

        return clienteRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }
}