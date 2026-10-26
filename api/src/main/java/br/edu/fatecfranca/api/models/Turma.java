package br.edu.fatecfranca.api.models;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;


import com.fasterxml.jackson.annotation.JsonIgnore;


@Document(collection = "turmas")
public class Turma {

    @Id
    private String id;

    private String cursoId;
    private String instrutorId;

    @NotNull(message = "A data de início da turma é obrigatória")
    private LocalDate dataInicio;

    @NotNull(message = "A data de fim da turma é obrigatória")
    private LocalDate dataFim;

    @NotNull (message = "O número de vagas da turma é obrigatório")
    @Positive(message = "O número de vagas deve ser maior que zero")
    private Integer numVagas;

    @NotBlank(message = "O status da turma é obrigatório")
    @Pattern(
        regexp = "PREVISTA|ATIVA|ENCERRADA|CANCELADA",
        message = "O status deve ser PREVISTA, ATIVA, ENCERRADA ou CANCELADA"
    )
    private String status;

    private List<Horario> horarios;

    public Turma() {
    }

    public Turma(
            String id,
            String cursoId,
            String instrutorId,
            LocalDate dataInicio,
            LocalDate dataFim,
            Integer numVagas,
            String status,
            List<Horario> horarios) {
        this.id = id;
        this.cursoId = cursoId;
        this.instrutorId = instrutorId;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.numVagas = numVagas;
        this.status = status;
        this.horarios = horarios;
    }

    @JsonIgnore
    @AssertTrue(message = "A data de início deve ser anterior à data de fim")
    public boolean isPeriodoValido() {
        if (dataInicio == null || dataFim == null) {
            return true; // A validação de @NotNull cuidará disso
        }

        return !dataFim.isBefore(dataInicio);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCursoId() {
        return cursoId;
    }

    public void setCursoId(String cursoId) {
        this.cursoId = cursoId;
    }

    public String getInstrutorId() {
        return instrutorId;
    }

    public void setInstrutorId(String instrutorId) {
        this.instrutorId = instrutorId;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getNumVagas() {
        return numVagas;
    }

    public void setNumVagas(Integer numVagas) {
        this.numVagas = numVagas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }
}
