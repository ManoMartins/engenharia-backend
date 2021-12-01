package com.software.software.domain;

public class Departamento {
    private String descricao;

    public Departamento() {
    }

    public Departamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
