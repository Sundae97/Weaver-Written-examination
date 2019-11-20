package com.sundae.filemanagerclient.service;

import com.alibaba.fastjson.JSON;
import com.sundae.filemanagerclient.Constant;
import com.sundae.filemanagerclient.bean.FileDetail;
import com.sundae.filemanagerclient.util.AESUtil;
import com.sundae.filemanagerclient.util.RSAUtil;
import feign.FeignException;
import feign.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Base64;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/19 下午1:35
 * @Description
 */
@Service
public class FileDetailService {

    private static final Logger logger = LoggerFactory.getLogger(FileDetailService.class);

    @Autowired
    private IHttpService httpService;

    public String upload(MultipartFile multipartFile){
        if(!multipartFile.isEmpty()){
            logger.info("upload -> " + multipartFile.getOriginalFilename());
            return httpService.uploadFile(multipartFile);
        }else{
            return "No upload file!";
        }
    }

    public void download(String uuid, HttpServletResponse httpServletResponse) throws Exception {
        Response response = httpService.downloadFile(uuid);
        Response.Body body = response.body();

        InputStream inputStream = body.asInputStream();
        int len = inputStream.available();
        if(len <= 0){
            logger.error("file available len <= 0 -> " + uuid);
            httpServletResponse.sendError(404, "没有目标文件");
            return;
        }

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = inputStream.read(buffer))) {
            output.write(buffer, 0, n);
        }
        byte[] bytes = output.toByteArray();
        inputStream.close();
        output.close();
        String fileDetailStr = null;
        try{
            fileDetailStr = httpService.getFileDetailByUUID(uuid);
        }catch (FeignException.NotFound exception){
            httpServletResponse.sendError(404,"Not found file!");
            return;
        }
        if(StringUtils.isEmpty(fileDetailStr)){
            logger.error("download -> " + uuid);
            return;
        }
        FileDetail fileDetail = JSON.parseObject(fileDetailStr, FileDetail.class);
        String secretKey = fileDetail.getSecretKey();
        String aesKey = RSAUtil.decrypt(secretKey, RSAUtil.getPrivateKeyFromResource(Constant.PRIVATE_KEY_PATH));

        byte[] resultBytes = AESUtil.decrypt(bytes, Base64.getDecoder().decode(aesKey));

        String fileName = URLEncoder.encode(fileDetail.getFileSourceName(), "UTF-8");
        httpServletResponse.setContentType("application/octet-stream;charset=UTF-8");
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        httpServletResponse.addHeader("Content-Length", "" + resultBytes.length);

        OutputStream out = httpServletResponse.getOutputStream();
        out.write(resultBytes);
        out.flush();
        out.close();
    }

    public String getFileDetailList(){
        return httpService.getFileDetailList();
    }

}
