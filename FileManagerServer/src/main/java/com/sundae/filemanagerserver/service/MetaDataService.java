package com.sundae.filemanagerserver.service;

import com.sundae.filemanagerserver.bean.FileDetail;
import com.sundae.filemanagerserver.dao.FileDetailDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/18 上午10:34
 * @Description
 */
public class MetaDataService {

    private static final Logger logger = LoggerFactory.getLogger(MetaDataService.class);

    private FileDetailDao fileDetailDao = new FileDetailDao();
    private static final int LIST_LIMIT = 10;

    public void getMetaDataByUUID(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uuid = req.getParameter("uuid");
        FileDetail fileDetail = fileDetailDao.getFileDetailByUUID(uuid);
        if(fileDetail == null){
            resp.sendError(404, "Not found file!");
        }else{
            resp.getWriter().println(fileDetail.toJsonString());
        }
    }

    public void getLatestMetaDataList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<FileDetail> list = fileDetailDao.getLatestFileDetailList(LIST_LIMIT);
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{\"length\":" + list.size() + ",");
        jsonBuilder.append("\"list\":[");
        for (int i = 0; i < list.size(); i++){
            FileDetail fileDetail = list.get(i);
            jsonBuilder.append(fileDetail.toJsonString());
            if(i != list.size() - 1)
                jsonBuilder.append(",");
        }
        jsonBuilder.append("]}");
        resp.getWriter().println(jsonBuilder.toString());
    }

}
