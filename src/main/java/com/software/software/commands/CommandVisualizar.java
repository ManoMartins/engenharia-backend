package com.software.software.commands;

import com.software.software.Resultado;
import com.software.software.domain.EntidadeDominio;

public class CommandVisualizar extends AbstractCommand {
    public Resultado execute(EntidadeDominio entidade) {

        return fachada.consultar(entidade);
    }
}
