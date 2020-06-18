/*
 Navicat Premium Data Transfer

 Source Server         : FXXKU
 Source Server Type    : MySQL
 Source Server Version : 50540
 Source Host           : localhost:3306
 Source Schema         : ssm

 Target Server Type    : MySQL
 Target Server Version : 50540
 File Encoding         : 65001

 Date: 08/06/2020 22:13:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phoneNum` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('E61D65F673D54F68B0861025C69773DB', '张三', '小三', '18888888888', 'zs@163.com');

-- ----------------------------
-- Table structure for order_traveller
-- ----------------------------
DROP TABLE IF EXISTS `order_traveller`;
CREATE TABLE `order_traveller`  (
  `orderId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `travellerId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`orderId`, `travellerId`) USING BTREE,
  INDEX `travellerId`(`travellerId`) USING BTREE,
  CONSTRAINT `order_traveller_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `orders` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_traveller_ibfk_2` FOREIGN KEY (`travellerId`) REFERENCES `traveller` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of order_traveller
-- ----------------------------
INSERT INTO `order_traveller` VALUES ('0E7231DC797C486290E8713CA3C6ECCC', '3FE27DF2A4E44A6DBC5D0FE4651D3D3E');
INSERT INTO `order_traveller` VALUES ('2FF351C4AC744E2092DCF08CFD314420', '3FE27DF2A4E44A6DBC5D0FE4651D3D3E');
INSERT INTO `order_traveller` VALUES ('3081770BC3984EF092D9E99760FDABDE', '3FE27DF2A4E44A6DBC5D0FE4651D3D3E');
INSERT INTO `order_traveller` VALUES ('55F9AF582D5A4DB28FB4EC3199385762', '3FE27DF2A4E44A6DBC5D0FE4651D3D3E');
INSERT INTO `order_traveller` VALUES ('5DC6A48DD4E94592AE904930EA866AFA', '3FE27DF2A4E44A6DBC5D0FE4651D3D3E');
INSERT INTO `order_traveller` VALUES ('96CC8BD43C734CC2ACBFF09501B4DD5D', '3FE27DF2A4E44A6DBC5D0FE4651D3D3E');
INSERT INTO `order_traveller` VALUES ('A0657832D93E4B10AE88A2D4B70B1A28', 'EE7A71FB6945483FBF91543DBE851960');
INSERT INTO `order_traveller` VALUES ('E4DD4C45EED84870ABA83574A801083E', 'EE7A71FB6945483FBF91543DBE851960');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `orderNum` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `orderTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `peopleCount` int(11) NULL DEFAULT NULL,
  `orderDesc` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payType` int(11) NULL DEFAULT NULL,
  `orderStatus` int(11) NULL DEFAULT NULL,
  `productId` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `memberId` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `orderNum`(`orderNum`) USING BTREE,
  INDEX `memberId`(`memberId`) USING BTREE,
  INDEX `productId`(`productId`) USING BTREE,
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`memberId`) REFERENCES `member` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('0E7231DC797C486290E8713CA3C6ECCC', '12345', '2020-04-07 00:00:00', 2, '没什么', 0, 1, '676C5BD1D35E429A8C2E114939C5685A', 'E61D65F673D54F68B0861025C69773DB');
INSERT INTO `orders` VALUES ('11CC8BD43C734CC2ACBFF09501B4DD4D', '88888', '2020-04-07 00:00:00', 2, '没什么', 0, 1, '12B7ABF2A4C544568B0A7C69F36BF8B7', 'E61D65F673D54F68B0861025C69773DB');
INSERT INTO `orders` VALUES ('18CC8BD43C734CC2ACBFF09501B4DD4D', '99999', '2020-04-07 00:00:00', 2, '没什么', 0, 1, '12B7ABF2A4C544568B0A7C69F36BF8B7', 'E61D65F673D54F68B0861025C69773DB');
INSERT INTO `orders` VALUES ('2FF351C4AC744E2092DCF08CFD314420', '67898', '2020-04-07 00:00:00', 2, '没什么', 0, 1, '12B7ABF2A4C544568B0A7C69F36BF8B7', 'E61D65F673D54F68B0861025C69773DB');
INSERT INTO `orders` VALUES ('3081770BC3984EF092D9E99760FDABDE', '55555', '2020-04-07 00:00:00', 2, '没什么', 0, 1, '9F71F01CB448476DAFB309AA6DF9497F', 'E61D65F673D54F68B0861025C69773DB');
INSERT INTO `orders` VALUES ('55F9AF582D5A4DB28FB4EC3199385762', '33333', '2020-04-07 00:00:00', 2, '没什么', 0, 1, '9F71F01CB448476DAFB309AA6DF9497F', 'E61D65F673D54F68B0861025C69773DB');
INSERT INTO `orders` VALUES ('5DC6A48DD4E94592AE904930EA866AFA', '54321', '2020-04-07 00:00:00', 2, '没什么', 0, 1, '676C5BD1D35E429A8C2E114939C5685A', 'E61D65F673D54F68B0861025C69773DB');
INSERT INTO `orders` VALUES ('91CC8BD43C734CC2ACBFF09501B4DD4D', '777', '2020-04-07 00:00:00', 2, '没什么', 0, 1, '12B7ABF2A4C544568B0A7C69F36BF8B7', 'E61D65F673D54F68B0861025C69773DB');
INSERT INTO `orders` VALUES ('91CC8BD43C734CC2ACBFF09501B4DD5D', '66666', '2020-04-07 00:00:00', 2, '没什么', 0, 1, '12B7ABF2A4C544568B0A7C69F36BF8B7', 'E61D65F673D54F68B0861025C69773DB');
INSERT INTO `orders` VALUES ('96CC8BD43C734CC2ACBFF09501B4DD5D', '22222', '2020-04-07 00:00:00', 2, '没什么', 0, 1, '12B7ABF2A4C544568B0A7C69F36BF8B7', 'E61D65F673D54F68B0861025C69773DB');
INSERT INTO `orders` VALUES ('A0657832D93E4B10AE88A2D4B70B1A28', '98765', '2020-04-07 00:00:00', 2, '没什么', 0, 1, '12B7ABF2A4C544568B0A7C69F36BF8B7', 'E61D65F673D54F68B0861025C69773DB');
INSERT INTO `orders` VALUES ('CA005CF1BE3C4EF68F88ABC7DF30E976', '44444', '2020-04-07 00:00:00', 2, '没什么', 0, 1, '9F71F01CB448476DAFB309AA6DF9497F', 'E61D65F673D54F68B0861025C69773DB');
INSERT INTO `orders` VALUES ('E4DD4C45EED84870ABA83574A801083E', '11111', '2020-04-07 00:00:00', 2, '没什么', 0, 1, '12B7ABF2A4C544568B0A7C69F36BF8B7', 'E61D65F673D54F68B0861025C69773DB');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `permissionName` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('30b16059a59411ea8adf847beb4175b4', 'user findAll', '/user/findAll.do');
INSERT INTO `permission` VALUES ('30b25340a59411ea8adf847beb4175b4', 'user findById', '/user/findById.do');
INSERT INTO `permission` VALUES ('316d2f16a65711ea8adf847beb4175b4', 'role findAll', '/role/findAll.do');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `productNum` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `productName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cityName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DepartureTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `productPrice` double(11, 0) NULL DEFAULT NULL,
  `productDesc` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `productStatus` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `productNum`(`productNum`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1234BD1D35E429A8C2E114939C5685A', 'itcast-004', '北京三日游', '北京', '2020-04-07 00:00:00', 1200, '不错的旅行', 1);
INSERT INTO `product` VALUES ('12B7ABF2A4C544568B0A7C69F36BF8B7', 'itcast-003', '上海五日游', '上海', '2020-06-06 00:00:00', 1800, '魔都我来了', 0);
INSERT INTO `product` VALUES ('36a057eaa0dc11ea8adf847beb4175b4', 'itcast-22222', '广州一日游', '大连', '2020-05-24 07:05:00', 100, 'qqq', 1);
INSERT INTO `product` VALUES ('676C5BD1D35E429A8C2E114939C5685A', 'itcast-002', '北京三日游', '北京', '2020-04-07 00:00:00', 1200, '不错的旅行', 1);
INSERT INTO `product` VALUES ('976fa90fa0da11ea8adf847beb4175b4', 'itcast111111', '广州一日游', '大连', '2020-05-24 07:05:00', 100, '1', 1);
INSERT INTO `product` VALUES ('9F71F01CB448476DAFB309AA6DF9497F', 'itcast-001', '北京三日游', '北京', '2020-06-07 00:00:00', 1200, '不错的旅行', 1);
INSERT INTO `product` VALUES ('fbf2ce7fa1ab11ea8adf847beb4175b4', 'aaa', '广州一日游', '大连', '2020-05-24 07:05:00', 100, 'aaa', 1);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `roleName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roleDesc` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ADMIN', 'vip');
INSERT INTO `role` VALUES ('2', 'USER', 'vip');
INSERT INTO `role` VALUES ('268d18cca65911ea8adf847beb4175b4', 'aaa', 'aaa');
INSERT INTO `role` VALUES ('53bb0d52a65311ea8adf847beb4175b4', 'GUEST', '测试账户');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `roleId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `permissionId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`roleId`, `permissionId`) USING BTREE,
  INDEX `roleId`(`permissionId`) USING BTREE,
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`permissionId`) REFERENCES `permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '30b16059a59411ea8adf847beb4175b4');
INSERT INTO `role_permission` VALUES ('2', '30b16059a59411ea8adf847beb4175b4');
INSERT INTO `role_permission` VALUES ('1', '30b25340a59411ea8adf847beb4175b4');
INSERT INTO `role_permission` VALUES ('1', '316d2f16a65711ea8adf847beb4175b4');

-- ----------------------------
-- Table structure for syslog
-- ----------------------------
DROP TABLE IF EXISTS `syslog`;
CREATE TABLE `syslog`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `visitTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `executionTime` int(11) NULL DEFAULT NULL,
  `method` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of syslog
-- ----------------------------
INSERT INTO `syslog` VALUES ('83041b59a98511ea8adf847beb4175b4', '2020-06-08 12:41:55', 'sherlock', '127.0.0.1', '/sysLogfindAll.do', 18, '[类名] com.abel.ssm.controller.SysLogController[方法名] findAll');
INSERT INTO `syslog` VALUES ('8e40a6f4a98511ea8adf847beb4175b4', '2020-06-08 12:42:13', 'sherlock', '127.0.0.1', '/sysLogfindAll.do', 5, '[类名] com.abel.ssm.controller.SysLogController[方法名] findAll');
INSERT INTO `syslog` VALUES ('a06f6e43a98311ea8adf847beb4175b4', '2020-06-08 12:28:25', 'sherlock', '127.0.0.1', '/permissionfindAll.do', 7, '[类名] com.abel.ssm.controller.PermissionController[方法名] findAll');
INSERT INTO `syslog` VALUES ('b732d1dea98211ea8adf847beb4175b4', '2020-06-08 12:21:54', 'sherlock', '127.0.0.1', '/product/findAll.do', 28, '[类名] com.abel.ssm.controller.ProductController[方法名] findAll');
INSERT INTO `syslog` VALUES ('f92dde59a98211ea8adf847beb4175b4', '2020-06-08 12:23:44', 'sherlock', '127.0.0.1', '/orders/findAll.do', 0, '[类名] com.abel.ssm.controller.OrdersController[方法名] findAll');

-- ----------------------------
-- Table structure for traveller
-- ----------------------------
DROP TABLE IF EXISTS `traveller`;
CREATE TABLE `traveller`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phoneNum` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `credentialsType` int(11) NULL DEFAULT NULL,
  `credentialsNum` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `travellerType` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of traveller
-- ----------------------------
INSERT INTO `traveller` VALUES ('3FE27DF2A4E44A6DBC5D0FE4651D3D3E', '张龙', '男', '13333333333', 0, '123456789009876543', 0);
INSERT INTO `traveller` VALUES ('EE7A71FB6945483FBF91543DBE851960', '张小龙', '男', '15555555555', 0, '987654321123456789', 1);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phoneNum` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `orderNum`(`email`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '123@163.com', 'sherlock', '$2a$10$asToxE4Ox2NXR2JPVxrvr.yLPUTxwhHpZN8bBtUcfhm8K0YwnOJMS', '15941999999', 1);
INSERT INTO `users` VALUES ('2', '124@163.com', 'tom', '123', '15941999999', 1);
INSERT INTO `users` VALUES ('680ab400a58b11ea8adf847beb4175b4', '15941999082@163.com', 'fox', '$2a$10$FaIYAngxiN7fnQ.8QyQoLegjF0tGDwae1.dDC9AHuTx5QIEBuhdle', '+15941999082', 1);

-- ----------------------------
-- Table structure for users_role
-- ----------------------------
DROP TABLE IF EXISTS `users_role`;
CREATE TABLE `users_role`  (
  `userId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `roleId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`userId`, `roleId`) USING BTREE,
  INDEX `userId`(`userId`) USING BTREE,
  INDEX `users_role_ibfk_2`(`roleId`) USING BTREE,
  CONSTRAINT `users_role_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `users_role_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users_role
-- ----------------------------
INSERT INTO `users_role` VALUES ('1', '1');
INSERT INTO `users_role` VALUES ('2', '2');
INSERT INTO `users_role` VALUES ('680ab400a58b11ea8adf847beb4175b4', '53bb0d52a65311ea8adf847beb4175b4');

SET FOREIGN_KEY_CHECKS = 1;
