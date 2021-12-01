package com.software.software.dao;

import com.software.software.domain.EntidadeDominio;
import com.software.software.domain.Telefone;
import com.software.software.domain.TipoTelefone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelefoneDao extends AbstractDao {
    public TelefoneDao(Connection connection) {
        super(connection, "telefones", "id");
    }

    public TelefoneDao() {
        super("telefones", "id");
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
        if (connection == null) {
            openConnection();
        }

        PreparedStatement pst = null;
        Telefone telefone = (Telefone) entidade;
        StringBuilder sql = new StringBuilder();

        sql.append("insert into telefones (ddd, numero, tipo, ddi) values (?, ?, ?, ?)");

        try {
            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, telefone.getDdd());
            pst.setString(2, telefone.getNumero());
            pst.setString(3, telefone.getTipoTelefone().getDescricao());
            pst.setString(4, telefone.getDdi());

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();

            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            telefone.setId(id);
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
        Telefone telefone = (Telefone) entidade;
        StringBuilder sql = new StringBuilder();

        sql.append("update telefones set ddd=?, numero=?, tipo=?, ddi=? where id=?");

        try {
            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, telefone.getDdd());
            pst.setString(2, telefone.getNumero());
            pst.setString(3, telefone.getTipoTelefone().getDescricao());
            pst.setString(4, telefone.getDdi());
            pst.setInt(5, telefone.getId());

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

        StringBuilder sql = new StringBuilder();
        sql.append("select * from telefones");

        if (entidade.getId() != 0) {
            sql.append(" where id = ?");
        }

        try {
            pst = connection.prepareStatement(sql.toString());

            if (entidade.getId() != 0) {
                pst.setInt(1, entidade.getId());
            }

            ResultSet rs = pst.executeQuery();

            ArrayList<EntidadeDominio> telefones = new ArrayList<>();

            while(rs.next()){
                Telefone telefone = new Telefone();
                telefone.setId(rs.getInt("id"));
                telefone.setDdd(rs.getString("ddd"));
                telefone.setDdi(rs.getString("ddi"));
                telefone.setNumero(rs.getString("numero"));
                TipoTelefone tipoTelefone = new TipoTelefone();
                tipoTelefone.setDescricao(rs.getString("tipo"));
                telefone.setTipoTelefone(tipoTelefone);

                telefones.add(telefone);
            }

            return telefones;
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
