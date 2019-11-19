package com.sundae.filemanagerclient.controller.service;

import com.sundae.filemanagerclient.config.FeignConfiguration;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/19 下午12:45
 * @Description
 */
@Service
@FeignClient(name = "httpService", url = "http://localhost:9999", configuration = FeignConfiguration.class)
public interface IHttpService {

    @RequestMapping(value = "upload", method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadFile(@RequestPart("file") MultipartFile file);

    @RequestMapping(value = "download", method = RequestMethod.GET)
    Response downloadFile(@RequestParam("uuid") String uuid);

    @RequestMapping(value = "getMetaData", method = RequestMethod.GET)
    String getFileDetailByUUID(@RequestParam("uuid") String uuid);

    @RequestMapping(value = "getMetaDataList", method = RequestMethod.GET)
    String getFileDetailList();

}
