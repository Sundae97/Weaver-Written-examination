# Weaver-Written-examination
泛微 契约锁 笔试题



## 启动方式

### Server(port:9999)

```
进入项目目录下bin/server  
执行java -jar FileManagerServer.jar
```

### Client([http://localhost:8080](http://localhost:8080))

```
进入项目目录下bin/client  
执行java -jar filemanagerclient-0.0.1-SNAPSHOT.jar
```



## 完成进度

### Server

- [x] 上传接口
- [x] 下载接口
- [x] 获取文件元数据接口
- [x] 接口权限校验
- [x] 扩展：文件查询接口，查询最近上传的10个文件元数据Json数组

### Client

- [x] 前端界面
- [x] 接口校验
- [x] 上传接口
- [x] 下载接口(+解密文件)
- [x] 扩展：列表展示，最近十个上传文件列表
- [x] 扩展：Client端支持集群部署，多个Client上传文件均存储在一个Server端



##  使用框架

### Server

-  Jetty

- Logger

- Derby

- Junit

### Client

- SpringBoot
- SpringMVC
- Thymeleaf
- OpenFeign  
- FastJson



## 开发环境

系统：Ubuntu 18.04 destop

开发工具：IDEA

JDK版本：1.8.0_221 (HopSpot 64-Bit)

构建工具：Maven 3.6.1



## 测试环境

系统：Ubuntu 18.04 destop

JDK版本：1.8.0_221 (HopSpot 64-Bit)

浏览器：Chrome 78.0.3904.97（正式版本） （64 位）

