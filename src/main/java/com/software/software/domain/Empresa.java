package com.software.software.domain;

import java.sql.Date;

public class Empresa extends EntidadeDominio {
    private TipoEmpresa tipoEmpresa;
    private transient Fornecedor fornecedor;

    public Empresa() {
    }

    public TipoEmpresa getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(TipoEmpresa tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
