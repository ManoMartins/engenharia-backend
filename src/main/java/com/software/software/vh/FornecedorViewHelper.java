package com.software.software.vh;

import com.google.gson.Gson;
import com.software.software.Resultado;
import com.software.software.domain.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FornecedorViewHelper implements IViewHelper {

    private Gson gson = new Gson();

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) throws IOException {
        String operacao = request.getParameter("operacao");

        Fornecedor fornecedor = null;

        if(operacao == null) {
            operacao = "CONSULTAR";
        }

        if(!operacao.equals("VISUALIZAR")){
            fornecedor = new Gson().fromJson(request.getReader(), Fornecedor.class);

            if (fornecedor == null) {
                fornecedor = new Fornecedor();
            }
        } else {
            fornecedor = new Gson().fromJson(request.getReader(), Fornecedor.class);
        }

        return fornecedor;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher d = null;
        String operacao = request.getParameter("operacao");

        if(operacao == null) {
            operacao = "CONSULTAR";
        }

        if (operacao.equals("ALTERAR")) {
            if (resultado.getMsg() == null) {
                response.setStatus(HttpServletResponse.SC_CREATED);
                return;
            }

            if (resultado.getMsg() != null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                String json = this.gson.toJson(resultado);
                out.print(json);
                out.flush();
                return;
            }
        }

        if (resultado.getMsg() != null && operacao.equals("SALVAR")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String json = this.gson.toJson(resultado);
            out.print(json);
            out.flush();
            return;
        }

        if(resultado.getMsg() == null && operacao.equals("SALVAR")) {
            response.setStatus(HttpServletResponse.SC_CREATED);

            resultado.setMsg("Produto cadastrado com sucesso");

            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String json = this.gson.toJson(resultado);
            out.print(json);
            out.flush();
            return;
        }

        if(resultado.getMsg() == null && operacao.equals("CONSULTAR")) {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String json = this.gson.toJson(resultado);
            out.print(json);
            out.flush();
            return;
        }

        if(resultado.getMsg() == null && operacao.equals("VISUALIZAR")) {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String json = this.gson.toJson(resultado.getEntidades().get(0));
            out.print(json);
            out.flush();
            return;
        }

        if(resultado.getMsg() == null && operacao.equals("EXCLUIR")) {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String json = this.gson.toJson(resultado.getEntidades().get(0));
            out.print(json);
            out.flush();
            return;
        }
    }
}