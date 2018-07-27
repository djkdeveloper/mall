/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50635
 Source Host           : localhost
 Source Database       : djk_mall

 Target Server Type    : MySQL
 Target Server Version : 50635
 File Encoding         : utf-8

 Date: 07/28/2018 06:52:09 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `djk_authority`
-- ----------------------------
DROP TABLE IF EXISTS `djk_authority`;
CREATE TABLE `djk_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(32) DEFAULT NULL COMMENT '权限名称',
  `action` varchar(255) DEFAULT NULL COMMENT '动作 目前有POST GET PUT DELETE',
  `url` varchar(256) DEFAULT '' COMMENT '权限对应的访问地址',
  `parentId` bigint(20) DEFAULT NULL COMMENT '父级id 如果是第一级  则为0',
  `grade` int(11) DEFAULT NULL COMMENT '级别 1 表示第一级 2表示第二级',
  `code` varchar(32) DEFAULT NULL COMMENT '菜单权限code ',
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COMMENT='管理员权限表';

-- ----------------------------
--  Records of `djk_authority`
-- ----------------------------
BEGIN;
INSERT INTO `djk_authority` VALUES ('1', '商品管理', null, '', '0', '1', 'goodsmanagerbase', '2018-07-09 22:30:41', '2018-07-09 22:30:43'), ('2', '商品管理', null, '', '1', '2', 'goodsmanager', '2018-07-09 22:31:32', '2018-07-09 22:31:34'), ('3', '商品配置', null, '', '1', '2', 'goodsconfiguration', '2018-07-09 22:32:32', '2018-07-09 22:32:35'), ('5', '商品列表', null, '', '2', '3', 'spulist', '2018-07-09 22:33:53', '2018-07-09 22:33:55'), ('6', '商品品牌', null, '', '3', '3', 'brandlist', '2018-07-09 22:34:40', '2018-07-09 22:34:42'), ('9', '商品分类', null, '', '3', '3', 'catelist', '2018-07-09 22:35:49', '2018-07-09 22:35:51'), ('10', '分页查询品牌信息', 'POST', '/brand/list', '6', '4', null, '2018-07-11 17:47:30', '2018-07-11 17:47:33'), ('11', '新增品牌信息', 'POST', '/brand', '6', '4', null, '2018-07-11 20:55:51', '2018-07-11 20:55:55'), ('12', '删除品牌信息', 'DELETE', '/brand', '6', '4', null, '2018-07-11 20:56:25', '2018-07-11 20:56:29'), ('13', '根据id查询品牌信息', 'GET', '/brand', '6', '4', null, '2018-07-11 20:56:58', '2018-07-11 20:57:01'), ('14', '更新品牌信息', 'PUT', '/brand', '6', '4', null, '2018-07-11 20:57:26', '2018-07-11 20:57:29'), ('15', '查询所有分类信息', 'GET', '/category/list', '9', '4', null, '2018-07-11 22:27:07', '2018-07-11 22:27:11'), ('16', '根据层级查询分类信息', 'GET', '/category/grade', '9', '4', null, '2018-07-12 10:13:34', '2018-07-12 10:13:38'), ('17', '新增分类信息', 'POST', '/category', '9', '4', null, '2018-07-12 10:58:48', '2018-07-12 10:58:51'), ('18', '根据分类id查询分类信息', 'GET', '/category', '9', '4', null, '2018-07-12 14:45:02', '2018-07-12 14:45:05'), ('19', '更新分类信息', 'PUT', '/category', '9', '4', null, '2018-07-12 14:58:33', '2018-07-12 14:58:35'), ('20', '删除分类信息', 'DELETE', '/category', '9', '4', null, '2018-07-12 15:16:10', '2018-07-12 15:16:13'), ('21', '分页查询商品信息', 'POST', '/spu/list', '5', '4', null, '2018-07-12 16:01:37', '2018-07-12 16:01:40'), ('22', '查询所有品牌', 'GET', '/spu/allbrands', '5', '4', null, '2018-07-12 17:12:39', '2018-07-12 17:12:41'), ('23', '查询所有一级分类', 'GET', '/spu/firstcategory', '5', '4', null, '2018-07-12 17:15:57', '2018-07-12 17:16:00'), ('24', '添加商品', 'POST', '/spu', '5', '4', null, '2018-07-12 17:43:09', '2018-07-12 17:43:12'), ('25', '删除商品', 'DELETE', '/spu', '5', '4', null, '2018-07-12 17:53:11', '2018-07-12 17:53:13'), ('26', '查询分类下的子分类', 'GET', '/spu/querycaterorychildren', '5', '4', null, '2018-07-12 20:25:18', '2018-07-12 20:25:22'), ('27', '根据商品id查询商品信息', 'GET', '/spu', '5', '4', null, '2018-07-12 22:05:58', '2018-07-12 22:06:00'), ('28', '更新商品信息', 'PUT', '/spu', '5', '4', null, '2018-07-12 22:17:57', '2018-07-12 22:18:00'), ('29', '会员管理', null, '', '0', '1', 'customermanagerbase', '2018-07-13 10:23:03', '2018-07-13 10:23:06'), ('30', '会员管理', null, '', '29', '2', 'customermanager', '2018-07-13 10:23:45', '2018-07-13 10:23:47'), ('31', '会员列表', null, '', '30', '3', 'customerlist', '2018-07-13 10:24:24', '2018-07-13 10:24:26'), ('32', '分页查询会员', 'POST', '/customer/list', '31', '4', null, '2018-07-13 10:44:00', '2018-07-13 10:44:02'), ('33', '新增会员', 'POST', '/customer', '31', '4', null, '2018-07-13 13:35:16', '2018-07-13 13:35:19'), ('34', '删除会员', 'DELETE', '/customer', '31', '4', null, '2018-07-13 13:43:11', null), ('35', '根据会员id查询会员信息', 'GET', '/customer', '31', '4', null, '2018-07-13 13:54:54', null), ('36', '更新会员信息', 'PUT', '/customer', '31', '4', null, '2018-07-13 14:22:21', null), ('37', '系统管理', null, '', '0', '1', 'systemmanagerbase', '2018-07-14 20:50:40', '2018-07-14 20:50:43'), ('38', '权限管理', null, '', '37', '2', 'authoritymanager', '2018-07-14 20:51:22', '2018-07-14 20:51:24'), ('39', '角色列表', null, '', '38', '3', 'rolelist', '2018-07-14 20:51:14', '2018-07-14 20:51:17'), ('40', '员工列表', null, '', '38', '3', 'stafflist', '2018-07-14 20:53:00', '2018-07-14 20:53:02'), ('41', '分页查询角色信息', 'POST', '/role/list', '39', '4', null, '2018-07-15 16:56:36', '2018-07-15 16:56:34'), ('42', '查询当前角色的权限', 'GET', '/role/authoritiesinduction', '39', '4', null, '2018-07-15 17:20:40', '2018-07-15 17:20:42'), ('43', '新增角色', 'POST', '/role', '39', '4', null, '2018-07-16 14:30:38', '2018-07-16 14:30:40'), ('44', '删除角色', 'DELETE', '/role', '39', '4', null, '2018-07-16 15:11:11', '2018-07-16 15:11:28'), ('45', '分页查询员工信息', 'POST', '/manager/list', '40', '4', null, '2018-07-16 15:41:51', '2018-07-16 15:41:53'), ('46', '查询员工创建的角色', 'GET', '/manager/roles', '40', '4', null, '2018-07-16 17:00:38', '2018-07-16 17:00:40'), ('47', '创建员工', 'POST', '/manager', '40', '4', null, '2018-07-16 17:45:37', '2018-07-16 17:45:39'), ('48', '删除员工', 'DELETE', '/manager', '40', '4', null, '2018-07-16 18:08:07', '2018-07-16 18:08:11'), ('49', '根据员工id查询员工信息', 'GET', '/manager', '40', '4', null, '2018-07-17 10:08:01', '2018-07-17 10:08:03'), ('50', '更新员工信息', 'PUT', '/manager', '40', '4', null, '2018-07-17 11:19:45', '2018-07-17 11:19:47'), ('51', '根据id查询角色', 'GET', '/role', '39', '4', null, '2018-07-20 13:42:51', '2018-07-20 13:42:54'), ('52', '更新角色', 'PUT', '/role', '39', '4', null, '2018-07-20 14:13:37', '2018-07-20 14:13:39');
COMMIT;

-- ----------------------------
--  Table structure for `djk_brand`
-- ----------------------------
DROP TABLE IF EXISTS `djk_brand`;
CREATE TABLE `djk_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) DEFAULT NULL COMMENT '品牌名称',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记 0 未删除 1 已删除',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='商品品牌';

