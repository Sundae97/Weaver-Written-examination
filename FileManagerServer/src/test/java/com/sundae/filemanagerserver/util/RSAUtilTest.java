package com.sundae.filemanagerserver.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/17 下午1:48
 * @Description
 */
public class RSAUtilTest {

    private static String pubKeyFile = "/key/pub.key";
    private static String priKeyFile = "/key/pri.key";
    private static String text = "message";

    @Test
    public void testRSA() throws Exception {
        String messageEn = RSAUtil.encrypt(text, RSAUtil.getPublicKeyFromResource(pubKeyFile));
        System.out.println("加密后的字符串为:" + messageEn);

        String messageDe = RSAUtil.decrypt(messageEn, RSAUtil.getPrivateKeyFromResource(priKeyFile));
        System.out.println("还原后的字符串为:" + messageDe);
    }

    @Test
    public void testRSASign(){
        String sign = RSAUtil.sign(text, RSAUtil.getPrivateKeyFromResource(priKeyFile));
        System.out.println("Sign : " + sign);
        System.out.println("Verify : " + RSAUtil.verifySign(text, RSAUtil.getPublicKeyFromResource(pubKeyFile), sign));
    }
}