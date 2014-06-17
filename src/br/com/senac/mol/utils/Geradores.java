package br.com.senac.mol.utils;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 *
 * @author Equipe MOL
 */
public class Geradores extends Criptografia {

    public String getHash() {
        Random r = new Random();
        Integer t = r.nextInt(100000000);
        return t.toString();
    }

    public String encrypt(String value) throws NoSuchAlgorithmException {
        return encryptByAlgorithm("MD5", value);
    }

}
