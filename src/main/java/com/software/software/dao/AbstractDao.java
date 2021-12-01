package com.software.software.dao;

import com.software.software.domain.EntidadeDominio;
import com.software.software.utils.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractDao implements IDAO {
    protected Connection connection;
    protected String table;
    protected String idTable;
    protected boolean ctrlTransaction = true;

    public AbstractDao(Connection connection, String table, String idTable) {
        this.connection = connection;
        this.table = table;
        this.idTable = idTable;
    }

    public AbstractDao(String table, String idTable) {
        this.table = table;
        this.idTable = idTable;
    }

    public void excluir(EntidadeDominio entidade) {
        openConnection();
        PreparedStatement pst = null;
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(table);
        sb.append(" WHERE ");
        sb.append(idTable);
        sb.append("=");
        sb.append("?");

        try {
            connection.setAutoCommit(false);
            pst = connection.prepareStatement(sb.toString());
            pst.setInt(1, entidade.getId());
            pst.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try{
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                pst.close();
                if(ctrlTransaction) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void openConnection() {
        try {
            if(connection == null || connection.isClosed()){
                connection = Conexao.getConnectionPostgres();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
