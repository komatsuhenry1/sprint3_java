package com.nts.aicommerce.pedido;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.nts.aicommerce.cliente.Cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pedidoId;

    @DateTimeFormat(pattern = "DD/MM/YYYY")
    private LocalDate dataPedido;

    private Double precoTotal;
    
    @ManyToOne
    private Cliente cliente;
}