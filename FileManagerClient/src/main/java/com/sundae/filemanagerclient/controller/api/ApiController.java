package com.sundae.filemanagerclient.controller.api;

import com.alibaba.fastjson.JSON;
import com.sundae.filemanagerclient.bean.FileDetail;
import com.sundae.filemanagerclient.controller.service.FileDetailService;
import com.sundae.filemanagerclient.controller.service.IHttpService;
import feign.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/19 上午11:26
 * @Description
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private IHttpService httpService;
    @Autowired
    private FileDetailService fileDetailService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile multipartFile){
        return fileDetailService.upload(multipartFile);
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public void download(@RequestParam("uuid") String uuid, HttpServletResponse response){
        try {
            fileDetailService.download(uuid, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getMetaDataList", method = RequestMethod.GET)
    @ResponseBody
    public String getMetaDataList(){
        return httpService.getFileDetailList();
    }

}
