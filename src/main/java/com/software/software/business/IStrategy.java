package com.software.software.business;

import com.software.software.domain.EntidadeDominio;

import java.sql.SQLException;

public interface IStrategy {
    public String processar(EntidadeDominio entidade) throws SQLException;
}
