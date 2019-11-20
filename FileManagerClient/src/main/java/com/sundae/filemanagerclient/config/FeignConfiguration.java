package com.sundae.filemanagerclient.config;


import com.sundae.filemanagerclient.Constant;
import com.sundae.filemanagerclient.util.RSAUtil;
import com.sundae.filemanagerclient.util.StringUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/19 下午12:52
 * @Description
 */
@Configuration
public class FeignConfiguration implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String sid = StringUtil.getRandomString(10);
        String sign = RSAUtil.sign(sid, RSAUtil.getPrivateKeyFromResource(Constant.PRIVATE_KEY_PATH));
        requestTemplate.header("X-SID", sid);
        requestTemplate.header("X-Signature", sign);
    }
}
