package com.daw.atlanthis.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.security.spec.KeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.faces.bean.ManagedProperty;
import org.apache.commons.codec.binary.Base64;


public class Utilidades  {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("atlanthisPU");
    
    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private final String myEncryptionKey = "4rG#rm#j%i5wf8!F%m4GiGc*";
    private final String myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
    byte[] arrayBytes;
    private final KeySpec ks;
    private final SecretKeyFactory skf = SecretKeyFactory.getInstance(myEncryptionScheme);
    private final Cipher cipher;
    SecretKey key;

    private final String STMP_host = "smtp-mail.outlook.com";
    private final String STMP_port = "587";
    private final String STMP_user = "periodicoatlanthis@outlook.com";
    private final String STMP_pass = "NxjzMahP4agq";

    @ManagedProperty("#{beanLogin.email}")
    private String mail;
    
    public Utilidades() throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, UnsupportedEncodingException {
        this.arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        this.ks = new DESedeKeySpec(arrayBytes);
        this.key = skf.generateSecret(ks);
        this.cipher = Cipher.getInstance(myEncryptionScheme);
    }
    
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSTMP_host() {
        return STMP_host;
    }

    public String getSTMP_port() {
        return STMP_port;
    }

    public String getSTMP_user() {
        return STMP_user;
    }

    public String getSTMP_pass() {
        return STMP_pass;
    }
        
    public String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (UnsupportedEncodingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
        }
        return encryptedString;
    }


    public String decrypt(String encryptedString) {
        String decryptedText=null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.decodeBase64(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= new String(plainText);
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
        }
        return decryptedText;
    }
}
