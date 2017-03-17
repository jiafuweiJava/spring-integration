# spring-integration
Spring SpringMVC MyBatis 整合二维码合成、生成、解析项目
-------------------
在这个项目里主要实现了以下技术：
* 二维码的生成、解析（包含含添加LOGO）
* Freemarker 对HTML页面的处理 
* swagger 自动生成API文档
* SpringMVC 上传下载
* log4j日志打印
* RESTful API 的支持
-------------------
  
  
  项目的下载
  	$ git clone https://github.com/jiafuweiJava/spring-integration.git

  
  就通过maven把它build成一个IDE项目或者eclipse项目，执行以下命令，打开CMD：
  

    $ cd spring-integration
    $ mvn eclipse:eclipse 或者  mvn idea:idea
    
    
  通过Eclipse或IDEA导入即可，然后修改jdbc数据库地址，导入项目下begin.sql。
-------------------
* 通过浏览器打开：http://localhost:8080/spring-mvc-web
* API接口查看地址：http://localhost:8080/spring-mvc-web/swagger-ui.html
* 博客教程地址：http://www.cnblogs.com/jiafuwei
-------------------
### 注意
	在本地电脑测试APP二维码的时候，请通过ip访问项目路径，列如http://192.168.1.100:8080/spring-mvc-web/
	我本机的ip为192.168.1.100，然后确保手机和电脑在同一局域网内，这样页面才能正常跳转

