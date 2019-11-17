package com.sundae.filemanagerserver.servlet;

import com.sundae.filemanagerserver.service.UploadService;
import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.UUID;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/15 下午6:55
 * @Description
 */
@WebServlet
@MultipartConfig
public class UploadServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(UploadServlet.class);
    private UploadService uploadService = new UploadService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        try {
            uploadService.upload(req, resp);
        } catch (Exception e) {
            logger.error("UploadServlet", e);
            resp.getWriter().println("Upload error");
        }
    }
}
