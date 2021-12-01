package com.software.software.business;

import com.software.software.dao.FornecedorDao;
import com.software.software.domain.EntidadeDominio;
import com.software.software.domain.Fornecedor;

import java.sql.SQLException;
import java.util.ArrayList;

public class ValidarUnicidadeCNPJ implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade) {
        Fornecedor fornecedor = (Fornecedor) entidade;

        FornecedorDao fornecedorDAO = new FornecedorDao();
        ArrayList forn = null;
        try {
            forn = (ArrayList) fornecedorDAO.consultar(new Fornecedor());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Object fornecedor1 : forn) {
            if (fornecedor.getCnpj().equals(((Fornecedor) fornecedor1).getCnpj())) {
                return "CNPJ já cadastrado";
            }
        }

        return null;
    }
}
