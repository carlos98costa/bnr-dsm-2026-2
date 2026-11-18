package br.edu.fatecfranca.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.fatecfranca.api.dtos.MatriculaResponse;
import br.edu.fatecfranca.api.models.Matricula;
import br.edu.fatecfranca.api.models.Turma;
import br.edu.fatecfranca.api.repositories.AlunoRepository;
import br.edu.fatecfranca.api.repositories.CursoRepository;
import br.edu.fatecfranca.api.repositories.MatriculaRepository;
import br.edu.fatecfranca.api.repositories.TurmaRepository;

@Service
public class MatriculaService {

    private final MatriculaRepository repository;
    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;
    private final CursoRepository cursoRepository;
    private final TurmaService turmaService;

    public MatriculaService(
            MatriculaRepository repository,
            AlunoRepository alunoRepository,
            TurmaRepository turmaRepository,
            CursoRepository cursoRepository,
            TurmaService turmaService) {
        this.repository = repository;
        this.alunoRepository = alunoRepository;
        this.turmaRepository = turmaRepository;
        this.cursoRepository = cursoRepository;
        this.turmaService = turmaService;
    }

    private void verificarAluno(String alunoId) {
        if (alunoId == null || alunoId.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O identificador do aluno é obrigatório.");
        }
        if (!alunoRepository.existsById(alunoId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Aluno não encontrado.");
        }
    }

    private void verificarTurma(String turmaId) {
        if (turmaId == null || turmaId.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O identificador da turma é obrigatório.");
        }
        if (!turmaRepository.existsById(turmaId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Turma não encontrada.");
        }
    }

    private void verificarCurso(String cursoId) {
        if (cursoId == null || cursoId.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O identificador do curso é obrigatório.");
        }
        if (!cursoRepository.existsById(cursoId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Curso não encontrado.");
        }
    }

    private MatriculaResponse montarResposta(Matricula matricula) {
        var aluno = alunoRepository.findById(matricula.getAlunoId()).orElse(null);
        var turma = turmaService.buscarComDetalhes(matricula.getTurmaId()).orElse(null);

        return new MatriculaResponse(
                matricula.getId(),
                matricula.getAlunoId(),
                matricula.getTurmaId(),
                matricula.getStatus(),
                matricula.getNotas(),
                aluno,
                turma);
    }

    public List<MatriculaResponse> listarComDetalhes() {
        return repository.findAll().stream()
                .map(this::montarResposta)
                .toList();
    }

    public Optional<MatriculaResponse> buscarComDetalhes(String id) {
        return repository.findById(id)
                .map(this::montarResposta);
    }

    public List<MatriculaResponse> listarPorAlunoComDetalhes(String alunoId) {
        verificarAluno(alunoId);
        return repository.findByAlunoId(alunoId).stream()
                .map(this::montarResposta)
                .toList();
    }

    public List<MatriculaResponse> listarPorCursoComDetalhes(String cursoId) {
        verificarCurso(cursoId);
        List<String> turmaIds = turmaRepository.findByCursoId(cursoId).stream()
                .map(Turma::getId)
                .toList();
        return repository.findByTurmaIdIn(turmaIds).stream()
                .map(this::montarResposta)
                .toList();
    }

    public Optional<Matricula> buscarPorId(String id) {
        return repository.findById(id);
    }

    public Matricula salvar(Matricula matricula) {
        verificarAluno(matricula.getAlunoId());
        verificarTurma(matricula.getTurmaId());
        return repository.save(matricula);
    }

    public void excluir(String id) {
        repository.deleteById(id);
    }
}
