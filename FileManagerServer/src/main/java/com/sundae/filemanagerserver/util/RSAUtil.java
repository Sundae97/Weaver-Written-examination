package com.sundae.filemanagerserver.util;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/17 上午11:26
 * @Description
 */
public class RSAUtil {

    private static final Logger logger = LoggerFactory.getLogger(RSAUtil.class);

    public static String readStringFromResource(String filePath) throws IOException {
        InputStream inputStream = RSAUtil.class.getResourceAsStream(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        String s = null;
        while ((s = bufferedReader.readLine()) != null){
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    public static PrivateKey getPrivateKeyFromResource(String priKeyFilePath){
        try {
            String priKeyStr = readStringFromResource(priKeyFilePath);
            return getPrivateKey(priKeyStr);
        } catch (Exception e) {
            logger.error("readStringFromFile()", e);
            return null;
        }
    }

    public static PublicKey getPublicKeyFromResource(String pubKeyFilePath){
        try {
            String priKeyStr = readStringFromResource(pubKeyFilePath);
            return getPublicKey(priKeyStr);
        } catch (Exception e) {
            logger.error("readStringFromFile()", e);
            return null;
        }
    }

    public static PrivateKey getPrivateKey(String keyStr) throws NoSuchAlgorithmException, Base64DecodingException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decode(keyStr.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        return keyFactory.generatePrivate(keySpec);
    }

    public static PublicKey getPublicKey(String keyStr) throws NoSuchAlgorithmException, Base64DecodingException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decode(keyStr.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        return keyFactory.generatePublic(keySpec);
    }

    public static String encrypt(String str, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        String outStr = Base64.encode(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    public static String decrypt(String str, PrivateKey privateKey) throws Exception {
        byte[] inputByte = Base64.decode(str.getBytes("UTF-8"));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

    public static String sign(String data, PrivateKey privateKey) {
        try{
            byte[] keyBytes = privateKey.getEncoded();
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey key = keyFactory.generatePrivate(keySpec);
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initSign(key);
            signature.update(data.getBytes());
            return Base64.encode(signature.sign());
        }catch (Exception e){
            logger.error("sign()", e);
            return null;
        }
    }

    public static boolean verifySign(String srcData, PublicKey publicKey, String sign) {
        try{
            byte[] keyBytes = publicKey.getEncoded();
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey key = keyFactory.generatePublic(keySpec);
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initVerify(key);
            signature.update(srcData.getBytes());
            return signature.verify(Base64.decode(sign.getBytes()));
        }catch (Exception e) {
            logger.error("verifySign()", e);
            return false;
        }
    }

}
