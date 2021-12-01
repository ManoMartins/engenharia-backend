package com.software.software.vh;

import com.software.software.Resultado;
import com.software.software.domain.EntidadeDominio;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IViewHelper {

    public EntidadeDominio getEntidade(HttpServletRequest request) throws IOException;

    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