-- ----------------------------
--  Records of `djk_brand`
-- ----------------------------
BEGIN;
INSERT INTO `djk_brand` VALUES ('1', '苹果', '0', '2018-07-24 16:07:03', null);
COMMIT;

-- ----------------------------
--  Table structure for `djk_category`
-- ----------------------------
DROP TABLE IF EXISTS `djk_category`;
CREATE TABLE `djk_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(16) DEFAULT NULL COMMENT '分类名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父id 如果没有父级 就为0 ',
  `sort` int(11) DEFAULT NULL COMMENT '排序 值越小越靠前',
  `grade` int(11) DEFAULT NULL COMMENT '层级 1 表示1级 2 表示2级 3表示3级',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记 0 未删除 1 已删除 默认0 ',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='商品分类表';

-- ----------------------------
--  Records of `djk_category`
-- ----------------------------
BEGIN;
INSERT INTO `djk_category` VALUES ('1', '手机', '0', '1', '1', '0', '2018-07-24 16:07:14', null), ('2', '苹果手机', '1', '1', '2', '0', '2018-07-24 16:07:24', null), ('3', 'iphone4', '2', '1', '3', '0', '2018-07-24 16:07:34', null);
COMMIT;

-- ----------------------------
--  Table structure for `djk_customer`
-- ----------------------------
DROP TABLE IF EXISTS `djk_customer`;
CREATE TABLE `djk_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(32) DEFAULT NULL COMMENT '会员名称',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `mobile` varchar(11) DEFAULT NULL COMMENT '会员手机号啊',
  `pic` varchar(256) DEFAULT NULL COMMENT '会员头像',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记  0 未删除 1已删除  默认0',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='会员表';

