package com.software.software.business;

import com.software.software.domain.*;

import java.util.ArrayList;

public class ValidarDadosObrigatorios implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade) {
        StringBuilder sb = new StringBuilder();
        Fornecedor fornecedor = (Fornecedor) entidade;

        String email = fornecedor.getEmail();
        if (email == null || email.equals("")) {
            sb.append("Email é obrigatório!\n");
        }

        String inscricaoEstadual = fornecedor.getIncricaoEstadual();
        if (inscricaoEstadual == null || inscricaoEstadual.equals("")) {
            sb.append("Inscrição Estadual é obrigatório!\n");
        }

        String incricaoMunicipal = fornecedor.getIncricaoMunicipal();
        if (incricaoMunicipal == null || incricaoMunicipal.equals("")) {
            sb.append("Inscrição Municipal é obrigatório!\n");
        }

        String nomeFantasia = fornecedor.getNomeFantasia();
        if (nomeFantasia == null || nomeFantasia.equals("")) {
            sb.append("Nome Fantasia é obrigatório!\n");
        }

        String razaoSocial = fornecedor.getRazaoSocial();
        if (razaoSocial == null || razaoSocial.equals("")) {
            sb.append("Razão Social é obrigatório!\n");
        }

        Status status = fornecedor.getStatus();
        if (status == null) {
            sb.append("Status é obrigatório!\n");
        }

        TipoFornecedor tipoFornecedor = fornecedor.getTipoFornecedor();
        if (tipoFornecedor == null) {
            sb.append("Tipo Fornecedor é obrigatório!\n");
        }

        if (fornecedor.getContato() != null) {
            String emailContato = fornecedor.getContato().getEmail();
            if (emailContato == null || emailContato.equals("")) {
                sb.append("Email do Contato é obrigatório!\n");
            }

            String nomeContato = fornecedor.getContato().getNome();
            if (nomeContato == null || nomeContato.equals("")) {
                sb.append("Nome do Contato é obrigatório!\n");
            }

            String departamentoContato = fornecedor.getContato().getDepartamento().getDescricao();
            if (departamentoContato == null || departamentoContato.equals("")) {
                sb.append("Departamento do Contato é obrigatório!\n");
            }

            int idadeContato = fornecedor.getContato().getIdade();
            if (idadeContato == 0) {
                sb.append("Idade do Contato é obrigatório!\n");
            }

            String dddContato = fornecedor.getContato().getTelefone().getDdd();
            if (dddContato == null || dddContato.equals("")) {
                sb.append("DDD do telefone do Contato é obrigatório!\n");
            }

            String numeroContato = fornecedor.getContato().getTelefone().getNumero();
            if (numeroContato == null || numeroContato.equals("")) {
                sb.append("Número do telefone do Contato é obrigatório!\n");
            }

            String tipoTelefoneContato = fornecedor.getContato().getTelefone().getTipoTelefone().getDescricao();
            if (tipoTelefoneContato == null || tipoTelefoneContato.equals("")) {
                sb.append("Tipo do Telefone do Contato é obrigatório!\n");
            }
        } else {
            sb.append("Contato é obrigatório!\n");
        }

        if (fornecedor.getEndereco() != null) {
            String cep = fornecedor.getEndereco().getCep();
            if (cep == null || cep.equals("")) {
                sb.append("CEP é obrigatório!\n");
            }

            String numeroEndereco = fornecedor.getEndereco().getNumero();
            if (numeroEndereco == null || numeroEndereco.equals("")) {
                sb.append("Número do Endereço é obrigatório!\n");
            }

            String bairro = fornecedor.getEndereco().getBairro();
            if (bairro == null || bairro.equals("")) {
                sb.append("Bairro é obrigatório!\n");
            }

            String logradouro = fornecedor.getEndereco().getLogradouro();
            if (logradouro == null || logradouro.equals("")) {
                sb.append("Logradouro é obrigatório!\n");
            }

            String cidade = fornecedor.getEndereco().getCidade().getDescricao();
            if (cidade == null || cidade.equals("")) {
                sb.append("Cidade é obrigatório!\n");
            }

            String estado = fornecedor.getEndereco().getCidade().getEstado().getDescricao();
            if (estado == null || estado.equals("")) {
                sb.append("Estado é obrigatório!\n");
            }

            String pais = fornecedor.getEndereco().getCidade().getEstado().getPais().getDescricao();
            if (pais == null || pais.equals("")) {
                sb.append("Pais é obrigatório!\n");
            }
        } else {
            sb.append("Endereço é obrigatório!\n");
        }

