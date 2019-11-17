package com.sundae.filemanagerserver.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/17 上午10:53
 * @Description
 */
public class FileUtil {

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

}
