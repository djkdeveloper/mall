# mall
商城管理端后端


该项目是一个简单的商城，用于springcloud的练手 后端是基于springcloud 开发的


该项目还在研发阶段，目前还没正式部署 所以暂时先提供开发阶段的环境搭建

该项目是基于maven的项目，里面用到了lombok  所以导入该项目后 ide需要安装lombok

jdk的版本是java8

common_utils 工具类＜/br＞
mall_goods 商品模块＜/br＞
mall_order 订单模块＜/br＞
mall_search 搜索模块＜/br＞
mall_server eureka服务模块＜/br＞
mall_shopping_cart 购物车模块＜/br＞
mall_user 用户模块＜/br＞
mall_zuul 后端网关路由＜/br＞
mall_zuul_site 前端网关路由＜/br＞
拿到项目后导入sql  sql是djk_mall.sql 数据库使用的是mysql 5.7.20＜/br＞

项目加入了elasticsearch
这边要注意 elasticesarch 的版本必须是 elasticsearch-5.5.0
使用的是ik分词器  ik和es配套的版本是:elasticsearch-analysis-ik-5.5.0

启动项目顺序
mall-server -> mall_zuul -> mall_goods -> mall_user

后端启动完成 登录用户名：djk  密码：123456

![image](https://raw.githubusercontent.com/djkdeveloper/mall/master/images/login.png)

![image](https://raw.githubusercontent.com/djkdeveloper/mall/master/images/desk.png)


前端github地址：https://github.com/djkdeveloper/mall-view.git
前端码云地址：https://gitee.com/djkdeveloper/mall-view.git



License
Apache License Version 2.0



