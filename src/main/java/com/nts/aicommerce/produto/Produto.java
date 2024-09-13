package com.nts.aicommerce.produto;

import com.nts.aicommerce.categoria.Categoria;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long produtoId;

    @NotBlank
    private String nome;

    @Size(max = 255)
    private String descricao;

    @Positive
    private Double preco;

    @ManyToOne
    private Categoria categoria;
}