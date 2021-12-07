package com.software.software.business;

import com.software.software.domain.EntidadeDominio;
import com.software.software.domain.Fornecedor;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarEmail implements IStrategy{

    @Override
    public String processar(EntidadeDominio entidade)  {
        StringBuilder sb = new StringBuilder();
        Fornecedor fornecedor = (Fornecedor) entidade;
        String email = fornecedor.getEmail();
        String emailContato = fornecedor.getContato().getEmail();

        String regex = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcherEmail = pattern.matcher(email);
        Matcher matcherEmailContato = pattern.matcher(emailContato);

        if(!matcherEmail.find()){
            sb.append("E-mail do fornecedor deve ser válido!\n");
        }

        if(!matcherEmailContato.find()) {
            sb.append("E-mail de contato deve ser válido!\n");
        }

        if (sb.length() > 0) {
            return sb.toString();
        } else {
            return null;
        }

    }
}
