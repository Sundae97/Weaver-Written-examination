package com.sundae.filemanagerclient.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/17 上午11:26
 * @Description
 */
public class RSAUtil {

    private static final Logger logger = LoggerFactory.getLogger(RSAUtil.class);

    public static PrivateKey getPrivateKeyFromResource(String priKeyFilePath){
        try {
            String priKeyStr = FileUtil.readStringFromResource(priKeyFilePath);
            return getPrivateKey(priKeyStr);
        } catch (Exception e) {
            logger.error("readStringFromFile()", e);
            return null;
        }
    }

    public static PublicKey getPublicKeyFromResource(String pubKeyFilePath){
        try {
            String priKeyStr = FileUtil.readStringFromResource(pubKeyFilePath);
            return getPublicKey(priKeyStr);
        } catch (Exception e) {
            logger.error("readStringFromFile()", e);
            return null;
        }
    }

    public static PrivateKey getPrivateKey(String keyStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.getDecoder().decode(keyStr.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        return keyFactory.generatePrivate(keySpec);
    }

    public static PublicKey getPublicKey(String keyStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.getDecoder().decode(keyStr.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        return keyFactory.generatePublic(keySpec);
    }

    public static String encrypt(String str, PublicKey publicKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        String outStr = Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    public static String decrypt(String str, PrivateKey privateKey) throws Exception{
        //64位解码加密后的字符串
        byte[] inputByte = Base64.getDecoder().decode(str.getBytes("UTF-8"));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

//    public static String decrypt(byte[] bytes, PrivateKey privateKey) throws Exception {
//        return decrypt(new String(bytes, "UTF-8"), privateKey);
//    }

    public static String sign(String data, PrivateKey privateKey) {
        try{
            byte[] keyBytes = privateKey.getEncoded();
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey key = keyFactory.generatePrivate(keySpec);
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initSign(key);
            signature.update(data.getBytes());
            return Base64.getEncoder().encodeToString(signature.sign()).replaceAll("\n","");
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
            return signature.verify(Base64.getDecoder().decode(sign.getBytes()));
        }catch (Exception e) {
            logger.error("verifySign()", e);
            return false;
        }
    }

}
