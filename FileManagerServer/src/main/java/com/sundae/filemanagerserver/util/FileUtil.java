package com.sundae.filemanagerserver.util;

import com.sundae.filemanagerserver.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

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

    public static byte[] readFile2Bytes(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        int len = fileInputStream.available();
        byte[] bytes = new byte[len];
        fileInputStream.read(bytes, 0, len);
        fileInputStream.close();
        return bytes;
    }

    public static void readFile2OutputStream(File file, OutputStream outputStream) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[4096];
        logger.info("readFile2OutputStream : "+ fileInputStream.available());
        int len = 0;
        while ((len=fileInputStream.read(bytes)) != -1){
            outputStream.write(bytes, 0, len);
            outputStream.flush();
        }
        fileInputStream.close();
        outputStream.close();
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
