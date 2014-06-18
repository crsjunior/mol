package br.com.senac.mol.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 *
 * @author Equipe MOL
 */
public class Uteis {

    public static String getExtensao(String file) {
        String[] parts = file.split("\\.");
        if(parts.length>1){
            return parts[parts.length-1];
        } else {
            return null;
        }
    }
    
    public static String formataCurrency(BigDecimal valor) {
        String retorno = valor.toString();
        retorno = retorno.replaceAll("\\.", ",");
        return retorno;
    }
    
}
