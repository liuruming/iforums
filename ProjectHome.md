### iforums简介 ###
iforums是为了更好的使jforum适用于中文论坛系统，而做的二次开发。iforums精仿国内最流行的Discuz! 论坛系统，提供了一个界面美观，高效率的论坛，一个易于使用的管理面板，先进的权限控制系统等等。

iforums采用了内置MVC框架，它可以部署在任何Servlet容器或者应用服务器如Tomcat等。iforums设计简洁和容易实现定制和扩展。iforums使用自由的BSD下开源许可证发布的。

现在已经发布了可用版本1.0,最新版本1.0.1。如果使用中有问题可发送邮件告知，以便尽快更正。<br />
在修改jforum的过程中，阅读了网上很多jforum相关的文章，有的文章在工程的wiki中做了转载，在此对作者表示感谢。
### [下载](http://code.google.com/p/iforums/downloads/list) ###
[下载安装文件](http://iforums.googlecode.com/files/iforums1.0.1.war)
### 安装注意事项 ###
1.请使用如下SQL创建数据库：
CREATE DATABASE `iforums` /`*`!40100 DEFAULT CHARACTER SET utf8 `*`/;
<br />   数据库名字iforums可修改<br />
2.推荐使用图形界面安装。如果想重新安装,请停止服务器，删除文件"WEB-INF/config/configration.properties"，然后再启动服务器，访问首页即可重新安装<br />
### 论坛风格预览 ###
<img src='http://iforums.googlecode.com/files/preview2.png'>