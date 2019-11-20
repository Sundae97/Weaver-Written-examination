package com.sundae.filemanagerclient.util;


import com.sundae.filemanagerclient.Constant;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/20 下午4:30
 * @Description
 */
public class DateFormatUtil {

    private static final String DATE_FORMAT_STRING = Constant.DATE_FORMAT_STRING;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STRING);

    public static String formatTimestamp(Timestamp timestamp){
        return dateFormat.format(timestamp);
    }

    public static Timestamp getNowTimestamp(){
        return new Timestamp(System.currentTimeMillis());
    }

}
