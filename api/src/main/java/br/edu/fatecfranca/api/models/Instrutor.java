package br.edu.fatecfranca.api.models;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "instrutores")
public class Instrutor {

    @Id
    private String id;

    private String nome;
    private String cpf;
    private String email;
    private BigDecimal valorHoraAula;
    private List<String> especialidades;
    private List<Endereco> enderecos;

    public Instrutor() {
    }

    public Instrutor(
            String id,
            String nome,
            String cpf,
            String email,
            BigDecimal valorHoraAula,
            List<String> especialidades,
            List<Endereco> enderecos) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.valorHoraAula = valorHoraAula;
        this.especialidades = especialidades;
        this.enderecos = enderecos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getValorHoraAula() {
        return valorHoraAula;
    }

    public void setValorHoraAula(BigDecimal valorHoraAula) {
        this.valorHoraAula = valorHoraAula;
    }

    public List<String> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<String> especialidades) {
        this.especialidades = especialidades;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
