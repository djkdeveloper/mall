# mall
商城管理端后端

## 简介
该项目是一个简单的商城，用于springcloud的练手 后端是基于springcloud 开发的

## 准备工作
- 该项目是基于maven的项目，里面用到了lombok  所以导入该项目后 ide需要安装lombok
- jdk的版本是java8
- 项目加入了elasticsearch elasticesarch 的版本必须是 elasticsearch-5.5.0 使用的是ik分词器  ik和es配套的版本是:elasticsearch-analysis-ik-5.5.0

## 模块说明
- common_utils 工具类
- mall_goods 商品模块
- mall_order 订单模块
- mall_search 搜索模块
- mall_server eureka服务模块
- mall_server_admin 监控模块
- mall_shopping_cart 购物车模块
- mall_user 用户模块
- mall_zuul 后端网关路由
- mall_zuul_site 前端网关路由


## 项目本地部署方案
该项目还在研发阶段，目前还没正式部署 所以暂时先提供开发阶段的环境搭建

拿到项目后导入sql  sql是djk_mall.sql 数据库使用的是mysql 5.7.20

启动项目顺序
mall-server -> mall_zuul->mall_zuul_site -> mall_goods -> mall_user->mall_order->mall_search->mall_shopping_cart

后端启动完成 登录用户名：djk  密码：123456

服务监控访问：http://localhost:8088/

## 关于图片存储
图片存储建议使用第三方比如又拍云

这边使用的是本地图片，本地图片需要一个服务器提供图片访问的能力

我这边使用是用tomcat作为图片服务器器 具体配置为 参数mall_goods模块中的配置文件

upload:
  path:  /Users/dujinkai/Desktop/apache-tomcat-7.0.82/webapps/ROOT/    tomcat位置
  accessPath: http://localhost:8888/      访问图片的路径

## 管理端截图

![image](https://raw.githubusercontent.com/djkdeveloper/mall/master/images/login.png)

![image](https://raw.githubusercontent.com/djkdeveloper/mall/master/images/desk.png)

## 后端管理的前端地址
- 前端github地址：https://github.com/djkdeveloper/mall-view.git
- 前端码云地址：https://gitee.com/djkdeveloper/mall-view.git

## 联系我
QQ:547265436

## License
Apache License Version 2.0



