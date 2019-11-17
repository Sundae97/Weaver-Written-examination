package com.sundae.filemanagerserver.dao;

import com.sundae.filemanagerserver.bean.FileDetail;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/17 下午5:09
 * @Description
 */
public class FileDetailDaoTest {

    @Test
    public void addFileDetail() {
        for (int i = 0; i < 3; i++) {
            FileDetail fileDetail = new FileDetail();
            fileDetail.setFileSourceName("test.jpg");
            fileDetail.setFileType("jpg");
            fileDetail.setFileSize(1024);
            fileDetail.setFilePath("/pic/test");
            fileDetail.setCreateTime(new Date());
            fileDetail.setFileName("asbduibiubiasd==");
            fileDetail.setSecretKey("aoyisudgfaofyuiadiyufgbas");
            int k = new FileDetailDao().addFileDetail(fileDetail);
            System.out.println(k+"");
        }
    }
}