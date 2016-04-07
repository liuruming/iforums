开发环境搭建

iforum的源码工程是个java工程，导入eclipse以后无法直接发布。
iforum的开发环境搭建是把整个工程目录当做一个应用发布的。所以需要使用eclipse以外的容器来部署。

步骤1：
下载jdk、tomcat、myeclipse、iforum源码压缩包（iforums1.0.1-src.zip）
安装配置jdk、tomcat、myeclipse 将工程导入myeclipse

步骤2：
tomcat配置虚拟目录：
host节点中加入如下配置
<Context path="/iforums" docBase="本地工程目录"
> privileged="true" antiResourceLocking="false" antiJARLocking="false"/>

步骤3
删除工程中 WEB-INF/config/configration.properties 文件。
建立数据库：
CREATE DATABASE iforums /**!40100 DEFAULT CHARACTER SET utf8**/;

步骤4
启动tomcat
访问 http://localhost:8080/iforums/install/install.html?module=install&action=welcome

看到安装界面就算成功。

ps:
1、不能使用myeclipse自带的tomcat部署
2、本地工程目录 不是工程目录下的应用目录


