package com.sundae.filemanagerserver;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/15 下午7:18
 * @Description
 */
public class Constant {

    public static String DERBY_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    public static String CONNECT_URL = "jdbc:derby:file_db;create=true";

    public static final int SERVER_PORT = 9999;
    public static final String PATH_WEBAPP = "./web";
    public static final String PATH_CONTEXT = "/";
    public static final String PATH_DESCRIPTOR = "./WEB-INF/web.xml";

    public static final String TABLE_CREATE_SQL = "CREATE TABLE file_detail_table(\n" +
            "  id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,\n" +
            "  file_sourcename VARCHAR(255) NOT NULL,\n" +
            "  file_name VARCHAR(255) NOT NULL,\n" +
            "  file_type VARCHAR(30) NOT NULL,\n" +
            "  file_size BIGINT NOT NULL,\n" +
            "  create_date TIMESTAMP NOT NULL,\n" +
            "  file_path VARCHAR(255) NOT NULL,\n" +
            "  file_meta_code VARCHAR(255) NOT NULL\n" +
            ")";

}
