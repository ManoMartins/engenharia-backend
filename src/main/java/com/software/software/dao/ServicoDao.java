package com.software.software.dao;

import com.software.software.domain.*;

import java.sql.*;
import java.util.ArrayList;

public class ServicoDao extends AbstractDao {
    public ServicoDao(Connection connection) {
        super(connection, "servicos", "id");
    }

    public ServicoDao() {
        super("servicos", "id");
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
        if (connection == null) {
            openConnection();
        }

        PreparedStatement pst = null;
        Servico servico = (Servico) entidade;
        StringBuilder sql = new StringBuilder();

        sql.append("insert into servicos ( ");
        sql.append("for_id, ");
        sql.append("codigo, ");
        sql.append("descricao, ");
        sql.append("data_inicio, ");
        sql.append("created_at ");
        sql.append(") ");
        sql.append("values (?, ?, ?, ?, ?)");

        try {
            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, servico.getFornecedor().getId());
            pst.setString(2, servico.getCodigo());
            pst.setString(3, servico.getDescricao());
            pst.setString(4, servico.getDataInicio());
            pst.setDate(5, (Date) servico.getCreatedAt());

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();

            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            servico.setId(id);

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (ctrlTransaction) {
                try {
                    pst.close();
                    if (ctrlTransaction) connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void alterar(EntidadeDominio entidade) throws SQLException {
        if (connection == null) {
            openConnection();
        }

        PreparedStatement pst = null;
        Servico servico = (Servico) entidade;
        StringBuilder sql = new StringBuilder();

        sql.append("update servicos set ");
        sql.append("codigo=?, ");
        sql.append("descricao=?, ");
        sql.append("data_inicio=? ");
        sql.append("where id = ?");

        try {
            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, servico.getCodigo());
            pst.setString(2, servico.getDescricao());
            pst.setString(3, servico.getDataInicio());
            pst.setInt(4, servico.getId());

            pst.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (ctrlTransaction) {
                try {
                    pst.close();
                    if (ctrlTransaction) connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public ArrayList<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
        if (connection == null) {
            openConnection();
        }

        PreparedStatement pst = null;

        Servico servico = (Servico) entidade;

        StringBuilder sql = new StringBuilder();
        ArrayList<EntidadeDominio> servicos = new ArrayList<>();

        sql.append("select * from servicos");

        if (servico.getFornecedor().getId() != 0) {
            sql.append(" where for_id = ?");
        }

        try {
            pst = connection.prepareStatement(sql.toString());

            if (servico.getFornecedor().getId() != 0) {
                pst.setInt(1, servico.getFornecedor().getId());
            }

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Servico p = new Servico();
                p.setId(rs.getInt("id"));
                p.setCodigo(rs.getString("codigo"));
                p.setDescricao(rs.getString("descricao"));
                p.setFornecedor(servico.getFornecedor());
                p.setDataInicio(rs.getString("data_inicio"));
                p.setCreatedAt(rs.getDate("created_at"));
                servicos.add(p);
            }

            return servicos;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ctrlTransaction) {
                try {
                    pst.close();
                    if (ctrlTransaction) connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
