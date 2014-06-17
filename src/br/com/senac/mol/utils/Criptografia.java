package br.com.senac.mol.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;
 
public class Criptografia {
     
    private MessageDigest messageDigest;
    private BASE64Encoder encoder;
     
    protected void useAlgortithm(String algorithm) throws NoSuchAlgorithmException{
    if (messageDigest == null || messageDigest.getAlgorithm() != algorithm){
        messageDigest = MessageDigest.getInstance(algorithm);
    }
        if (encoder == null) {
            encoder = new BASE64Encoder();
        }
         
    }
     
    protected String encryptByAlgorithm(String algorithm, String value) throws NoSuchAlgorithmException{
        if (value == null) {
            throw new IllegalArgumentException("Valor null");
        }
         
        useAlgortithm(algorithm);
        byte[] hash = messageDigest.digest(value.getBytes());
        return encoder.encode(hash);
    }
     
}