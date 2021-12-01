<%@ page import="com.software.software.Resultado" %><%--
  Created by IntelliJ IDEA.
  User: manom
  Date: 2021-11-28
  Time: 2:39 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        Resultado resultado = (Resultado) session.getAttribute("resultado");

        if (resultado != null) {
            if (resultado.getMsg() != null) {
                out.println("<span>" + resultado.getMsg() + "</span>");
            }
        }
    %>

    <form action="salvar?operacao=SALVAR" method="POST">
        <fieldset>
            <legend>Cadastro de Fornecedor</legend>

            <label for="email">Email:</label>
            <input name="email" id="email" required type="email" />

            <label for="cnpj">CNPJ:</label>
            <input id="cnpj" name="cnpj" required maxlength="14" />

            <label for="inscricaoEstadual">Inscrição estadual:</label>
            <input id="inscricaoEstadual" name="inscricaoEstadual" required />

            <label for="inscricaoMunicipal">Inscrição municipal:</label>
            <input id="inscricaoMunicipal" name="inscricaoMunicipal" required />

            <label for="razaoSocial">Razao social:</label>
            <input id="razaoSocial" name="razaoSocial" required />

            <label for="nomeFantasia">Nome fantasia:</label>
            <input id="nomeFantasia" name="nomeFantasia" required />

            <label for="tipoFornecedor">Tipo fornecedor</label>
            <select id="tipoFornecedor" name="tipoFornecedor" required>
                <option value="SERVICOS" >Servicos</option>
                <option value="VENDAS" >Vendas</option>
            </select>

            <label for="status">Status</label>
            <select id="status" name="status" required>
                <option value="ATIVO" >Ativo</option>
                <option value="INATIVO" >Inativo</option>
            </select>
        </fieldset>

        <fieldset>
            <legend>Cnaes</legend>

            <label for="cnae">Cnae</label>
            <input id="cnae" name="cnae" required />
        </fieldset>

        <fieldset id="produtos">
            <legend>Produtos</legend>

            <label for="nomeProduto">Nome produto</label>
            <input id="nomeProduto" name="nomeProduto" required />

            <label for="descricaoProduto">Descricao produto</label>
            <input id="descricaoProduto" name="descricaoProduto" required />
        </fieldset>

        <fieldset>
            <legend>Telefone</legend>

            <label for="ddd">DDD</label>
            <input id="ddd" name="ddd" required type="number" />

            <label for="numeroTelefone">Numero</label>
            <input id="numeroTelefone" name="numeroTelefone" required type="number" />

            <label for="tipoTelefone">Tipo de telefone</label>
            <input id="tipoTelefone" name="tipoTelefone" required />
        </fieldset>

        <fieldset>
            <legend>Endereço</legend>

            <label for="numero">Número:</label>
            <input id="numero" name="numero" required type="number" />

            <label for="complemento">Complemento:</label>
            <input id="complemento" name="complemento" required />

            <label for="logradouro">Logradouro:</label>
            <input id="logradouro" name="logradouro" required />

            <label for="bairro">Bairro:</label>
            <input id="bairro" name="bairro" required />

            <label for="cidade">Cidade:</label>
            <input id="cidade" name="cidade" required />

            <label for="estado">Estado:</label>
            <input id="estado" name="estado" required />

            <label for="pais">País:</label>
            <input id="pais" name="pais" required />

            <label for="cep">CEP:</label>
            <input id="cep" name="cep" required type="number" />
        </fieldset>

        <fieldset>
            <legend>Contato</legend>

            <label for="emailContato">Email:</label>
            <input name="emailContato" id="emailContato" required type="email" />

            <label for="departamento">Departamento</label>
            <input id="departamento" name="departamento" required />

            <label for="nome">Nome:</label>
            <input id="nome" name="nome" required />

            <label for="idade">Idade</label>
            <input id="idade" name="idade" required type="number" />

            <label for="dddContato">DDD</label>
            <input id="dddContato" name="dddContato" required type="number" />

            <label for="numeroContato">Numero</label>
            <input id="numeroContato" name="numeroContato" required type="number" />

            <label for="tipoTelefoneContato">Tipo telefone</label>
            <input id="tipoTelefoneContato" name="tipoTelefoneContato" required />
        </fieldset>

        <fieldset>
            <legend>Empresa</legend>

            <label for="tipoEmpresa">Tipo de empresa</label>
            <select id="tipoEmpresa" name="tipoEmpresa" required>
                <option value="MATRIZ" >Matriz</option>
                <option value="FILIAL" >Filial</option>
            </select>
        </fieldset>

        <input type="submit" value="Salvar" />
    </form>

</body>
</html>
