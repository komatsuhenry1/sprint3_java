package com.nts.aicommerce.pedido;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.nts.aicommerce.cliente.Cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedidoId;

    @DateTimeFormat(pattern = "DD/MM/YYYY")
    private LocalDate dataPedido;

    private Double precoTotal;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}