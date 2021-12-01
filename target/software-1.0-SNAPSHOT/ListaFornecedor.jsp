<%@ page import="com.software.software.domain.Fornecedor" %>
<%@ page import="java.util.List" %>
<%@ page import="com.software.software.Resultado" %>
<%@ page import="com.software.software.domain.EntidadeDominio" %><%--
  Created by IntelliJ IDEA.
  User: manom
  Date: 2021-11-23
  Time: 11:33 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<script>
    window.onload = async function () {
        await fetch('http://localhost:8080/controle?operacao=CONSULTAR', {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
    }
</script>
<%
    Resultado resultado = (Resultado)session.getAttribute("resultado");
    out.print("<h1>Fornecedores</h1>");
%>

    <table>
        <tr>
            <th>Razao Social</th>
            <th>CNPJ</th>
            <th>Telefone</th>
            <th>Email</th>
            <th>Ações</th>
        </tr>

        <%
            StringBuilder sb = new StringBuilder();
            List<EntidadeDominio> entidades = resultado.getEntidades();

            if (entidades != null) {
                for (int i = 0; i < entidades.size(); i++) {
                    Fornecedor fornecedor = (Fornecedor) entidades.get(i);

                    sb.append("<tr>");
                    sb.append("<td>" + fornecedor.getRazaoSocial() + "</td>");
                    sb.append("<td>" + fornecedor.getCnpj() + "</td>");
                    sb.append("<td>" + fornecedor.getTelefone() + "</td>");
                    sb.append("<td>" + fornecedor.getEmail() + "</td>");
                    sb.append("<td><a href=form?txtId="+ fornecedor.getId() + "&operacao=VISUALIZAR> Editar</a></td>");
                    sb.append("<td><a href=listar?txtId="+ fornecedor.getId() + "&operacao=EXCLUIR> Excluir</a></td>");
                    sb.append("</tr>");
                }

                out.print(sb.toString());
            }
        %>
    </table>

</body>
</html>