package br.com.senac.mol.utils;

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
    
}
