package com.software.software.business;

import com.software.software.domain.EntidadeDominio;
import com.software.software.domain.Fornecedor;

import java.sql.SQLException;

public class ValidarCEP implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade) throws SQLException {
        Fornecedor fornecedor = (Fornecedor) entidade;
        String cep = fornecedor.getEndereco().getCep();

        if (cep == null || cep.length() != 8) {
            return "CEP inválido, deve conter 8 dígitos";
        }

        return null;
    }
}