-- ----------------------------
--  Records of `djk_customer`
-- ----------------------------
BEGIN;
INSERT INTO `djk_customer` VALUES ('1', 'djk', 'E10ADC3949BA59ABBE56E057F20F883E', '15195812224', null, '0', '2018-07-26 13:43:20', null);
COMMIT;

-- ----------------------------
--  Table structure for `djk_manager`
-- ----------------------------
DROP TABLE IF EXISTS `djk_manager`;
CREATE TABLE `djk_manager` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主角id',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '是否删除 0 否1 是 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

-- ----------------------------
--  Records of `djk_manager`
-- ----------------------------
BEGIN;
INSERT INTO `djk_manager` VALUES ('1', 'djk', 'E10ADC3949BA59ABBE56E057F20F883E', '0', '2018-06-30 16:10:37', '0');
COMMIT;

-- ----------------------------
--  Table structure for `djk_manager_role`
-- ----------------------------
DROP TABLE IF EXISTS `djk_manager_role`;
CREATE TABLE `djk_manager_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `manager_id` bigint(20) DEFAULT NULL COMMENT '管理员id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='管理员和角色的关联表';

-- ----------------------------
--  Records of `djk_manager_role`
-- ----------------------------
BEGIN;
INSERT INTO `djk_manager_role` VALUES ('1', '1', '1');
COMMIT;

-- ----------------------------
--  Table structure for `djk_order`
-- ----------------------------
DROP TABLE IF EXISTS `djk_order`;
CREATE TABLE `djk_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `price` decimal(20,2) DEFAULT NULL COMMENT '订单价格',
  `status` char(1) DEFAULT NULL COMMENT '订单状态 1:待付款  （用户刚下单）2:代发货  （用户付完款 等待商城发货）3:代收货  （商城已经发货 等待用户确认收货） 4:已完成  5 取消订单',
  `cancel_reason` char(1) DEFAULT NULL COMMENT '订单取消原因 1:现在不想买 2:商品价格较贵 3:价格波动 4:商品缺货 5:重复下单',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '下单时间',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT '支付时间',
  `cancel_time` timestamp NULL DEFAULT NULL COMMENT '取消时间',
  `receiving_time` timestamp NULL DEFAULT NULL COMMENT '确认收货时间',
  `delivery_time` timestamp NULL DEFAULT NULL COMMENT '发货时间',
  `receipt_name` varchar(1024) DEFAULT NULL COMMENT '收货人姓名',
  `receipt_address` varchar(2046) DEFAULT NULL COMMENT '收货人地址',
  `receipt_mobile` varchar(11) DEFAULT NULL COMMENT '收货人手机号码',
  `remark` varchar(2046) DEFAULT NULL COMMENT '订单备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
