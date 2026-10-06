package br.edu.fatecfranca.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "modulos")
public class Modulo {

    @Id
    private String id;

    private String titulo;
    private String conteudo;
    private Integer cargaHoraria;

    public Modulo() {
    }

    public Modulo(String id, String titulo, String conteudo, Integer cargaHoraria) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.cargaHoraria = cargaHoraria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
}
