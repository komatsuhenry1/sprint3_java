package com.nts.aicommerce.produto;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping("{id}")
    @ResponseStatus(FOUND)
    public ResponseEntity<Produto> getProduto(@PathVariable Long id) {

        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/many")
    @ResponseStatus(FOUND)
    public ResponseEntity<Page<Produto>> getProduto(@RequestParam List<Long> lista, Pageable pageable) {
        Page<Produto> produtos = repository.findByProdutoIdIn(lista, pageable);

        return ResponseEntity.ok(produtos);
    }

    @GetMapping
    @ResponseStatus(FOUND)
    public ResponseEntity<Page<Produto>> getProduto(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String nome,
            @PageableDefault(size = 5) Pageable pageable) {
                
        if (categoria != null) {
            return ResponseEntity.ok(repository.findByCategoriaNome(categoria, pageable));
        }

        if (nome != null) {
            return ResponseEntity.ok(repository.findByNomeContaining(nome, pageable));
        }

        Page<Produto> produtos = repository.findAll(pageable);
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Produto> createProduto(@RequestBody @Valid Produto Produto) {
        try {
            return ResponseEntity.ok(repository.save(Produto));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Produto> updateProduto(@RequestBody @Valid Produto Produto) {
        verify(Produto.getProdutoId());

        return ResponseEntity.ok(repository.save(Produto));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(OK)
    public ResponseEntity<String> deleteProduto(@PathVariable Long id) {
        verify(id);
        repository.deleteById(id);

        return ResponseEntity.ok("Produto apagado com sucesso");
    }

    /**
     * Verificação feita para os métodos de update e delete.
     * 
     * @param id
     * @throws ResponseStatusException
     *                                 Se entidade não fôr encontrada
     * @author
     *         Pedro Sena
     */
    private void verify(Long id) {

        boolean exists = repository.existsById(id);

        if (exists == false) {
            throw new EntityNotFoundException("Não encontrado");
        }
    }
}
