package com.sundae.filemanagerserver;

import com.sundae.filemanagerserver.util.DerbyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/15 下午6:53
 * @Description
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        DerbyHelper.getInstance();

        JettyServer jettyServer = new JettyServer();
        jettyServer.start();
    }

}
