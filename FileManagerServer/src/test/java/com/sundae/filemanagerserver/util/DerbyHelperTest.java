package com.sundae.filemanagerserver.util;

import com.sundae.filemanagerserver.Constant;
import com.sundae.filemanagerserver.bean.FileDetail;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/17 上午10:44
 * @Description
 */
public class DerbyHelperTest {

    @Test
    public void showAllData() throws SQLException {
        ResultSet resultSet = DerbyHelper.getInstance().executeQuery("SELECT * FROM " + Constant.TABLE_NAME);
        while (resultSet.next()){
            FileDetail fileDetail = new FileDetail();
            fileDetail.setId(resultSet.getLong("id"));
            fileDetail.setFileName(resultSet.getString("file_name"));
            fileDetail.setCreateTime(resultSet.getLong("create_date"));
            fileDetail.setSecretKey(resultSet.getString("secret_key"));
            fileDetail.setFilePath(resultSet.getString("file_path"));
            fileDetail.setFileSize(resultSet.getLong("file_size"));
            fileDetail.setFileType(resultSet.getString("file_type"));
            fileDetail.setFileSourceName(resultSet.getString("file_sourcename"));
            System.out.println(fileDetail.toString());
        }
    }

}