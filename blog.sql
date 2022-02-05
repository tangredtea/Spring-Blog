-- --------------------------------------------------------
-- 主机:                           localhost
-- 服务器版本:                        5.7.32 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 blog 的数据库结构
CREATE DATABASE IF NOT EXISTS `blog` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `blog`;

-- 导出  表 blog.t_blog 结构
CREATE TABLE IF NOT EXISTS `t_blog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '博客表主键id',
  `title` varchar(255) DEFAULT NULL COMMENT '博客标题',
  `description` text COMMENT '文章描述',
  `content` mediumtext COMMENT '博文内容',
  `first_picture` varchar(255) DEFAULT NULL COMMENT '博文封面',
  `views` int(11) DEFAULT '0' COMMENT '文章阅读量',
  `flag` bit(1) DEFAULT NULL COMMENT '文章状态位，1：原创，0：转载',
  `appreciation` bit(1) DEFAULT NULL COMMENT '文章状态位，1：开启，0：关闭',
  `share_statement` bit(1) DEFAULT NULL COMMENT '分享状态位，1：开启，0：关闭',
  `commentable` bit(1) DEFAULT NULL COMMENT '评论状态位，1：开启，0：关闭',
  `published` bit(1) DEFAULT NULL COMMENT '发布状态位，1：开启，0：关闭',
  `recommend` bit(1) DEFAULT NULL COMMENT '推荐状态位，1：开启，0：关闭',
  `create_time` datetime DEFAULT NULL COMMENT '文章创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '文章修改时间',
  `type_id` int(11) DEFAULT NULL COMMENT '关联的分类id',
  `user_id` int(11) DEFAULT NULL COMMENT '关联的用户id',
  `tag_ids` varchar(100) DEFAULT NULL COMMENT '关联标签',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `KEY1` (`type_id`,`user_id`,`tag_ids`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 正在导出表  blog.t_blog 的数据：~11 rows (大约)
/*!40000 ALTER TABLE `t_blog` DISABLE KEYS */;
REPLACE INTO `t_blog` (`id`, `title`, `description`, `content`, `first_picture`, `views`, `flag`, `appreciation`, `share_statement`, `commentable`, `published`, `recommend`, `create_time`, `update_time`, `type_id`, `user_id`, `tag_ids`) VALUES
	(1, '111', '111', '11', 'https://t1.picb.cc/uploads/2021/03/23/ZboxAv.png', 0, b'1', b'1', b'1', b'1', b'1', b'1', '2022-02-05 22:36:13', '2022-02-05 22:36:14', 1, 1, '1');
/*!40000 ALTER TABLE `t_blog` ENABLE KEYS */;

-- 导出  表 blog.t_blog_tags 结构
CREATE TABLE IF NOT EXISTS `t_blog_tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '关联表主键',
  `tag_id` int(11) DEFAULT NULL COMMENT '标签id',
  `blog_id` bigint(20) DEFAULT NULL COMMENT '博文id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 正在导出表  blog.t_blog_tags 的数据：~51 rows (大约)
/*!40000 ALTER TABLE `t_blog_tags` DISABLE KEYS */;
REPLACE INTO `t_blog_tags` (`id`, `tag_id`, `blog_id`) VALUES
	(1, 1, 1);
/*!40000 ALTER TABLE `t_blog_tags` ENABLE KEYS */;

-- 导出  表 blog.t_friend 结构
CREATE TABLE IF NOT EXISTS `t_friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '友链表主键',
  `blog_address` varchar(255) NOT NULL COMMENT '友链地址',
  `blog_name` varchar(255) NOT NULL COMMENT '友链名字',
  `picture_address` varchar(255) NOT NULL COMMENT '友链图标',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- 正在导出表  blog.t_friend 的数据：~11 rows (大约)
/*!40000 ALTER TABLE `t_friend` DISABLE KEYS */;
REPLACE INTO `t_friend` (`id`, `blog_address`, `blog_name`, `picture_address`, `create_time`) VALUES
	(1, 'baidu.com', '百度', 'https://t1.picb.cc/uploads/2021/03/23/ZboxAv.png', '2022-02-05 22:36:56');
/*!40000 ALTER TABLE `t_friend` ENABLE KEYS */;

-- 导出  表 blog.t_message 结构
CREATE TABLE IF NOT EXISTS `t_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '留言表主键id',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `parent_message_id` bigint(20) DEFAULT NULL COMMENT '父留言id',
  `admin_message` bit(1) NOT NULL COMMENT '是否为管理员评论',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- 正在导出表  blog.t_message 的数据：~12 rows (大约)
/*!40000 ALTER TABLE `t_message` DISABLE KEYS */;
REPLACE INTO `t_message` (`id`, `nickname`, `email`, `content`, `avatar`, `create_time`, `parent_message_id`, `admin_message`) VALUES
	(1, 'sss', 'ss@qq.com', 'ss', 'https://t1.picb.cc/uploads/2021/03/23/ZboxAv.png', '2022-02-05 22:37:52', 1, b'1'),
	(2, 'ss', 'ss@qq.com', 'asfafa', 'https://t1.picb.cc/uploads/2021/03/23/ZboxAv.png', '2022-02-05 22:41:25', -1, b'1');
/*!40000 ALTER TABLE `t_message` ENABLE KEYS */;

-- 导出  表 blog.t_tag 结构
CREATE TABLE IF NOT EXISTS `t_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签表主键',
  `name` varchar(255) NOT NULL COMMENT '标签名字',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 正在导出表  blog.t_tag 的数据：~7 rows (大约)
/*!40000 ALTER TABLE `t_tag` DISABLE KEYS */;
REPLACE INTO `t_tag` (`id`, `name`) VALUES
	(1, 'test');
/*!40000 ALTER TABLE `t_tag` ENABLE KEYS */;

-- 导出  表 blog.t_type 结构
CREATE TABLE IF NOT EXISTS `t_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类主键id',
  `name` varchar(255) NOT NULL COMMENT '分类名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 正在导出表  blog.t_type 的数据：~7 rows (大约)
/*!40000 ALTER TABLE `t_type` DISABLE KEYS */;
REPLACE INTO `t_type` (`id`, `name`) VALUES
	(1, 'test');
/*!40000 ALTER TABLE `t_type` ENABLE KEYS */;

-- 导出  表 blog.t_user 结构
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表主键id',
  `username` varchar(255) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '用户密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 正在导出表  blog.t_user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
REPLACE INTO `t_user` (`id`, `username`, `password`, `nickname`, `email`, `avatar`) VALUES
	(1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '学编程的文若', '110@qq.com', 'https://t1.picb.cc/uploads/2021/03/23/ZboxAv.png');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
