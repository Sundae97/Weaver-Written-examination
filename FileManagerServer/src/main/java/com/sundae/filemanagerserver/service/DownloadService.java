package com.sundae.filemanagerserver.service;

import com.sundae.filemanagerserver.bean.FileDetail;
import com.sundae.filemanagerserver.dao.FileDetailDao;
import com.sundae.filemanagerserver.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/17 下午8:57
 * @Description
 */
public class DownloadService {

    private static final Logger logger = LoggerFactory.getLogger(DownloadService.class);
    private FileDetailDao fileDetailDao = new FileDetailDao();

    public void download(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uuid = req.getParameter("uuid");
        FileDetail fileDetail = fileDetailDao.getFileDetailByUUID(uuid);
        File file = new File(fileDetail.getFilePath());
        FileUtil.readFile2OutputStream(file, resp.getOutputStream());
    }

}
