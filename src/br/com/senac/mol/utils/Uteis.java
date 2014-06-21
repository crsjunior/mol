package br.com.senac.mol.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    
    public static String formataData(Date data) {
        String retorno = "";
        SimpleDateFormat dt = new SimpleDateFormat("d/M/y");
        String[] txt_dt = dt.format(data).split("/");
        txt_dt[0] = Integer.valueOf(txt_dt[0])<9?"0"+txt_dt[0]:txt_dt[0];
        txt_dt[1] = Integer.valueOf(txt_dt[1])<9?"0"+txt_dt[1]:txt_dt[1];
        retorno = txt_dt[0]+"/"+txt_dt[1]+"/"+txt_dt[2];
        return retorno;
                
    }
    
}
