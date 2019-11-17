package com.sundae.filemanagerserver.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/17 上午10:55
 * @Description
 */
public class AESUtil {
    public static final String KEY_ALGORITHM = "AES";
    public static final String CIPHER_ALGORITHM_ECB = "AES/ECB/PKCS5Padding";

    public static byte[] initKey() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        kg.init(128);
        return kg.generateKey().getEncoded();
    }

    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, KEY_ALGORITHM));
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, KEY_ALGORITHM));
        return cipher.doFinal(data);
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
