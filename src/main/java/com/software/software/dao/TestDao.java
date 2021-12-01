package com.software.software.dao;

import com.software.software.domain.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class TestDao {
    public static void main(String[] args) {
        TipoTelefone tipoTelefone = new TipoTelefone();
        tipoTelefone.setDescricao("Celular");

        Telefone telefoneContato;
        telefoneContato = new Telefone();
        telefoneContato.setNumero("99999-9999");
        telefoneContato.setTipoTelefone(tipoTelefone);
        telefoneContato.setDdd("11");

        Departamento departamento = new Departamento();
        departamento.setDescricao("TI");

        Contato contato = new Contato();
        contato.setEmail("manoel@hub.com");
        contato.setTelefone(telefoneContato);
        contato.setNome("Manoel");
        contato.setIdade(30);
        contato.setDepartamento(departamento);

        Pais pais = new Pais();
        pais.setDescricao("Brasil");

        Estado estado = new Estado();
        estado.setDescricao("São Paulo");
        estado.setPais(pais);

        Cidade cidade = new Cidade();
        cidade.setDescricao("São Paulo");
        cidade.setEstado(estado);

        Endereco endereco = new Endereco();
        endereco.setBairro("Centro");
        endereco.setCep("99999-999");
        endereco.setCidade(cidade);
        endereco.setComplemento("Apto 101");
        endereco.setLogradouro("Rua dos Bobos");
        endereco.setNumero("123");

        Telefone telefoneFornecedor = new Telefone();
        telefoneFornecedor.setNumero("96666-6666");
        telefoneFornecedor.setTipoTelefone(tipoTelefone);
        telefoneFornecedor.setDdd("16");

        ArrayList<Produto> produtos = new ArrayList<>();
        Produto produto = new Produto();
        produto.setDescricao("Produto 1");
        produto.setNome("Produto 1");
        produtos.add(produto);

        Produto produto2 = new Produto();
        produto2.setDescricao("Produto 2");
        produto2.setNome("Produto 2");
        produtos.add(produto2);

        Produto produto3 = new Produto();
        produto3.setDescricao("Produto 3");
        produto3.setNome("Produto 3");
        produtos.add(produto3);

        Produto produto4 = new Produto();
        produto4.setDescricao("Produto 4");
        produto4.setNome("Produto 4");
        produtos.add(produto4);

        ArrayList<Cnae> cnaes = new ArrayList<>();

        Cnae cnae = new Cnae();
        cnae.setNumero("Cnae 1");
        cnae.setCreatedAt(new java.sql.Date(new Date().getTime()));
        cnaes.add(cnae);

        Cnae cnae2 = new Cnae();
        cnae2.setNumero("Cnae 2");
        cnae2.setCreatedAt(new java.sql.Date(new Date().getTime()));
        cnaes.add(cnae2);

        Cnae cnae3 = new Cnae();
        cnae3.setNumero("Cnae 3");
        cnae3.setCreatedAt(new java.sql.Date(new Date().getTime()));
        cnaes.add(cnae3);

        ArrayList<Empresa> empresas = new ArrayList<>();

        Empresa empresa = new Empresa();
        empresa.setTipoEmpresa(TipoEmpresa.FILIAL);
        empresa.setCreatedAt(new java.sql.Date(new Date().getTime()));
        empresas.add(empresa);

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setCnpj("12345678901234");
        fornecedor.setContato(contato);
        fornecedor.setTelefone(telefoneFornecedor);
        fornecedor.setEndereco(endereco);
        fornecedor.setNomeFantasia("Fornecedor");
        fornecedor.setRazaoSocial("Fornecedor LTDA");
        fornecedor.setTipoFornecedor(TipoFornecedor.SERVICOS);
        fornecedor.setIncricaoEstadual("12345678901234");
        fornecedor.setIncricaoMunicipal("12345678901234");
        fornecedor.setStatus(Status.ATIVO);
        fornecedor.setProdutos(produtos);
        fornecedor.setEmail("adm@hub.com");
        fornecedor.setCnaes(cnaes);
        fornecedor.setEmpresas(empresas);

        FornecedorDao fornecedorDao = new FornecedorDao();

        try {
            fornecedorDao.salvar(fornecedor);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
