package com.nts.aicommerce.cliente;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    
    @Id
    private UUID clienteId = UUID.randomUUID();

    @NotBlank
    private String nome;

    private String email;

    @NotBlank
    private String endereco;

    @NotBlank
    private String senha;
}