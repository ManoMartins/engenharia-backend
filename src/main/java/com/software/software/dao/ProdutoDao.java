package com.software.software.dao;

import com.software.software.domain.EntidadeDominio;
import com.software.software.domain.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao extends AbstractDao {
    public ProdutoDao(Connection connection) {
        super(connection, "produtos", "id");
    }

    public ProdutoDao() {
        super("produtos", "id");
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
        if (connection == null) {
            openConnection();
        }

        PreparedStatement pst = null;
        Produto produto = (Produto) entidade;
        StringBuilder sql = new StringBuilder();

        sql.append("insert into produtos (nome, for_id, descricao) values (?, ?, ?)");

        try {
            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, produto.getNome());
            pst.setInt(2, produto.getFornecedor().getId());
            pst.setString(3, produto.getDescricao());

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();

            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            produto.setId(id);
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
        Produto produto = (Produto) entidade;
        StringBuilder sql = new StringBuilder();

        sql.append("update produtos set nome=?, descricao=? where id=?");

        try {
            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, produto.getNome());
            pst.setString(2, produto.getDescricao());
            pst.setInt(3, produto.getId());

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

        Produto produto = (Produto) entidade;

        StringBuilder sql = new StringBuilder();
        ArrayList<EntidadeDominio> produtos = new ArrayList<>();

        sql.append("select * from produtos");

        if (produto.getFornecedor().getId() != 0) {
            sql.append(" where for_id = ?");
        }

        try {
            pst = connection.prepareStatement(sql.toString());

            if (produto.getFornecedor().getId() != 0) {
                pst.setInt(1, produto.getFornecedor().getId());
            }

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setFornecedor(produto.getFornecedor());
                p.setCreatedAt(rs.getDate("created_at"));
                produtos.add(p);
            }

            return produtos;
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
