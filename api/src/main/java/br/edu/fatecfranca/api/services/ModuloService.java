package br.edu.fatecfranca.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.fatecfranca.api.models.Modulo;
import br.edu.fatecfranca.api.repositories.ModuloRepository;

@Service
public class ModuloService {

    private final ModuloRepository repository;

    public ModuloService(ModuloRepository repository) {
        this.repository = repository;
    }

    public List<Modulo> listarTodos() {
        return repository.findAll();
    }

    public Optional<Modulo> buscarPorId(String id) {
        return repository.findById(id);
    }

    public Modulo salvar(Modulo modulo) {
        return repository.save(modulo);
    }

    public void excluir(String id) {
        repository.deleteById(id);
    }
}
