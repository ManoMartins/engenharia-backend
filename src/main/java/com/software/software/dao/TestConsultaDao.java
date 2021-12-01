package com.software.software.dao;

import com.software.software.domain.Fornecedor;

import java.sql.SQLException;

public class TestConsultaDao {
    public static void main(String[] args) {
        Fornecedor fornecedor = new Fornecedor();

        fornecedor.setId(15);

        FornecedorDao fornecedorDao = new FornecedorDao();
        try {
            fornecedorDao.consultar(fornecedor);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
