package com.sundae.filemanagerclient.util;

import java.util.Random;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/20 下午4:54
 * @Description
 */
public class StringUtil {

    private static final String RANDOM_RES_STR="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String getRandomString(int len){
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<len;i++){
            int number=random.nextInt(62);
            sb.append(RANDOM_RES_STR.charAt(number));
        }
        return sb.toString();
    }

}