        if (fornecedor.getTelefone() != null) {
            String ddd = fornecedor.getTelefone().getDdd();
            if (ddd == null || ddd.equals("")) {
                sb.append("DDD do telefone é obrigatório!\n");
            }

            String numeroTelefone = fornecedor.getTelefone().getNumero();
            if (numeroTelefone == null || numeroTelefone.equals("")) {
                sb.append("Número do telefone é obrigatório!\n");
            }

            String tipoTelefone = fornecedor.getTelefone().getTipoTelefone().getDescricao();
            if (tipoTelefone == null || tipoTelefone.equals("")) {
                sb.append("Tipo do Telefone é obrigatório!\n");
            }
        } else {
            sb.append("Telefone é obrigatório!\n");
        }

        if (fornecedor.getCnaes() != null && fornecedor.getCnaes().size() > 0) {
            ArrayList<Cnae> cnaes = fornecedor.getCnaes();

            for (Cnae cnae : cnaes) {
                String numero = cnae.getNumero();

                if (numero == null || numero.equals("")) {
                    sb.append("Número do cnae é obrigatório!\n");
                }
            }
        } else {
            sb.append("Digite pelo menos um cnae!\n");
        }

        if (fornecedor.getEmpresas() != null && fornecedor.getEmpresas().size() > 0) {
            ArrayList<Empresa> empresas = fornecedor.getEmpresas();

            for (Empresa empresa : empresas) {
                TipoEmpresa tipoEmpresa = empresa.getTipoEmpresa();
                if (tipoEmpresa == null) {
                    sb.append("Tipo da Empresa é obrigatório!\n");
                }
            }
        } else {
            sb.append("Selecione pelo menos um tipo empresa!\n");
        }

        if (fornecedor.getProdutos() != null && fornecedor.getProdutos().size() > 0) {
            ArrayList<Produto> produtos = fornecedor.getProdutos();

            for (Produto produto : produtos) {
                String descricao = produto.getDescricao();
                if (descricao == null || descricao.equals("")) {
                    sb.append("Descrição do produto é obrigatório!\n");
                }

                String nome = produto.getNome();
                if (nome == null || nome.equals("")) {
                    sb.append("Nome do produto é obrigatório!\n");
                }
            }
        } else {
            sb.append("Digite pelo menos um produto!\n");
        }

        if (fornecedor.getServicos() != null && fornecedor.getServicos().size() > 0) {
            ArrayList<Servico> servicos = fornecedor.getServicos();

            for (Servico servico : servicos) {
                String descricao = servico.getDescricao();
                if (descricao == null || descricao.equals("")) {
                    sb.append("Descrição do serviço é obrigatório!\n");
                }

                String codigo = servico.getCodigo();
                if (codigo == null || codigo.equals("")) {
                    sb.append("Código do serviço é obrigatório!\n");
                }

                String dataInicio = servico.getDataInicio();
                if (dataInicio == null || dataInicio.equals("")) {
                    sb.append("Data de início do serviço é obrigatório!\n");
                }
            }
        } else {
            sb.append("Digite pelo menos um serviço!\n");
        }

        if (sb.length() > 0) {
            return sb.toString();
        } else {
            return null;
        }
    }
}
