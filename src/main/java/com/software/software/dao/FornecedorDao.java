package com.software.software.dao;

import com.software.software.domain.*;

import java.sql.*;
import java.util.ArrayList;

public class FornecedorDao extends AbstractDao {

    public FornecedorDao(Connection cx) {
        super(cx, "fornecedores", "id");
    }

    public FornecedorDao() {
        super("fornecedores", "id");
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
        if (connection == null) {
            openConnection();
        }

        PreparedStatement pst = null;
        Fornecedor fornecedor = (Fornecedor) entidade;
        StringBuilder sql = new StringBuilder();

        sql.append("insert into fornecedores ( ");
        sql.append("tel_id, ");
        sql.append("con_id, ");
        sql.append("end_id, ");
        sql.append("email, ");
        sql.append("cnpj, ");
        sql.append("inscricao_municipal, ");
        sql.append("inscricao_estadual, ");
        sql.append("razao_social, ");
        sql.append("nome_fantasia, ");
        sql.append("tipo_fornecedor, ");
        sql.append("status");
        sql.append(") ");
        sql.append("values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?::fornecedor_type_enum, ?::status_type_enum)");

        try {
            openConnection();

            TelefoneDao telefoneDao = new TelefoneDao(connection);
            telefoneDao.ctrlTransaction = false;
            telefoneDao.salvar(fornecedor.getTelefone());

            ContatoDao contatoDao = new ContatoDao(connection);
            contatoDao.ctrlTransaction = false;
            contatoDao.salvar(fornecedor.getContato());

            EnderecoDao enderecoDao = new EnderecoDao(connection);
            enderecoDao.ctrlTransaction = false;
            enderecoDao.salvar(fornecedor.getEndereco());

            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, fornecedor.getTelefone().getId());
            pst.setInt(2, fornecedor.getContato().getId());
            pst.setInt(3, fornecedor.getEndereco().getId());
            pst.setString(4, fornecedor.getEmail());
            pst.setString(5, fornecedor.getCnpj());
            pst.setString(6, fornecedor.getIncricaoMunicipal());
            pst.setString(7, fornecedor.getIncricaoEstadual());
            pst.setString(8, fornecedor.getRazaoSocial());
            pst.setString(9, fornecedor.getNomeFantasia());
            pst.setString(10, fornecedor.getTipoFornecedor().name());
            pst.setString(11, fornecedor.getStatus().name());

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();

            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            fornecedor.setId(id);

            for (Produto produto : fornecedor.getProdutos()) {
                produto.setFornecedor(fornecedor);
                ProdutoDao produtoDao = new ProdutoDao(connection);
                produtoDao.ctrlTransaction = false;
                produtoDao.salvar(produto);
            }

            for (Cnae cnae : fornecedor.getCnaes()) {
                cnae.setFornecedor(fornecedor);
                CnaeDao cnaeDao = new CnaeDao(connection);
                cnaeDao.ctrlTransaction = false;
                cnaeDao.salvar(cnae);
            }

            for (Empresa empresa : fornecedor.getEmpresas()) {
                empresa.setFornecedor(fornecedor);
                EmpresaDao empresaDao = new EmpresaDao(connection);
                empresaDao.ctrlTransaction = false;
                empresaDao.salvar(empresa);
            }

            for (Servico servico : fornecedor.getServicos()) {
                servico.setFornecedor(fornecedor);
                ServicoDao servicoDao = new ServicoDao(connection);
                servicoDao.ctrlTransaction = false;
                servicoDao.salvar(servico);
            }

            connection.commit();
        } catch (SQLException e) {
            System.out.println("ERRO AO SALVAR FORNECEDOR");

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
        Fornecedor fornecedor = (Fornecedor) entidade;
        StringBuilder sql = new StringBuilder();

        sql.append("update fornecedores set ");
        sql.append("email=?, ");
        sql.append("cnpj=?, ");
        sql.append("inscricao_municipal=?, ");
        sql.append("inscricao_estadual=?, ");
        sql.append("razao_social=?, ");
        sql.append("nome_fantasia=?, ");
        sql.append("tipo_fornecedor=?::fornecedor_type_enum, ");
        sql.append("status=?::status_type_enum ");
        sql.append("where id=?");

        try {
            openConnection();

            TelefoneDao telefoneDao = new TelefoneDao(connection);
            telefoneDao.ctrlTransaction = false;
            telefoneDao.alterar(fornecedor.getTelefone());

            ContatoDao contatoDao = new ContatoDao(connection);
            contatoDao.ctrlTransaction = false;
            contatoDao.alterar(fornecedor.getContato());

            EnderecoDao enderecoDao = new EnderecoDao(connection);
            enderecoDao.ctrlTransaction = false;
            enderecoDao.alterar(fornecedor.getEndereco());

            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, fornecedor.getEmail());
            pst.setString(2, fornecedor.getCnpj());
            pst.setString(3, fornecedor.getIncricaoMunicipal());
            pst.setString(4, fornecedor.getIncricaoEstadual());
            pst.setString(5, fornecedor.getRazaoSocial());
            pst.setString(6, fornecedor.getNomeFantasia());
            pst.setString(7, fornecedor.getTipoFornecedor().name());
            pst.setString(8, fornecedor.getStatus().name());
            pst.setInt(9, fornecedor.getId());

            pst.executeUpdate();

            for (Produto produto : fornecedor.getProdutos()) {
                produto.setFornecedor(fornecedor);
                ProdutoDao produtoDao = new ProdutoDao(connection);
                produtoDao.ctrlTransaction = false;
                produtoDao.alterar(produto);
            }

            for (Cnae cnae : fornecedor.getCnaes()) {
                cnae.setFornecedor(fornecedor);
                CnaeDao cnaeDao = new CnaeDao(connection);
                cnaeDao.ctrlTransaction = false;
                cnaeDao.alterar(cnae);
            }

            for (Empresa empresa : fornecedor.getEmpresas()) {
                empresa.setFornecedor(fornecedor);
                EmpresaDao empresaDao = new EmpresaDao(connection);
                empresaDao.ctrlTransaction = false;
                empresaDao.alterar(empresa);
            }

            for (Servico servico : fornecedor.getServicos()) {
                servico.setFornecedor(fornecedor);
                ServicoDao servicoDao = new ServicoDao(connection);
                servicoDao.ctrlTransaction = false;
                servicoDao.alterar(servico);
            }

            connection.commit();
        } catch (SQLException e) {
            System.out.println("ERRO AO ALTERAR FORNECEDOR");

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

        Fornecedor fornecedor = (Fornecedor) entidade;

        StringBuilder sql = new StringBuilder();

        sql.append("select * from fornecedores f");

        if (fornecedor.getId() != 0) {
            sql.append(" where f.id = ?");
        }
        if (fornecedor.getCnpj() != null) {
            sql.append(" where f.cnpj like ?");
        }
        if (fornecedor.getRazaoSocial() != null) {
            sql.append(" where f.razao_social like ?");
        }
        if (fornecedor.getNomeFantasia() != null) {
            sql.append(" where f.nome_fantasia like ?");
        }


        try {
            openConnection();
            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString());

            if (fornecedor.getId() != 0) {
                pst.setInt(1, fornecedor.getId());
            }
            if (fornecedor.getCnpj() != null) {
                pst.setString(1, "%" + fornecedor.getCnpj() + "%");
            }
            if (fornecedor.getRazaoSocial() != null) {
                pst.setString(1, "%" + fornecedor.getRazaoSocial() + "%");
            }
            if (fornecedor.getNomeFantasia() != null) {
                pst.setString(1, "%" + fornecedor.getNomeFantasia() + "%");
            }

            ResultSet rs = pst.executeQuery();
            ArrayList<EntidadeDominio> fornecedores = new ArrayList<>();

            while (rs.next()) {
                Fornecedor f = new Fornecedor();
                f.setId(rs.getInt("id"));
                f.setCnpj(rs.getString("cnpj"));
                f.setRazaoSocial(rs.getString("razao_social"));
                f.setNomeFantasia(rs.getString("nome_fantasia"));
                f.setIncricaoEstadual(rs.getString("inscricao_estadual"));
                f.setIncricaoMunicipal(rs.getString("inscricao_municipal"));
                f.setEmail(rs.getString("email"));
                f.setStatus(Status.valueOf(rs.getString("status")));
                f.setTipoFornecedor(TipoFornecedor.valueOf(rs.getString("tipo_fornecedor")));

                if (fornecedor.getId() != 0) {
                    ProdutoDao produtoDao = new ProdutoDao(connection);
                    produtoDao.ctrlTransaction = false;
                    Produto produto = new Produto();
                    produto.setFornecedor(f);
                    f.setProdutos((ArrayList)produtoDao.consultar(produto));

                    CnaeDao cnaeDao = new CnaeDao(connection);
                    cnaeDao.ctrlTransaction = false;
                    Cnae cnae = new Cnae();
                    cnae.setFornecedor(f);
                    f.setCnaes((ArrayList)cnaeDao.consultar(cnae));

                    EmpresaDao empresaDao = new EmpresaDao(connection);
                    empresaDao.ctrlTransaction = false;
                    Empresa empresa = new Empresa();
                    empresa.setFornecedor(f);
                    f.setEmpresas((ArrayList)empresaDao.consultar(empresa));

                    ServicoDao servicoDao = new ServicoDao(connection);
                    servicoDao.ctrlTransaction = false;
                    Servico servico = new Servico();
                    servico.setFornecedor(f);
                    f.setServicos((ArrayList)servicoDao.consultar(servico));

                    ContatoDao contatoDao = new ContatoDao(connection);
                    contatoDao.ctrlTransaction = false;
                    Contato contato = new Contato();
                    contato.setId(rs.getInt("con_id"));
                    f.setContato((Contato) contatoDao.consultar(contato).get(0));

                    EnderecoDao enderecoDao = new EnderecoDao(connection);
                    enderecoDao.ctrlTransaction = false;
                    Endereco endereco = new Endereco();
                    endereco.setId(rs.getInt("end_id"));
                    f.setEndereco((Endereco) enderecoDao.consultar(endereco).get(0));

                    TelefoneDao telefoneDao = new TelefoneDao(connection);
                    telefoneDao.ctrlTransaction = false;
                    Telefone telefone = new Telefone();
                    telefone.setId(rs.getInt("tel_id"));
                    f.setTelefone((Telefone) telefoneDao.consultar(telefone).get(0));
                }

                fornecedores.add(f);
            }

            return fornecedores;

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
                    if (pst != null) {
                        pst.close();
                    }

                    if (ctrlTransaction) connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
