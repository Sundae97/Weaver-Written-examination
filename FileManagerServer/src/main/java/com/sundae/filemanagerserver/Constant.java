package com.sundae.filemanagerserver;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/15 下午7:18
 * @Description
 */
public class Constant {

    public static String DERBY_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    public static final String DATABASE_NAME = "file_db";
    public static String CONNECT_URL = "jdbc:derby:"+ DATABASE_NAME +";create=true";
    public static final String TABLE_NAME = "file_detail_table";

    public static final int SERVER_PORT = 9999;
    public static final String PATH_WEBAPP = "./web";
    public static final String PATH_CONTEXT = "/";
    public static final String PATH_DESCRIPTOR = "./WEB-INF/web.xml";

}
