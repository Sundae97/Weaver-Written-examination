package com.sundae.filemanagerserver.util;

import com.sundae.filemanagerserver.Constant;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static void saveFile(InputStream inputStream, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file.getPath());
        byte[] bytes = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(bytes)) != -1){
            fileOutputStream.write(bytes, 0, length);
        }
        fileOutputStream.close();
        inputStream.close();
    }

    public static void saveFile(byte[] bytes, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file.getPath());
        fileOutputStream.write(bytes, 0, bytes.length);
        fileOutputStream.close();
    }

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    public static String getTodayPath(){
        String dirName = simpleDateFormat.format(new Date());
        dirName = Constant.SAVE_FILE_PATH + "/" + dirName;
        File file = new File(dirName);
        if(!file.exists())
            file.mkdirs();
        return file.getPath();
    }

}
