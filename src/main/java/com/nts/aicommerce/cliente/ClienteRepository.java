package com.nts.aicommerce.cliente;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    public Optional<Cliente> findByEmail(String email);
}
