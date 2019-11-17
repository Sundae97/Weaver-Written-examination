package com.sundae.filemanagerserver.servlet;

import com.sundae.filemanagerserver.service.DownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/15 下午7:29
 * @Description
 */
public class DownloadServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DownloadServlet.class);

    private DownloadService downloadService = new DownloadService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        downloadService.download(req, resp);
    }
}
