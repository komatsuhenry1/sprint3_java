package com.nts.aicommerce.itensPedido;

import com.nts.aicommerce.pedido.Pedido;
import com.nts.aicommerce.produto.Produto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "items_pedido")
public class ItemsPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemsId;

    private int quantidade;

    private Double preco;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
}