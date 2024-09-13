package com.nts.aicommerce.itensPedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedidoRepository extends JpaRepository<ItemsPedido, Long>{
    public List<ItemsPedido> findByPedidoPedidoId(Long id);
}
