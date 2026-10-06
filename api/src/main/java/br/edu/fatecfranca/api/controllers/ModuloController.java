package br.edu.fatecfranca.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fatecfranca.api.models.Modulo;
import br.edu.fatecfranca.api.services.ModuloService;

@RestController
@RequestMapping("/modulos")
public class ModuloController {

    private final ModuloService service;

    public ModuloController(ModuloService service) {
        this.service = service;
    }

    @GetMapping
    public List<Modulo> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modulo> buscarPorId(@PathVariable String id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Modulo> criar(@RequestBody Modulo modulo) {
        Modulo moduloSalvo = service.salvar(modulo);
        return ResponseEntity.status(HttpStatus.CREATED).body(moduloSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modulo> atualizar(@PathVariable String id, @RequestBody Modulo modulo) {
        if (service.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        modulo.setId(id);
        return ResponseEntity.ok(service.salvar(modulo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable String id) {
        if (service.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
