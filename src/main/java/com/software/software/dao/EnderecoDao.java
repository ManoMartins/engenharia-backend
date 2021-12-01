package com.software.software.dao;

import com.software.software.domain.*;

import java.sql.*;
import java.util.ArrayList;

public class EnderecoDao extends AbstractDao {
    public EnderecoDao(Connection connection) {
        super(connection, "enderecos", "id");
    }

    public EnderecoDao() {
        super("enderecos", "id");
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
        if (connection == null) {
            openConnection();
        }

        PreparedStatement pst = null;
        Endereco endereco = (Endereco) entidade;
        StringBuilder sql = new StringBuilder();

        sql.append("insert into enderecos ( ");
        sql.append("logradouro, ");
        sql.append("numero, ");
        sql.append("cep, ");
        sql.append("bairro, ");
        sql.append("complemento, ");
        sql.append("cidade, ");
        sql.append("estado, ");
        sql.append("pais ");
        sql.append(") ");
        sql.append("values (?, ?, ?, ?, ?, ?, ?, ?)");

        try {
            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, endereco.getLogradouro());
            pst.setString(2, endereco.getNumero());
            pst.setString(3, endereco.getCep());
            pst.setString(4, endereco.getBairro());
            pst.setString(5, endereco.getComplemento());
            pst.setString(6, endereco.getCidade().getDescricao());
            pst.setString(7, endereco.getCidade().getEstado().getDescricao());
            pst.setString(8, endereco.getCidade().getEstado().getPais().getDescricao());

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();

            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            endereco.setId(id);

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
        Endereco endereco = (Endereco) entidade;
        StringBuilder sql = new StringBuilder();

        sql.append("update enderecos set ");
        sql.append("logradouro=?, ");
        sql.append("numero=?, ");
        sql.append("cep=?, ");
        sql.append("bairro=?, ");
        sql.append("complemento=?, ");
        sql.append("cidade=?, ");
        sql.append("estado=?, ");
        sql.append("pais=? ");
        sql.append("where id=?");

        try {
            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, endereco.getLogradouro());
            pst.setString(2, endereco.getNumero());
            pst.setString(3, endereco.getCep());
            pst.setString(4, endereco.getBairro());
            pst.setString(5, endereco.getComplemento());
            pst.setString(6, endereco.getCidade().getDescricao());
            pst.setString(7, endereco.getCidade().getEstado().getDescricao());
            pst.setString(8, endereco.getCidade().getEstado().getPais().getDescricao());
            pst.setInt(9, endereco.getId());

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

        sql.append("select * from enderecos");

        if (entidade.getId() != 0) {
            sql.append(" where id = ?");
        }

        try {
            pst = connection.prepareStatement(sql.toString());

            if (entidade.getId() != 0) {
                pst.setInt(1, entidade.getId());
            }

            ResultSet rs = pst.executeQuery();

            ArrayList<EntidadeDominio> enderecos = new ArrayList<>();

            while (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("id"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setCep(rs.getString("cep"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setComplemento(rs.getString("complemento"));
                Pais pais = new Pais();
                pais.setDescricao(rs.getString("pais"));
                Estado estado = new Estado();
                estado.setDescricao(rs.getString("estado"));
                estado.setPais(pais);
                Cidade cidade = new Cidade();
                cidade.setDescricao(rs.getString("cidade"));
                cidade.setEstado(estado);
                endereco.setCidade(cidade);

                enderecos.add(endereco);
            }

            return enderecos;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                if (ctrlTransaction) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
