package com.sundae.filemanagerserver;

import com.sundae.filemanagerserver.filter.AuthFilter;
import com.sundae.filemanagerserver.servlet.DownloadServlet;
import com.sundae.filemanagerserver.servlet.GetMetaDataListServlet;
import com.sundae.filemanagerserver.servlet.GetMetaDataServlet;
import com.sundae.filemanagerserver.servlet.UploadServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/15 下午8:48
 * @Description
 */
public class JettyServer {
    private Server server = new Server();

    private static final Logger logger = LoggerFactory.getLogger(JettyServer.class);

    public JettyServer(){
        server.setStopAtShutdown(true);     //jvm退出时自动关闭
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(Constant.SERVER_PORT);
        connector.setReuseAddress(true);   //端口占用是不会报错
        server.setConnectors(new Connector[]{connector});

//        WebAppContext webContext = new WebAppContext();
//        webContext.setDescriptor(Constant.PATH_DESCRIPTOR);
//        webContext.setContextPath(Constant.PATH_CONTEXT);
//        webContext.setResourceBase(Constant.PATH_WEBAPP);
//        webContext.setClassLoader(Thread.currentThread().getContextClassLoader());

        ServletContextHandler servletContextHandler = new ServletContextHandler();
        servletContextHandler.addServlet(UploadServlet.class, "/upload");
        servletContextHandler.addServlet(DownloadServlet.class, "/download");
        servletContextHandler.addServlet(GetMetaDataServlet.class, "/getMetaData");
        servletContextHandler.addServlet(GetMetaDataListServlet.class, "/getMetaDataList");

        servletContextHandler.addFilter(AuthFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        server.setHandler(servletContextHandler);
    }

    public void start(){
        try {
            server.start();
            logger.info("Jetty Server started");
            server.join();
        } catch (Exception e) {
            logger.error("start()", e);
        }
    }

}
