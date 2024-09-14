package com.nts.aicommerce.cliente;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @GetMapping("{id}")
    @ResponseStatus(FOUND)
    public ResponseEntity<Cliente> getUser(@PathVariable Long id) {

        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Cliente> createUser(@RequestBody @Valid Cliente cliente) {
        try {
            return ResponseEntity.ok(repository.save(cliente));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Cliente> updateUser(@RequestBody @Valid Cliente cliente) {
        verify(cliente.getClienteId());

        return ResponseEntity.ok(repository.save(cliente));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(OK)
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        verify(id);
        repository.deleteById(id);

        return ResponseEntity.ok("Perfil apagado com sucesso");
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