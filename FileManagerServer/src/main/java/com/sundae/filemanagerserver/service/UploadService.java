package com.sundae.filemanagerserver.service;

import com.sundae.filemanagerserver.Constant;
import com.sundae.filemanagerserver.bean.FileDetail;
import com.sundae.filemanagerserver.dao.FileDetailDao;
import com.sundae.filemanagerserver.util.AESUtil;
import com.sundae.filemanagerserver.util.DateFormatUtil;
import com.sundae.filemanagerserver.util.FileUtil;
import com.sundae.filemanagerserver.util.RSAUtil;
import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/17 下午4:48
 * @Description
 */
public class UploadService {
    private static final Logger logger = LoggerFactory.getLogger(UploadService.class);

    private FileDetailDao fileDetailDao = new FileDetailDao();

    private static final MultipartConfigElement MULTI_PART_CONFIG = new MultipartConfigElement("temp");

    public void upload(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if(req.getContentType() != null && req.getContentType().startsWith("multipart/")){
            req.setAttribute(Request.MULTIPART_CONFIG_ELEMENT, MULTI_PART_CONFIG);
        }

        PrintWriter writer = resp.getWriter();

        Part part = req.getPart("file");
        if(part == null){
            writer.println("No file!");
            return;
        }
        String disposition = part.getHeader("Content-Disposition");
        String fileType = disposition.substring(disposition.lastIndexOf(".")+1,disposition.length()-1);
        String fileName = UUID.randomUUID()+"";
        String filePath = FileUtil.getTodayPath() + "/" + fileName;

        byte[] randomKey = AESUtil.generateRandomKey();
        byte[] encryptCode = AESUtil.encryptFile2Bytes(part.getInputStream(), randomKey);

        File file = new File(filePath);
        FileUtil.saveFile(encryptCode, file);

        FileDetail fileDetail = new FileDetail();
        fileDetail.setFileSourceName(part.getSubmittedFileName());
        fileDetail.setFileType(fileType);
        fileDetail.setFileSize(part.getSize());
        fileDetail.setFilePath(filePath);
        fileDetail.setCreateTime(DateFormatUtil.getNowTimestamp());
        fileDetail.setFileName(file.getName());
        String aesKeyBase64 = Base64.getEncoder().encodeToString(randomKey);
        System.out.println(aesKeyBase64);
        String rsaEncryptKey = RSAUtil.encrypt(aesKeyBase64, RSAUtil.getPublicKeyFromResource(Constant.PUBLIC_KEY_PATH));
        fileDetail.setSecretKey(rsaEncryptKey);
        fileDetailDao.addFileDetail(fileDetail);

        writer.println(fileName);
    }

}
