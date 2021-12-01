package com.software.software.commands;

import com.software.software.Resultado;
import com.software.software.domain.EntidadeDominio;

public class CommandExcluir extends AbstractCommand {
    @Override
    public Resultado execute(EntidadeDominio entidade) {
        return fachada.excluir(entidade);
    }
}
