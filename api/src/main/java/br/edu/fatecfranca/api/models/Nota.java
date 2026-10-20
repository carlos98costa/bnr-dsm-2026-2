package br.edu.fatecfranca.api.models;

import java.math.BigDecimal;

public class Nota {

    private String avaliacao;
    private BigDecimal valor;

    public Nota() {
    }

    public Nota(String avaliacao, BigDecimal valor) {
        this.avaliacao = avaliacao;
        this.valor = valor;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
