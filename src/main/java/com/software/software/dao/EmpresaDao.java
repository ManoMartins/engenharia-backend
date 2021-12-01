package com.software.software.dao;

import com.software.software.domain.Empresa;
import com.software.software.domain.EntidadeDominio;
import com.software.software.domain.TipoEmpresa;

import java.sql.*;
import java.util.ArrayList;

public class EmpresaDao extends AbstractDao {
    public EmpresaDao(Connection connection) {
        super(connection, "empresas", "id");
    }

    public EmpresaDao() {
        super("empresas", "id");
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
        if (connection == null) {
            openConnection();
        }

        PreparedStatement pst = null;
        Empresa empresa = (Empresa) entidade;
        StringBuilder sql = new StringBuilder();

        sql.append("insert into empresas ( ");
        sql.append("for_id, ");
        sql.append("tipo_empresa, ");
        sql.append("created_at ");
        sql.append(") ");
        sql.append("values (?, ?::empresa_type_enum, ?)");

        try {
            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, empresa.getFornecedor().getId());
            pst.setString(2, empresa.getTipoEmpresa().name());
            pst.setDate(3, (Date) empresa.getCreatedAt());

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();

            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            empresa.setId(id);

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
        Empresa empresa = (Empresa) entidade;
        StringBuilder sql = new StringBuilder();

        sql.append("update empresas set ");
        sql.append("tipo_empresa = ?::empresa_type_enum ");
        sql.append("where id = ?");

        try {
            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, empresa.getTipoEmpresa().name());
            pst.setInt(2, empresa.getId());

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
        Empresa empresa = (Empresa) entidade;

        StringBuilder sql = new StringBuilder();
        sql.append("select * from empresas");

        if (empresa.getFornecedor().getId() != 0) {
            sql.append(" where for_id = ? ");
        }

        try {
            pst = connection.prepareStatement(sql.toString());

            if (empresa.getFornecedor().getId() != 0) {
                pst.setInt(1, empresa.getFornecedor().getId());
            }

            ResultSet rs = pst.executeQuery();

            ArrayList<EntidadeDominio> empresas = new ArrayList<>();

            while (rs.next()) {
                empresa = new Empresa();
                empresa.setId(rs.getInt("id"));
                empresa.setFornecedor(empresa.getFornecedor());
                empresa.setTipoEmpresa(TipoEmpresa.valueOf(rs.getString("tipo_empresa")));
                empresa.setCreatedAt(rs.getDate("created_at"));

                empresas.add(empresa);
            }

            return empresas;
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
