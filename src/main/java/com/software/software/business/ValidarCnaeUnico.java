package com.software.software.business;

import com.software.software.domain.Cnae;
import com.software.software.domain.EntidadeDominio;
import com.software.software.domain.Fornecedor;

import java.sql.SQLException;
import java.util.ArrayList;

public class ValidarCnaeUnico implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) throws SQLException {
        Fornecedor fornecedor = (Fornecedor) entidade;
        ArrayList<Cnae> cnaes = fornecedor.getCnaes();

        for (int i = 0; i < cnaes.size(); i++) {
            for (int j = i + 1; j < cnaes.size(); j++) {
                if (cnaes.get(i).getNumero().equals(cnaes.get(j).getNumero()) && i != j) {
                    return "O Cnae " + cnaes.get(i).getNumero() + " já está incluso na lista";
                }
            }
        }

        return null;
    }
}
