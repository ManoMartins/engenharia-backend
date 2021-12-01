package com.software.software.dao;

import com.software.software.domain.Fornecedor;

public class TestExcluirDao {
    public static void main(String[] args) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(2);

        FornecedorDao fornecedorDao = new FornecedorDao();

        fornecedorDao.excluir(fornecedor);
    }
}
