package com.sundae.filemanagerserver.servlet;

import com.sundae.filemanagerserver.service.MetaDataService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/15 下午7:30
 * @Description
 */
public class GetMetaDataServlet extends HttpServlet {

    private MetaDataService metaDataService = new MetaDataService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        metaDataService.getMetaDataByUUID(req, resp);
    }
}
