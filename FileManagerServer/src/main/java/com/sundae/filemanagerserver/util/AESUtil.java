package com.sundae.filemanagerserver.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/17 上午10:55
 * @Description
 */
public class AESUtil {
    private static final String KEY_ALGORITHM = "AES";
    private static final String CHAR_ENCODING = "UTF-8";
    public static final String CIPHER_ALGORITHM_ECB = "AES";

    public static byte[] generateRandomKey() throws Exception {
        KeyGenerator keygen = KeyGenerator.getInstance(KEY_ALGORITHM);
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        keygen.init(128,random);
        return keygen.generateKey().getEncoded();
    }

    public static String generateRandomKeyWithBase64() throws Exception {
        return Base64.getEncoder().encodeToString(generateRandomKey());
    }

    public static SecretKey getKey(byte[] key) throws NoSuchAlgorithmException {
        KeyGenerator keygen = KeyGenerator.getInstance(KEY_ALGORITHM);
        SecureRandom random=SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(key);
        keygen.init(128, random);
        return keygen.generateKey();
    }

    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
        cipher.init(Cipher.ENCRYPT_MODE, getKey(key));//使用解密模式初始化 密钥
        byte[] decrypt = cipher.doFinal(data);
        return decrypt;
    }

    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
        cipher.init(Cipher.DECRYPT_MODE, getKey(key));//使用解密模式初始化 密钥
        byte[] decrypt = cipher.doFinal(data);
        return decrypt;
    }

    public static String encryptToBase64(String data, String key) {
        try {
            byte[] valueByte = encrypt(data.getBytes(CHAR_ENCODING), key.getBytes(CHAR_ENCODING));
            return Base64.getEncoder().encodeToString(valueByte);
        } catch (Exception e) {
            throw new RuntimeException("encrypt fail!", e);
        }

    }

    public static String decryptFromBase64(String data, String key) {
        try {
            byte[] originalData = Base64.getDecoder().decode(data.getBytes());
            byte[] valueByte = decrypt(originalData, key.getBytes(CHAR_ENCODING));
            return new String(valueByte, CHAR_ENCODING);
        } catch (Exception e) {
            throw new RuntimeException("decrypt fail!", e);
        }
    }

    public static byte[] encryptFile2Bytes(InputStream inputStream, byte[] key) throws Exception{
        BufferedInputStream bufferedInputStream = null;
        try{
            bufferedInputStream = new BufferedInputStream(inputStream);
            int len = bufferedInputStream.available();
            byte[] sourceBytes = new byte[len];
            bufferedInputStream.read(sourceBytes, 0, len);
            byte[] encodeBytes = encrypt(sourceBytes, key);
            return encodeBytes;
        }finally {
            bufferedInputStream.close();
        }
    }

    public static byte[] decryptFile2Bytes(File file, byte[] key) throws Exception{
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            int len = fileInputStream.available();
            byte[] sourceBytes = new byte[len];
            fileInputStream.read(sourceBytes, 0, len);
            byte[] decodeBytes = decrypt(sourceBytes, key);
            return decodeBytes;
        }finally {
            fileInputStream.close();
        }
    }

}
