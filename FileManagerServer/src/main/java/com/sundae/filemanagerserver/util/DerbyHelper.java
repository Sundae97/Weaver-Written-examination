package com.sundae.filemanagerserver.util;

import com.sundae.filemanagerserver.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/15 下午8:08
 * @Description
 */

public class DerbyHelper {

    private static DerbyHelper instance = null;

    private static final Logger logger = LoggerFactory.getLogger(DerbyHelper.class);

    private static final String TABLE_CREATE_SQL = "CREATE TABLE " + Constant.TABLE_NAME + "(\n" +
            "  id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,\n" +
            "  file_sourcename VARCHAR(255) NOT NULL,\n" +
            "  file_name VARCHAR(255) NOT NULL,\n" +
            "  file_type VARCHAR(30) NOT NULL,\n" +
            "  file_size BIGINT NOT NULL,\n" +
            "  create_date TIMESTAMP NOT NULL,\n" +
            "  file_path VARCHAR(255) NOT NULL,\n" +
            "  secret_key VARCHAR(255) NOT NULL\n" +
            ")";

    public static DerbyHelper getInstance(){
        if(instance == null){
            synchronized (DerbyHelper.class){
                if(instance == null){
                    instance = new DerbyHelper();
                }
            }
        }
        return instance;
    }

    private Connection connection = null;

    private DerbyHelper(){
        try {
            System.setProperty("derby.stream.error.file","logs/derby.log");
            connect();
            createDefaultTable();
        } catch (ClassNotFoundException e) {
            logger.error("DerbyHelper()", e);
        } catch (SQLException e) {
            logger.error("DerbyHelper()", e);
        }
    }

    private void connect() throws ClassNotFoundException, SQLException {
        Class.forName(Constant.DERBY_DRIVER);
        connection = DriverManager.getConnection(Constant.CONNECT_URL);
        logger.info("derby connect success");
    }

    private void createDefaultTable(){
        try {
            Statement statement = connection.createStatement();
            statement.execute(TABLE_CREATE_SQL);
            logger.info("default table created");
        } catch (SQLException e) {
            if(DerbyHelper.isExistsTable(e)){
                logger.info("Table already exists");
            }else{
                logger.error("createDefaultTable()", e);
            }
        }
    }

    public boolean execute(String sql) {
        try{
            Statement statement = connection.createStatement();
            return statement.execute(sql);
        }catch (SQLException e){
            logger.error("execute()" , e);
            return false;
        }
    }

    public ResultSet executeQuery(String sql) {
        try{
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        }catch (SQLException e){
            logger.error("executeQuery()" , e);
            return null;
        }
    }

    public int executeUpdate(String sql) {
        try{
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        }catch (SQLException e){
            logger.error("executeUpdate()" , e);
            return -1;
        }
    }

    private static void shutdownDerby(){
        boolean gotSQLExc = false;
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException se)  {
            if ( se.getSQLState().equals("XJ015") ) {
                gotSQLExc = true;
            }
        }
        if (!gotSQLExc) {
            logger.info("Database did not shut down normally");
        }  else  {
            logger.info("Database shut down normally");
        }
    }

    private static boolean isExistsTable(Throwable throwable){
        return throwable.getMessage().contains("already exists in Schema");
    }

}
