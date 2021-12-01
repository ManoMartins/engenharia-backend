<%--
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
    <form method="post">
        <fieldset>
            <legend>Cadastro de Fornecedor</legend>

            <label for="email">Email:</label>
            <input type="text" name="email" id="email" />

            <label for="cnpj">CNPJ:</label>
            <input id="cnpj" name="cnpj" />
            <pre>
                <% String cnpj = (String) session.getAttribute("cnpj") %>
                <% cnpj %>
            </pre>

            <label for="inscricaoEstadual">Inscrição estadual:</label>
            <input id="inscricaoEstadual" name="inscricaoEstadual" />

            <label for="inscricaoMunicipal">Inscrição municipal:</label>
            <input id="inscricaoMunicipal" name="inscricaoMunicipal" />

            <label for="razaoSocial">Razao social:</label>
            <input id="razaoSocial" name="razaoSocial" />

            <label for="nomeFantasia">Nome fantasia:</label>
            <input id="nomeFantasia" name="nomeFantasia" />

            <label for="tipo_fornecedor">Tipo fornecedor</label>
            <select id="tipo_fornecedor" name="tipo_fornecedor">
                <option value="SERVICOS">Servicos</option>
                <option value="VENDAS">Vendas</option>
            </select>

            <label for="status">Status</label>
            <select id="status" name="status">
                <option value="ATIVO">Ativo</option>
                <option value="INATIVO">Inativo</option>
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
            <input id="numero" name="numero" />

            <label for="complemento">Complemento:</label>
            <input id="complemento" name="complemento" />

            <label for="logradouro">Logradouro:</label>
            <input id="logradouro" name="logradouro" />

            <label for="bairro">Bairro:</label>
            <input id="bairro" name="bairro" />

            <label for="cidade">Cidade:</label>
            <input id="cidade" name="cidade" />

            <label for="estado">Estado:</label>
            <input id="estado" name="estado" />

            <label for="pais">País:</label>
            <input id="pais" name="pais" />

            <label for="cep">CEP:</label>
            <input id="cep" name="cep" />
        </fieldset>

        <fieldset>
            <legend>Contato</legend>

            <label for="emailContato">Email:</label>
            <input type="text" name="emailContato" id="emailContato" />

            <label for="departamento">Departamento</label>
            <input id="departamento" name="departamento" />

            <label for="nome">Nome:</label>
            <input id="nome" name="nome" />

            <label for="idade">Idade</label>
            <input id="idade" name="idade" />
        </fieldset>

        <input type="submit" value="Cadastrar" />
    </form>
</body>
</html>
