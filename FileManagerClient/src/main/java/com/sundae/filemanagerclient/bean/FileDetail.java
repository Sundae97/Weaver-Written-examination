package com.sundae.filemanagerclient.bean;

import com.sundae.filemanagerclient.util.DateFormatUtil;

import java.sql.Timestamp;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/19 下午1:29
 * @Description
 */
public class FileDetail {
    private long id;
    private String fileSourceName;
    private String fileName;
    private String fileType;
    private long fileSize;
    private Timestamp createTime;
    private String filePath;
    private String secretKey;

    public FileDetail() {
    }

    public FileDetail(String fileSourceName, String fileName, String fileType, long fileSize, Timestamp createTime, String filePath, String secretKey) {
        this.fileSourceName = fileSourceName;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.createTime = createTime;
        this.filePath = filePath;
        this.secretKey = secretKey;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileSourceName() {
        return fileSourceName;
    }

    public void setFileSourceName(String fileSourceName) {
        this.fileSourceName = fileSourceName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String toString() {
        return "FileDetail{" +
                "id=" + id +
                ", fileSourceName='" + fileSourceName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileSize=" + fileSize +
                ", createTime=" + DateFormatUtil.formatTimestamp(createTime) +
                ", filePath='" + filePath + '\'' +
                ", secretKey='" + secretKey + '\'' +
                '}';
    }
}
