package com.software.software.dao;

import com.software.software.domain.Cnae;
import com.software.software.domain.EntidadeDominio;

import java.sql.*;
import java.util.ArrayList;

public class CnaeDao extends AbstractDao {
    public CnaeDao(Connection connection) {
        super(connection, "cnaes", "id");
    }

    public CnaeDao() {
        super("cnaes", "id");
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
        if (connection == null) {
            openConnection();
        }

        PreparedStatement pst = null;
        Cnae cnae = (Cnae) entidade;
        StringBuilder sql = new StringBuilder();

        sql.append("insert into cnaes (numero, for_id, created_at) values (?, ?, ?)");

        try {
            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, cnae.getNumero());
            pst.setInt(2, cnae.getFornecedor().getId());
            pst.setDate(3, (Date) cnae.getCreatedAt());

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();

            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            cnae.setId(id);
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
        Cnae cnae = (Cnae) entidade;
        StringBuilder sql = new StringBuilder();

        sql.append("update cnaes set numero=? where id=?");

        try {
            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, cnae.getNumero());
            pst.setInt(2, cnae.getId());

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

        Cnae cnae = (Cnae) entidade;
        PreparedStatement pst = null;

        StringBuilder sql = new StringBuilder();

        sql.append("select * from cnaes c");

        if (cnae.getFornecedor().getId() != 0) {
            sql.append(" where c.for_id = ?");
        }

        ArrayList<EntidadeDominio> cnaes = new ArrayList<>();

        try {
            pst = connection.prepareStatement(sql.toString());

            if (cnae.getFornecedor().getId() != 0) {
                pst.setInt(1, cnae.getFornecedor().getId());
            }
            ResultSet rs = pst.executeQuery();


            while (rs.next()) {
                Cnae cnaeConsultado = new Cnae();
                cnaeConsultado.setId(rs.getInt("id"));
                cnaeConsultado.setNumero(rs.getString("numero"));
                cnaeConsultado.setFornecedor(cnae.getFornecedor());
                cnaeConsultado.setCreatedAt(rs.getDate("created_at"));

                cnaes.add(cnaeConsultado);
            }

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

        return cnaes;
    }
}
