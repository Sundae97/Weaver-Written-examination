package com.sundae.filemanagerserver.dao;

import com.sundae.filemanagerserver.bean.FileDetail;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Test
    public void getFileDetailByUUID() {
        FileDetailDao fileDetailDao = new FileDetailDao();
        FileDetail fileDetail = fileDetailDao.getFileDetailByUUID("eab462c0-e6f6-4ac2-ae4f-c407b7a81eff");
        System.out.println(fileDetail);
        System.out.println(fileDetail.toJsonString());
    }

    @Test
    public void getLatestFileDetailList() {
        List<FileDetail> list = new FileDetailDao().getLatestFileDetailList(10);
        for (FileDetail fileDetail: list) {
            System.out.println(fileDetail.toString());
        }
    }
}