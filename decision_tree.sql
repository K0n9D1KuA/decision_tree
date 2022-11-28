/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50738
 Source Host           : localhost:3306
 Source Schema         : decision_tree

 Target Server Type    : MySQL
 Target Server Version : 50738
 File Encoding         : 65001

 Date: 25/11/2022 19:25:19
*/

-- 所需要的sql文件

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tree_node
-- ----------------------------
DROP TABLE IF EXISTS `tree_node`;
CREATE TABLE `tree_node`  (
  `tree_node_id` bigint(20) NULL DEFAULT NULL COMMENT '主键',
  `tree_id` bigint(20) NULL DEFAULT NULL COMMENT '决策树id',
  `rule_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '决策规则',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点描述信息',
  `tree_node_type` tinyint(4) NULL DEFAULT NULL COMMENT '类型[1:叶子节点，0：非叶子节点]'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tree_node_link
-- ----------------------------
DROP TABLE IF EXISTS `tree_node_link`;
CREATE TABLE `tree_node_link`  (
  `tree_node_from_id` bigint(20) NULL DEFAULT NULL COMMENT '从哪里来',
  `tree_node_to_id` bigint(20) NULL DEFAULT NULL COMMENT '到哪里去',
  `limit_type` tinyint(4) NULL DEFAULT NULL COMMENT '树枝判断类型  1:=  2:> 3:< 4:>= 5:<= 6:可以扩展的其他类型 枚举',
  `limit_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '树枝判断值'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tree_rich
-- ----------------------------
DROP TABLE IF EXISTS `tree_rich`;
CREATE TABLE `tree_rich`  (
  `tree_id` bigint(20) NOT NULL COMMENT '主键',
  `tree_root_id` bigint(20) NULL DEFAULT NULL COMMENT '根节点id 入口',
  `tree_rich_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '决策树名字',
  PRIMARY KEY (`tree_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
