package com.software.software.dao;

import com.software.software.domain.Contato;
import com.software.software.domain.Departamento;
import com.software.software.domain.EntidadeDominio;
import com.software.software.domain.Telefone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDao extends AbstractDao{

    public ContatoDao(Connection cx) {
        super(cx, "contatos", "id");
    }

    public ContatoDao() {
        super("contatos", "id");
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
        if (connection == null) {
            openConnection();
        }

        PreparedStatement pst = null;
        Contato contato = (Contato) entidade;
        StringBuilder sql = new StringBuilder();

        TelefoneDao telefoneDao = new TelefoneDao(connection);
        telefoneDao.ctrlTransaction = false;
        telefoneDao.salvar(contato.getTelefone());

        sql.append("insert into contatos (tel_id, email, departamento, nome, idade) values (?, ?, ?, ?, ?)");

        try {
            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, contato.getTelefone().getId());
            pst.setString(2, contato.getEmail());
            pst.setString(3, contato.getDepartamento().getDescricao());
            pst.setString(4, contato.getNome());
            pst.setInt(5, contato.getIdade());

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();

            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            contato.setId(id);
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
        Contato contato = (Contato) entidade;
        StringBuilder sql = new StringBuilder();

        TelefoneDao telefoneDao = new TelefoneDao(connection);
        telefoneDao.ctrlTransaction = false;
        telefoneDao.alterar(contato.getTelefone());

        sql.append("update contatos set email=?, departamento=?, nome=?, idade=? where id=?");

        try {
            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, contato.getEmail());
            pst.setString(2, contato.getDepartamento().getDescricao());
            pst.setString(3, contato.getNome());
            pst.setInt(4, contato.getIdade());
            pst.setInt(5, contato.getId());

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

        sql.append("select * from contatos");

        if (entidade.getId() != 0) {
            sql.append(" where id = ?");
        }

        try {
            pst = connection.prepareStatement(sql.toString());

            if (entidade.getId() != 0) {
                pst.setInt(1, entidade.getId());
            }

            ResultSet rs = pst.executeQuery();

            ArrayList<EntidadeDominio> contatos = new ArrayList<>();

            while (rs.next()) {
                Contato contato = new Contato();
                contato.setId(rs.getInt("id"));
                contato.setEmail(rs.getString("email"));

                TelefoneDao telefoneDao = new TelefoneDao(connection);
                telefoneDao.ctrlTransaction = false;
                Telefone telefone = new Telefone();
                telefone.setId(rs.getInt("tel_id"));
                contato.setTelefone((Telefone) telefoneDao.consultar(telefone).get(0));
                contato.setDepartamento(new Departamento(rs.getString("departamento")));
                contato.setCreatedAt(rs.getDate("created_at"));
                contato.setNome(rs.getString("nome"));
                contato.setIdade(rs.getInt("idade"));

                contatos.add(contato);
            }

            return contatos;
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
