package com.software.software.control;

import com.software.software.Resultado;
import com.software.software.domain.EntidadeDominio;

import java.util.ArrayList;

public interface IFachada {
    public Resultado salvar(EntidadeDominio entidade);
    public Resultado alterar(EntidadeDominio entidade);
    public Resultado excluir(EntidadeDominio entidade);
    public Resultado consultar(EntidadeDominio entidade);
    public Resultado visualizar(EntidadeDominio entidade);
}
