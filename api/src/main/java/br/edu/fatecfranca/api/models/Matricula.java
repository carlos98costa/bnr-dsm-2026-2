package br.edu.fatecfranca.api.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "matriculas")
public class Matricula {

    @Id
    private String id;

    private String alunoId;
    private String turmaId;
    private String status;
    private List<Nota> notas;

    public Matricula() {
    }

    public Matricula(String id, String alunoId, String turmaId, String status, List<Nota> notas) {
        this.id = id;
        this.alunoId = alunoId;
        this.turmaId = turmaId;
        this.status = status;
        this.notas = notas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(String alunoId) {
        this.alunoId = alunoId;
    }

    public String getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(String turmaId) {
        this.turmaId = turmaId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
}
