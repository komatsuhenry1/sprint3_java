package com.nts.aicommerce.produto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nts.aicommerce.cliente.Cliente;


public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Page<Produto> findByCategoriaNome(String nome, Pageable pageable);

    Page<Produto> findByNomeContaining (String nome, Pageable pageable);

    Page<Produto> findByProdutoIdIn(List<Long> ids, Pageable pageable);
}
