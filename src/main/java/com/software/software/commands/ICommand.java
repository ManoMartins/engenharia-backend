package com.software.software.commands;

import com.software.software.Resultado;
import com.software.software.domain.EntidadeDominio;

public interface ICommand {
    public Resultado execute(EntidadeDominio entidade);
}
