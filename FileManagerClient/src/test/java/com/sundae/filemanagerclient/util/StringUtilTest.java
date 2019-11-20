package com.sundae.filemanagerclient.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/20 下午4:56
 * @Description
 */
class StringUtilTest {

    @Test
    void getRandomString() {
        System.out.println(StringUtil.getRandomString(10));
    }
}