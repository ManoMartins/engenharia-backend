package com.software.software.control;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.software.software.Resultado;
import com.software.software.commands.*;
import com.software.software.dao.FornecedorDao;
import com.software.software.domain.*;
import com.software.software.vh.FornecedorViewHelper;
import com.software.software.vh.IViewHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "controle", urlPatterns = { "/controle", "/listar", "/form" , "/salvar" })
public class Controle extends HttpServlet {
    public static Map<String, ICommand> commands;
    public static Map<String, IViewHelper> vhs;

    public Controle() {
        commands = new HashMap<String, ICommand>();
        commands.put("SALVAR", new CommandSalvar());
        commands.put("CONSULTAR", new CommandConsultar());
        commands.put("VISUALIZAR", new CommandVisualizar());
        commands.put("ALTERAR", new CommandAlterar());
        commands.put("EXCLUIR", new CommandExcluir());

        vhs = new HashMap<String, IViewHelper>();
        vhs.put("/listar", new FornecedorViewHelper());
        vhs.put("/form", new FornecedorViewHelper());
        vhs.put("/salvar", new FornecedorViewHelper());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcessRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcessRequest(req, resp);
    }

    protected void doProcessRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        IViewHelper vh = vhs.get(uri);

        EntidadeDominio entidade = vh.getEntidade(request);

        String operacao = request.getParameter("operacao");


        if(operacao == null) {
            operacao = "CONSULTAR";
        }
        ICommand command = commands.get(operacao);

        Resultado resultado = command.execute(entidade);

        vh.setView(resultado, request, response);

    }
}
