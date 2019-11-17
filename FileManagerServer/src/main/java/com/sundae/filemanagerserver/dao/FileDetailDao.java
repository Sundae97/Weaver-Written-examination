package com.sundae.filemanagerserver.dao;

import com.sundae.filemanagerserver.Constant;
import com.sundae.filemanagerserver.bean.FileDetail;
import com.sundae.filemanagerserver.util.DerbyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/17 下午4:51
 * @Description
 */
public class FileDetailDao {
    private static final Logger logger = LoggerFactory.getLogger(FileDetailDao.class);

    private DerbyHelper derbyHelper = DerbyHelper.getInstance();

    private static final String TABLE_NAME = Constant.TABLE_NAME;
    private static final String INSERT_SQL = "INSERT INTO " + TABLE_NAME + " " +
            "(file_sourcename,file_name,file_type,file_size,create_date,file_path,secret_key) " +
            "VALUES(?,?,?,?,?,?,?)";

    public int addFileDetail(FileDetail fileDetail){
        try {
            return derbyHelper.executeUpdate(INSERT_SQL, new Object[]{
                    fileDetail.getFileSourceName(),
                    fileDetail.getFileName(),
                    fileDetail.getFileType(),
                    fileDetail.getFileSize(),
                    fileDetail.getCreateTime(),
                    fileDetail.getFilePath(),
                    fileDetail.getSecretKey()
            });
        } catch (SQLException e) {
            logger.error("addFileDetail()", e);
            return -1;
        }
    }

    private static final String QUERY_BY_UUID_SQL = "SELECT * FROM " + TABLE_NAME + "" +
            " WHERE file_name=?";
    public FileDetail getFileDetailByUUID(String uuid){

        try {
            ResultSet resultSet = derbyHelper.executeQuery(QUERY_BY_UUID_SQL, new Object[]{uuid});
            if(!resultSet.next())
                return null;
            FileDetail fileDetail = new FileDetail();
            fileDetail.setId(resultSet.getLong("id"));
            fileDetail.setFileName(resultSet.getString("file_name"));
            fileDetail.setCreateTime(resultSet.getDate("create_date"));
            fileDetail.setSecretKey(resultSet.getString("secret_key"));
            fileDetail.setFilePath(resultSet.getString("file_path"));
            fileDetail.setFileSize(resultSet.getLong("file_size"));
            fileDetail.setFileType(resultSet.getString("file_type"));
            fileDetail.setFileSourceName(resultSet.getString("file_sourcename"));
            resultSet.close();
            return fileDetail;
        } catch (SQLException e) {
            logger.error("getFileDetailByUUID()", e);
            return null;
        }
    }

}
