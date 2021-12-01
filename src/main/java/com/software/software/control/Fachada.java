package com.software.software.control;

import com.software.software.Resultado;
import com.software.software.business.*;
import com.software.software.dao.FornecedorDao;
import com.software.software.dao.IDAO;
import com.software.software.domain.EntidadeDominio;
import com.software.software.domain.Fornecedor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fachada implements IFachada {
    private Map<String, IDAO> daos;
    private Map<String, Map<String, List<IStrategy>>> rns;

    private Resultado resultado;

    public Fachada() {
        daos = new HashMap<String, IDAO>();
        daos.put(Fornecedor.class.getName(), new FornecedorDao());

        rns = new HashMap<String, Map<String, List<IStrategy>>>();
        Map<String, List<IStrategy>> rnsSalvar = new HashMap<String, List<IStrategy>>();
        Map<String, List<IStrategy>> rnsAlterar = new HashMap<String, List<IStrategy>>();

        ValidadorCNPJ validadorCNPJ = new ValidadorCNPJ();
        ValidarDadosObrigatorios validarDadosObrigatorios = new ValidarDadosObrigatorios();
        ValidarUnicidadeCNPJ validarUnicidadeCNPJ = new ValidarUnicidadeCNPJ();
        ValidarCEP validarCEP = new ValidarCEP();
        ValidarCnaeUnico validarCnaeUnico = new ValidarCnaeUnico();

        ArrayList<IStrategy> rnsFornecedorSalvar = new ArrayList<IStrategy>();
        rnsFornecedorSalvar.add(validadorCNPJ);
        rnsFornecedorSalvar.add(validarDadosObrigatorios);
        rnsFornecedorSalvar.add(validarUnicidadeCNPJ);
        rnsFornecedorSalvar.add(validarCEP);
        rnsFornecedorSalvar.add(validarCnaeUnico);

        ArrayList<IStrategy> rnsFornecedorAlterar = new ArrayList<IStrategy>();
        rnsFornecedorAlterar.add(validadorCNPJ);
        rnsFornecedorAlterar.add(validarDadosObrigatorios);
        rnsFornecedorAlterar.add(validarCEP);
        rnsFornecedorAlterar.add(validarCnaeUnico);

        rnsSalvar.put(Fornecedor.class.getName(), rnsFornecedorSalvar);
        rnsAlterar.put(Fornecedor.class.getName(), rnsFornecedorAlterar);

        rns.put("SALVAR", rnsSalvar);
        rns.put("ALTERAR", rnsAlterar);
    }

    @Override
    public Resultado salvar(EntidadeDominio entidade) {
        resultado = new Resultado();
        String nomeClasse = entidade.getClass().getName();

        String msg = executaRegras(entidade, "SALVAR");

        if(msg == null) {
            IDAO dao = daos.get(nomeClasse);
            try {
                dao.salvar(entidade);
                List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
                entidades.add(entidade);
                resultado.setEntidades(entidades);
            } catch(SQLException e) {
                e.printStackTrace();
                resultado.setMsg("Não foi possível realizar o cadastro");
            }
        } else {
            resultado.setMsg(msg);
        }

        return resultado;
    }

    @Override
    public Resultado alterar(EntidadeDominio entidade) {
        resultado = new Resultado();
        String nomeClasse = entidade.getClass().getName();

        String msg = executaRegras(entidade, "ALTERAR");

        if(msg == null) {
            IDAO dao = daos.get(nomeClasse);
            try {
                dao.alterar(entidade);
                List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
                entidades.add(entidade);
                resultado.setEntidades(entidades);
            } catch(SQLException e) {
                e.printStackTrace();
                resultado.setMsg("Não foi possível realizar a alteração");
            }
        } else {
            resultado.setMsg(msg);
        }

        return resultado;
    }

    @Override
    public Resultado excluir(EntidadeDominio entidade) {

        resultado = new Resultado();
        String nomeClasse = entidade.getClass().getName();

        String msg = executaRegras(entidade, "EXCLUIR");

        if(msg == null) {
            IDAO dao = daos.get(nomeClasse);
            try {
                dao.excluir(entidade);
                resultado.setEntidades(dao.consultar(new Fornecedor()));
            } catch(SQLException e) {
                e.printStackTrace();
                resultado.setMsg("Não foi possível realizar a exclusão");
            }
        }

        return resultado;
    }

    @Override
    public Resultado consultar(EntidadeDominio entidade) {
        resultado = new Resultado();
        String nomeClasse = entidade.getClass().getName();

        String msg = executaRegras(entidade, "CONSULTAR");

        if(msg == null) {
            IDAO dao = daos.get(nomeClasse);
            try {
                resultado.setEntidades(dao.consultar(entidade));
            } catch(SQLException e) {
                e.printStackTrace();
                resultado.setMsg("Não foi possível realizar a consullta");
            }
        }

        return resultado;
    }

    public Resultado visualizar(EntidadeDominio entidade){
        resultado = new Resultado();
        String nomeClasse = entidade.getClass().getName();

        String msg = executaRegras(entidade, "CONSULTAR");

        if(msg == null) {
            IDAO dao = daos.get(nomeClasse);
            try {
                resultado.setEntidades(dao.consultar(entidade));
            } catch(SQLException e) {
                e.printStackTrace();
                resultado.setMsg("Não foi possível realizar a visualização");
            }
        }

        return resultado;
    }

    private String executaRegras(EntidadeDominio entidade, String operacao) {
        String nomeClasse = entidade.getClass().getName();
        StringBuilder msg = new StringBuilder();

        Map<String, List<IStrategy>> regrasOperacao = rns.get(operacao);

        if(regrasOperacao != null) {
            List<IStrategy> regras =regrasOperacao.get(nomeClasse);

            if(regras != null) {
                for(IStrategy regra : regras) {
                    String m = null;
                    try {
                        m = regra.processar(entidade);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    if(m != null) {
                        msg.append(m);
                        msg.append("\n");
                    }
                }
            }
        }

        if(msg.length() > 0) {
            return msg.toString();
        } else {
            return null;
        }
    }
}
