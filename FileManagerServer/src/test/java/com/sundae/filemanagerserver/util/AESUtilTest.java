package com.sundae.filemanagerserver.util;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.junit.Test;


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
}