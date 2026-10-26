package br.edu.fatecfranca.api.services;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.fatecfranca.api.dtos.TurmaResponse;
import br.edu.fatecfranca.api.models.Curso;
import br.edu.fatecfranca.api.models.Instrutor;
import br.edu.fatecfranca.api.models.Turma;
import br.edu.fatecfranca.api.repositories.CursoRepository;
import br.edu.fatecfranca.api.repositories.InstrutorRepository;
import br.edu.fatecfranca.api.repositories.TurmaRepository;

@Service
public class TurmaService {

    private final TurmaRepository repository;
    private final CursoRepository cursoRepository;
    private final InstrutorRepository instrutorRepository;

    public TurmaService(
        TurmaRepository repository,
        CursoRepository cursoRepository,
        InstrutorRepository instrutorRepository
    ) {
        this.repository = repository;
        this.cursoRepository = cursoRepository;
        this.instrutorRepository = instrutorRepository;
    }

    private TurmaResponse montarResposta(Turma turma) {
        Curso curso = cursoRepository.findById(turma.getCursoId())
                .orElse(null);

        Instrutor instrutor = turma.getInstrutorId() == null
                || turma.getInstrutorId().isBlank()
                        ? null
                        : instrutorRepository.findById(turma.getInstrutorId())
                                .orElse(null);

        return new TurmaResponse(
                turma.getId(),
                turma.getCursoId(),
                turma.getInstrutorId(),
                turma.getDataInicio(),
                turma.getDataFim(),
                turma.getNumVagas(),
                turma.getHorarios(),
                curso,
                instrutor);
    }

    public List<TurmaResponse> listarComDetalhes() {
        return repository.findAll().stream()
                .map(this::montarResposta)
                .toList();
    }

    public Optional<TurmaResponse> buscarComDetalhes(String id) {
        return repository.findById(id)
                .map(this::montarResposta);
    }

    public List<TurmaResponse> listarPorCursoComDetalhes(String cursoId) {
        return repository.findByCursoId(cursoId).stream()
                .map(this::montarResposta)
                .toList();
    }


    public List<Turma> listarTodos() {
        return repository.findAll();
    }

    public Optional<Turma> buscarPorId(String id) {
        return repository.findById(id);
    }

    public Turma salvar(Turma turma) {
        return repository.save(turma);
    }

    public void excluir(String id) {
        repository.deleteById(id);
    }
}
