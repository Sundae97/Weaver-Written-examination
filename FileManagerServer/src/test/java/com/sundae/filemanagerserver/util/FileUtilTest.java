package com.sundae.filemanagerserver.util;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/17 下午1:52
 * @Description
 */
public class FileUtilTest {

    @Test
    public void readStringFromResource() throws IOException {
        System.out.println(FileUtil.readStringFromResource("/key/pri.key"));
    }
}