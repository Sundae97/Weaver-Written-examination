package com.sundae.filemanagerserver.util;

import com.sundae.filemanagerserver.Constant;
import com.sundae.filemanagerserver.bean.FileDetail;
import com.sundae.filemanagerserver.dao.FileDetailDao;
import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;


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
        byte[] key = AESUtil.generateRandomKey();
        byte[] result = AESUtil.encrypt(text, key);
        System.out.println(Base64.getEncoder().encodeToString(result));
        byte[] decryptResult = AESUtil.decrypt(result, key);
        System.out.println(new String(decryptResult, "UTF-8"));

        String keyStr = Base64.getEncoder().encodeToString(key);
        System.out.println(keyStr);
        String s = RSAUtil.encrypt(keyStr, RSAUtil.getPublicKeyFromResource(Constant.PUBLIC_KEY_PATH));
        s = RSAUtil.decrypt(s, RSAUtil.getPrivateKeyFromResource(Constant.PRIVATE_KEY_PATH));
        System.out.println(s);

        byte[] key2 = Base64.getDecoder().decode(s);
        decryptResult = AESUtil.decrypt(result, key2);
        System.out.println(new String(decryptResult, "UTF-8"));
    }

    @Test
    public void encryptFile() throws Exception {
        byte[] key = AESUtil.generateRandomKey();
        File file = new File("./test.jpg");
        File encryptFile = new File("./encryptFile.jpg");
        File decryptFile = new File("./decryptFile.jpg");
        byte[] encryptBytes = AESUtil.encryptFile2Bytes(new FileInputStream(file), key);
        FileUtil.saveFile(encryptBytes, encryptFile);

        byte[] decryptBytes = AESUtil.decryptFile2Bytes(encryptFile, key);
        FileUtil.saveFile(decryptBytes, decryptFile);
    }

    @Test
    public void decryptFile() throws Exception {
        FileDetailDao fileDetailDao = new FileDetailDao();
        FileDetail fileDetail = fileDetailDao.getFileDetailByUUID("8ff0d116-2c9a-4e5c-ab8a-098eaacc5f25");
        FileInputStream fileInputStream = new FileInputStream(new File(fileDetail.getFilePath()));
        int length = fileInputStream.available();
        byte[] bytes = new byte[length];
        fileInputStream.read(bytes);
        fileInputStream.close();

        String base64AESKey = RSAUtil.decrypt(fileDetail.getSecretKey(), RSAUtil.getPrivateKeyFromResource(Constant.PRIVATE_KEY_PATH));
        System.out.println(base64AESKey);
        byte[] key = Base64.getDecoder().decode(base64AESKey);
        byte[] fileBytes = AESUtil.decrypt(bytes, key);
        System.out.println(fileBytes);

    }
}