package com.sundae.filemanagerserver.util;

import com.sundae.filemanagerserver.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/15 下午8:08
 * @Description
 */

public class DerbyHelper {

    private static DerbyHelper instance = null;

    private static final Logger logger = LoggerFactory.getLogger(DerbyHelper.class);

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
    private Statement statement = null;

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
        statement = connection.createStatement();
        logger.info("derby connect success");
    }

    private void createDefaultTable(){
        try {
            statement.execute(Constant.TABLE_CREATE_SQL);
            logger.info("default table created");
        } catch (SQLException e) {
            if(DerbyHelper.isExistsTable(e)){
                logger.info("Table already exists");
            }else{
                logger.error("createDefaultTable()", e);
            }
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
