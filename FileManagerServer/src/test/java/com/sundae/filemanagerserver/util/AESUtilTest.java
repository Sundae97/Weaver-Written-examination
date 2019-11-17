package com.sundae.filemanagerserver.util;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;


/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/17 上午11:09
 * @Description
 */
public class AESUtilTest {

    @Test
    public void encrypt() throws Exception {
        byte[] text = "hello aes".getBytes("UTF-8");
        byte[] key = AESUtil.initKey();
        byte[] result = AESUtil.encrypt(text, key);
        System.out.println(Base64.encode(result));

        byte[] decryptResult = AESUtil.decrypt(result, key);
        System.out.println(new String(decryptResult, "UTF-8"));
    }

    @Test
    public void encryptFile() throws Exception {
        byte[] key = AESUtil.initKey();
        File file = new File("./test.jpg");
        File encryptFile = new File("./encryptFile.jpg");
        File decryptFile = new File("./decryptFile.jpg");
        byte[] encryptBytes = AESUtil.encryptFile2Bytes(new FileInputStream(file), key);
        FileUtil.saveFile(encryptBytes, encryptFile);

        byte[] decryptBytes = AESUtil.decryptFile2Bytes(encryptFile, key);
        FileUtil.saveFile(decryptBytes, decryptFile);
    }
}