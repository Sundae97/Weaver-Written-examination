package com.sundae.filemanagerclient.config;


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
        requestTemplate.header("X-SID", "message");
        requestTemplate.header("X-Signature", "Co0O9CUAPX+vjVncICuea0tYv9uk2KUrTMbWiD7tT9nnaeh+ZMFWUKJnskSFu6s3Uu2tWRju9iReM3znmMXWvLhHvUjN7mzw67lhuWP2lJLqKY6Ko/mcNmXtYvuAdsmSHBp4xIaXa9SNIme6vM8uxYyyrwf4FFmggUhjVXxWOss=");
    }
}
