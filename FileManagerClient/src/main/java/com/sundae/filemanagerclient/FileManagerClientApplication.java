package com.sundae.filemanagerclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FileManagerClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileManagerClientApplication.class, args);
    }

}
