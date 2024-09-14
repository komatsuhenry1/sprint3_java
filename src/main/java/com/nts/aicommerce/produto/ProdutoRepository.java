package com.nts.aicommerce.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Page<Produto> findByCategoriaNome(String nome, Pageable pageable);

    Page<Produto> findByNomeContaining (String nome, Pageable pageable);
}
