package com.software.software.domain;

import java.sql.Date;

public class Cnae extends EntidadeDominio {
    private String numero;
    private transient Fornecedor fornecedor;

    public Cnae() {
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
