<%@ page import="com.software.software.domain.Fornecedor" %>
<%@ page import="com.software.software.domain.TipoFornecedor" %>
<%@ page import="com.software.software.domain.Status" %><%--
  Created by IntelliJ IDEA.
  User: manom
  Date: 2021-11-23
  Time: 12:29 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
  <%
    Fornecedor fornecedor = (Fornecedor)request.getAttribute("fornecedor");
    System.out.println(fornecedor);
  %>

  <form action="controle" method="post">
    <fieldset>
      <legend>Cadastro de Fornecedor</legend>

      <label for="email">Email:</label>
      <input type="text" name="email" id="email" value=<% out.print("'" + fornecedor.getEmail() + "'"); %>  />

      <label for="cnpj">CNPJ:</label>
      <input id="cnpj" name="cnpj" value=<% out.print("'" + fornecedor.getCnpj() + "'"); %> />

      <label for="inscricaoEstadual">Inscrição estadual:</label>
      <input id="inscricaoEstadual" name="inscricaoEstadual" value=<% out.print("'" + fornecedor.getIncricaoEstadual() + "'"); %> />

      <label for="inscricaoMunicipal">Inscrição municipal:</label>
      <input id="inscricaoMunicipal" name="inscricaoMunicipal" value=<% out.print("'" + fornecedor.getIncricaoMunicipal() + "'"); %> />

      <label for="razaoSocial">Razao social:</label>
      <input id="razaoSocial" name="razaoSocial" value=<% out.print("'" + fornecedor.getRazaoSocial() + "'"); %> />

      <label for="nomeFantasia">Nome fantasia:</label>
      <input id="nomeFantasia" name="nomeFantasia" value=<% out.print("'" + fornecedor.getNomeFantasia() + "'"); %> />

      <label for="tipo_fornecedor">Tipo fornecedor</label>
      <select id="tipo_fornecedor" name="tipo_fornecedor">
        <option value="SERVICOS" <% if (fornecedor.getTipoFornecedor().equals(TipoFornecedor.SERVICOS)) { out.print("selected");} %>>Servicos</option>
        <option value="VENDAS" <% if (fornecedor.getTipoFornecedor().equals(TipoFornecedor.VENDAS)) { out.print("selected");} %>>Vendas</option>
      </select>

      <label for="status">Status</label>
      <select id="status" name="status">
        <option value="ATIVO" <% if (fornecedor.getStatus().equals(Status.ATIVO)) { out.print("selected");} %>>Ativo</option>
        <option value="INATIVO" <% if (fornecedor.getStatus().equals(Status.INATIVO)) { out.print("selected");} %>>Inativo</option>
      </select>

      <label for="cnae">Cnae</label>
      <input id="cnae" name="cnae" />

      <label for="nomeProduto">Nome produto</label>
      <input id="nomeProduto" name="nomeProduto" />

      <label for="descricaoProduto">Descricao produto</label>
      <input id="descricaoProduto" name="descricaoProduto" />

      <label for="ddd">DDD</label>
      <input id="ddd" name="ddd" />

      <label for="numeroTelefone">Numero</label>
      <input id="numeroTelefone" name="numeroTelefone" />

      <label for="tipoTelefone">Tipo de telefone</label>
      <input id="tipoTelefone" name="tipoTelefone" />
    </fieldset>

    <fieldset>
      <legend>Endereço</legend>

      <label for="numero">Número:</label>
      <input id="numero" name="numero" value=<% out.print("'" + fornecedor.getEndereco().getNumero() + "'"); %> />

      <label for="complemento">Complemento:</label>
      <input id="complemento" name="complemento" value=<% out.print("'" + fornecedor.getEndereco().getComplemento() + "'"); %> />

      <label for="logradouro">Logradouro:</label>
      <input id="logradouro" name="logradouro" value=<% out.print("'" + fornecedor.getEndereco().getLogradouro() + "'"); %> />

      <label for="bairro">Bairro:</label>
      <input id="bairro" name="bairro" value=<% out.print("'" + fornecedor.getEndereco().getBairro() + "'"); %> />

      <label for="cidade">Cidade:</label>
      <input id="cidade" name="cidade" value=<% out.print("'" + fornecedor.getEndereco().getCidade().getDescricao() + "'"); %> />

      <label for="estado">Estado:</label>
      <input id="estado" name="estado" value=<% out.print("'" + fornecedor.getEndereco().getCidade().getEstado().getDescricao() + "'"); %> />

      <label for="pais">País:</label>
      <input id="pais" name="pais" value=<% out.print("'" + fornecedor.getEndereco().getCidade().getEstado().getPais().getDescricao() + "'"); %> />

      <label for="cep">CEP:</label>
      <input id="cep" name="cep" value=<% out.print("'" + fornecedor.getEndereco().getCep() + "'"); %> />
    </fieldset>

    <fieldset>
      <legend>Contato</legend>

      <label for="emailContato">Email:</label>
      <input type="text" name="emailContato" id="emailContato" value=<% out.print("'" + fornecedor.getContato().getEmail() + "'"); %> />

      <label for="departamento">Departamento</label>
      <input id="departamento" name="departamento" value=<% out.print("'" + fornecedor.getContato().getDepartamento().getDescricao() + "'"); %> />

      <label for="nome">Nome:</label>
      <input id="nome" name="nome" value=<% out.print("'" + fornecedor.getContato().getNome() + "'"); %> />

      <label for="idade">Idade</label>
      <input id="idade" name="idade" value=<% out.print("'" + fornecedor.getContato().getIdade() + "'"); %> />
    </fieldset>

    <input type="submit" value="Alterar" />
  </form>
</body>
</html>
