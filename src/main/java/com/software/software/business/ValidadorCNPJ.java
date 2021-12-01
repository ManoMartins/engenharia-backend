package com.software.software.business;

import com.software.software.domain.EntidadeDominio;
import com.software.software.domain.Fornecedor;

public class ValidadorCNPJ implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {
        Fornecedor fornecedor = (Fornecedor) entidade;
        String cnpj = fornecedor.getCnpj();

        if (cnpj == null || cnpj.length() != 14) {
            return "CNPJ inválido, deve conter 14 dígitos";
        }

        return null;
    }
}
