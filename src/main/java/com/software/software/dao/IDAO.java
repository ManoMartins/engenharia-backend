package com.software.software.dao;

import com.software.software.domain.EntidadeDominio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IDAO {
    void salvar(EntidadeDominio entidade) throws SQLException;

    void alterar(EntidadeDominio entidade) throws SQLException;

    void excluir(EntidadeDominio entidade) throws SQLException;

    ArrayList<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException;
}
