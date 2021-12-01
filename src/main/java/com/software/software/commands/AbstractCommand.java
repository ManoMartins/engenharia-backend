package com.software.software.commands;

import com.software.software.control.Fachada;
import com.software.software.control.IFachada;

public abstract class AbstractCommand implements ICommand {
    protected IFachada fachada = new Fachada();
}
