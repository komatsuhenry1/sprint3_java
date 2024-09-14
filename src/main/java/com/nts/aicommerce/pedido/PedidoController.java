package com.nts.aicommerce.pedido;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(path = "/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository repository;

    @GetMapping("id/{id}")
    @ResponseStatus(FOUND)
    public ResponseEntity<Pedido> getPedido(@PathVariable Long id) {

        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("cliente/{nome}")
    public ResponseEntity<Page<Pedido>> getPedidoByCliente(@PathVariable String nome,
    @PageableDefault(size = 5, sort = "dataPedido", direction = Direction.DESC) Pageable pageable){
        Page<Pedido> pedidos = repository.findByClienteNome(nome, pageable);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> getPedidos() {
        List<Pedido> pedidos = repository.findAll();
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Pedido> createPedido(@RequestBody @Valid Pedido pedido) {
        try {
            return ResponseEntity.ok(repository.save(pedido));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    public ResponseEntity<Pedido> updatePedido(@RequestBody Pedido pedido) {
        verify(pedido.getPedidoId());

        return ResponseEntity.ok(repository.save(pedido));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(OK)
    public ResponseEntity<String> deletePedido(@PathVariable Long id) {
        verify(id);
        repository.deleteById(id);

        return ResponseEntity.ok("Pedido apagado com sucesso");
    }

     /**
     * Verificação feita para os métodos de update e delete.
     * @param id
     * @throws ResponseStatusException
     * Se entidade não fôr encontrada
     * @author 
     * Pedro Sena
     */
    private void verify(Long id) {

        boolean exists = repository.existsById(id);

        if (exists == false) {
            throw new EntityNotFoundException("Não encontrado");
        }
    }
}
