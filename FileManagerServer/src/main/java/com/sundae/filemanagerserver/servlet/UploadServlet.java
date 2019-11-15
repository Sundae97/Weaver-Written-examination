package com.sundae.filemanagerserver.servlet;

import org.eclipse.jetty.server.Request;

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

    private static final MultipartConfigElement MULTI_PART_CONFIG = new MultipartConfigElement("temp");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();

//        String fileDir = getServletContext().getRealPath(".");
        File file = new File("./files");
        if(!file.exists())
            file.mkdir();

        if(req.getContentType() != null && req.getContentType().startsWith("multipart/")){
            req.setAttribute(Request.MULTIPART_CONFIG_ELEMENT, MULTI_PART_CONFIG);
        }
        Part part = req.getPart("file");
        String disposition = part.getHeader("Content-Disposition");
        String fileType = disposition.substring(disposition.lastIndexOf("."),disposition.length()-1);
        String fileName = UUID.randomUUID()+"";
        InputStream inputStream = part.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(file.getPath() + "/" + fileName + "." + fileType);
        byte[] bytes = new byte[1024];
        int length = 0;
        while ((length=inputStream.read(bytes)) != -1){
            fileOutputStream.write(bytes, 0, length);
        }
        fileOutputStream.close();
        inputStream.close();
        writer.println("upload success");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("HELLO");
    }
}