--  Records of `djk_order`
-- ----------------------------
BEGIN;
INSERT INTO `djk_order` VALUES ('2', '1', '1259520.00', '1', null, '2018-07-27 14:06:18', null, null, null, null, null, null, null, null), ('3', '1', '1259520.00', '1', null, '2018-07-27 14:10:55', null, null, null, null, null, null, null, null), ('4', '1', '1259520.00', '1', null, '2018-07-27 14:11:42', null, null, null, null, null, null, null, null), ('6', '1', '251524.00', '1', null, '2018-07-27 14:31:28', null, null, null, null, null, null, null, null), ('7', '1', '3144050.00', '1', null, '2018-07-28 06:49:11', null, null, null, null, null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `djk_order_spu`
-- ----------------------------
DROP TABLE IF EXISTS `djk_order_spu`;
CREATE TABLE `djk_order_spu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `spu_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `num` int(11) DEFAULT NULL COMMENT '购买数量',
  `spu_name` varchar(1024) DEFAULT NULL COMMENT '商品名称',
  `spu_price` decimal(20,2) DEFAULT NULL COMMENT '商品价格',
  `spu_image` varchar(1024) DEFAULT NULL COMMENT '商品图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='订单和商品的关联表';

-- ----------------------------
--  Records of `djk_order_spu`
-- ----------------------------
BEGIN;
INSERT INTO `djk_order_spu` VALUES ('3', '2', '13', '20', 'djk手机11', '62881.00', null), ('4', '2', '14', '19', 'iphone4手机', '100.00', null), ('5', '3', '13', '20', 'djk手机11', '62881.00', null), ('6', '3', '14', '19', 'iphone4手机', '100.00', null), ('7', '4', '13', '20', 'djk手机11', '62881.00', null), ('8', '4', '14', '19', 'iphone4手机', '100.00', null), ('10', '6', '13', '4', 'djk手机11', '62881.00', null), ('11', '7', '13', '50', 'djk手机11', '62881.00', null);
COMMIT;

-- ----------------------------
--  Table structure for `djk_role`
-- ----------------------------
DROP TABLE IF EXISTS `djk_role`;
CREATE TABLE `djk_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建角色的员工id',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
--  Records of `djk_role`
-- ----------------------------
BEGIN;
INSERT INTO `djk_role` VALUES ('1', '超级管理员', '0', '2018-07-09 22:29:51', '2018-07-09 22:29:53');
COMMIT;

-- ----------------------------
--  Table structure for `djk_role_authority`
-- ----------------------------
DROP TABLE IF EXISTS `djk_role_authority`;
CREATE TABLE `djk_role_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id ',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `authority_id` bigint(20) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=355 DEFAULT CHARSET=utf8mb4 COMMENT='角色和权限的关联表';

-- ----------------------------
--  Records of `djk_role_authority`
-- ----------------------------
BEGIN;
INSERT INTO `djk_role_authority` VALUES ('1', '1', '1'), ('2', '1', '2'), ('3', '1', '3'), ('5', '1', '5'), ('6', '1', '6'), ('9', '1', '9'), ('10', '1', '10'), ('11', '1', '11'), ('12', '1', '12'), ('13', '1', '13'), ('14', '1', '14'), ('15', '1', '15'), ('16', '1', '16'), ('17', '1', '17'), ('18', '1', '18'), ('19', '1', '19'), ('20', '1', '20'), ('21', '1', '21'), ('22', '1', '22'), ('23', '1', '23'), ('24', '1', '24'), ('25', '1', '25'), ('26', '1', '26'), ('27', '1', '27'), ('28', '1', '28'), ('29', '1', '29'), ('30', '1', '30'), ('31', '1', '31'), ('32', '1', '32'), ('33', '1', '33'), ('34', '1', '34'), ('35', '1', '35'), ('36', '1', '36'), ('37', '1', '37'), ('38', '1', '38'), ('39', '1', '39'), ('40', '1', '40'), ('41', '1', '41'), ('42', '1', '42'), ('43', '1', '43'), ('48', '1', '44'), ('95', '1', '45'), ('190', '1', '46'), ('195', '1', '47'), ('241', '1', '48'), ('291', '1', '49'), ('292', '1', '50'), ('293', '1', '51'), ('354', '1', '52');
COMMIT;

-- ----------------------------
--  Table structure for `djk_shopping_cart`
-- ----------------------------
DROP TABLE IF EXISTS `djk_shopping_cart`;
CREATE TABLE `djk_shopping_cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '注解id',
  `spu_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `num` int(11) DEFAULT NULL COMMENT '购买数量',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

-- ----------------------------
--  Table structure for `djk_spu`
-- ----------------------------
DROP TABLE IF EXISTS `djk_spu`;
CREATE TABLE `djk_spu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(1024) DEFAULT NULL COMMENT '商品名称',
  `price` decimal(20,2) DEFAULT NULL COMMENT '价格',
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  `brand_id` bigint(20) DEFAULT NULL COMMENT '品牌id',
  `first_cate_id` bigint(20) DEFAULT NULL COMMENT '一级分类id',
  `second_cate_id` bigint(20) DEFAULT NULL COMMENT '二级分类id',
  `third_cate_id` bigint(20) DEFAULT NULL COMMENT '三级分类id',
  `pic` varchar(1024) DEFAULT NULL COMMENT '商品图片地址',
  `del_flag` char(1) DEFAULT NULL COMMENT '删除标记 0 未删除 1 已删除',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- ----------------------------
--  Records of `djk_spu`
-- ----------------------------
BEGIN;
INSERT INTO `djk_spu` VALUES ('1', 'iphone4手机', '6288.00', '100', '1', '1', '2', '3', null, '1', '2018-07-24 16:07:51', null), ('2', 'iphone手机', '6288.00', '100', '1', '1', '2', '3', null, '1', '2018-07-24 16:10:41', null), ('3', '测试手机', '6498.00', '100', '1', '1', '2', '3', null, '1', '2018-07-24 16:13:56', null), ('4', '我的手机', '100.00', '100', '1', '1', '2', '3', null, '1', '2018-07-24 16:54:23', null), ('5', '华为手机', '1000.00', '100', '1', '1', '2', '3', null, '1', '2018-07-24 16:57:28', null), ('6', '魅族手机', '100.00', '100', '1', '1', '2', '3', null, '1', '2018-07-24 17:01:50', null), ('7', '哈哈手机', '1231.00', '1231', '1', '1', '2', '3', null, '1', '2018-07-24 17:03:30', null), ('8', '12312', '321.00', '1231', '1', '1', '2', '3', null, '1', '2018-07-24 17:03:58', null), ('9', 'ces ', '321.00', '1231', '1', '1', '2', '3', null, '1', '2018-07-24 17:16:48', null), ('10', '124手机', '123.00', '123', '1', '1', '2', '3', null, '1', '2018-07-24 17:19:35', null), ('11', '好的手机', '100.00', '100', '1', '1', '2', '3', null, '1', '2018-07-24 17:22:58', null), ('12', '987手机', '100.00', '10', '1', '1', '2', '3', null, '1', '2018-07-24 17:32:58', null), ('13', 'djk手机11', '62881.00', '46', '1', '1', '2', '3', null, '0', '2018-07-24 17:40:37', '2018-07-24 17:43:02'), ('14', 'iphone4手机', '100.00', '100', '1', '1', '2', '3', null, '0', '2018-07-26 14:37:19', null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
