package com.nts.aicommerce.itensPedido;

import com.nts.aicommerce.pedido.Pedido;
import com.nts.aicommerce.produto.Produto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "items_pedido")
public class ItemsPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemsId;

    private int quantidade;

    private Double preco;

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Produto produto;
}