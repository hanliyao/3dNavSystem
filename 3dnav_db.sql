-- phpMyAdmin SQL Dump
-- version 3.4.10.1
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2016 年 12 月 01 日 03:20
-- 服务器版本: 5.0.22
-- PHP 版本: 5.3.10

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `3dnav_db`
--

-- --------------------------------------------------------

--
-- 表的结构 `lt_access`
--

CREATE TABLE IF NOT EXISTS `lt_access` (
  `role_id` int(5) NOT NULL,
  `node_id` int(5) NOT NULL,
  KEY `role_id` (`role_id`),
  KEY `node_id` (`node_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `lt_access`
--

INSERT INTO `lt_access` (`role_id`, `node_id`) VALUES
(2, 296),
(2, 301),
(2, 299),
(2, 300),
(2, 307),
(2, 306),
(2, 305),
(2, 304),
(2, 303),
(2, 302),
(2, 127),
(2, 126),
(2, 51),
(2, 50),
(4, 126),
(4, 127),
(4, 155),
(4, 156),
(4, 158),
(4, 159),
(4, 171),
(4, 261),
(4, 263),
(4, 262),
(4, 264),
(4, 265),
(4, 271),
(4, 272),
(4, 273),
(4, 274),
(4, 276),
(6, 155),
(6, 156),
(6, 158),
(6, 159),
(6, 171),
(6, 261),
(6, 263),
(6, 262),
(6, 264),
(6, 265),
(6, 256),
(6, 260),
(6, 259),
(6, 258),
(6, 257),
(6, 223),
(6, 227),
(6, 226),
(6, 225),
(6, 224),
(6, 277),
(6, 278),
(3, 297),
(3, 300),
(3, 298);

-- --------------------------------------------------------

--
-- 表的结构 `lt_admin`
--

CREATE TABLE IF NOT EXISTS `lt_admin` (
  `id` int(10) NOT NULL auto_increment,
  `userName` varchar(50) default NULL,
  `realName` varchar(30) default NULL COMMENT '真实姓名',
  `password` varchar(250) default NULL,
  `addTime` datetime default NULL,
  `lastTime` datetime default NULL,
  `status` tinyint(1) unsigned default '1',
  `roleId` int(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=20 ;

--
-- 转存表中的数据 `lt_admin`
--

INSERT INTO `lt_admin` (`id`, `userName`, `realName`, `password`, `addTime`, `lastTime`, `status`, `roleId`) VALUES
(15, 'dmthlb', 'dmthlb实施', NULL, '2016-09-10 18:09:02', NULL, 1, 1),
(11, 'admin', '黄良宝', 'a66abb5684c45962d887564f08346e8d', '2016-07-26 10:28:42', NULL, 1, 1),
(18, 'huangliangbao', '黄良宝', 'e83b4e6a9712045bf2c45536796763ba', '2016-11-19 10:24:04', NULL, 1, 2);

-- --------------------------------------------------------

--
-- 表的结构 `lt_company`
--

CREATE TABLE IF NOT EXISTS `lt_company` (
  `id` int(11) NOT NULL auto_increment,
  `name` text COMMENT '属性名称',
  `positionId` varchar(255) default NULL COMMENT '模拟量点或者遥信点',
  `logo` varchar(255) default NULL COMMENT 'LOGO',
  `product` text COMMENT '产品',
  `content` text,
  `sort` mediumint(4) default '0' COMMENT '排序',
  `addtime` datetime default NULL,
  `status` int(2) default '1' COMMENT '状态',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=29 ;

--
-- 转存表中的数据 `lt_company`
--

INSERT INTO `lt_company` (`id`, `name`, `positionId`, `logo`, `product`, `content`, `sort`, `addtime`, `status`) VALUES
(26, '桂林力拓信息科技有限公司', '2', '/logo/20161201/1480560477824.png', 'VR、3D可视化应用软件、数据中心、虚拟仿真', '', 0, '2016-11-29 11:16:33', 1),
(21, '江苏徐工信息技术股份有限公司', '1', '/logo/20161201/1480560663759.png', '智能制造、物联网、 两化融合、电子商务、ERP、软件开发、系统集成', '', 0, '2016-11-29 10:12:35', 1),
(22, '无锡埃姆维工业控制设备有限公司', '2', '/logo/20161201/1480560671448.png', '机器视觉、机电工程、打码、智能安防、Rfid、工业机器人、AGV小车', '', 0, '2016-11-29 10:12:45', 1),
(23, '上海蓝鸟机电有限公司', '3', '/logo/20161201/1480560386500.png', '机电EPC工程、信息自动化、智造信息化、环保新能源、3D打印', '', 0, '2016-11-29 10:12:54', 1),
(27, 'dsfsa发生地方大厦', '1', '/logo/20161201/1480560482838.png', '的撒范德萨玩儿去无人v', '', 0, '2016-11-30 10:17:25', 1);

-- --------------------------------------------------------

--
-- 表的结构 `lt_nav`
--

CREATE TABLE IF NOT EXISTS `lt_nav` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `alias` varchar(50) default NULL,
  `url` varchar(255) default NULL,
  `sort_order` smallint(4) default '0',
  `system` tinyint(1) default '0' COMMENT '1-系统 0-自定义',
  `type` tinyint(1) default '1' COMMENT '导航位置1-顶部 2-底部',
  `in_site` tinyint(1) default NULL COMMENT '1-本站内 0-站外',
  `is_show` tinyint(1) default '1',
  `open_type` tinyint(1) default '0',
  `seo_title` varchar(255) default NULL,
  `seo_keys` text,
  `seo_desc` text,
  `items_cate_id` int(11) default NULL COMMENT '//分类id',
  PRIMARY KEY  (`id`),
  KEY `is_show` (`is_show`),
  KEY `type` (`type`),
  KEY `sort_order` (`sort_order`),
  KEY `alias` (`alias`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `lt_node`
--

CREATE TABLE IF NOT EXISTS `lt_node` (
  `id` int(11) unsigned NOT NULL auto_increment,
  `module` varchar(255) default NULL,
  `moduleName` varchar(50) default NULL,
  `action` varchar(255) default NULL,
  `actionName` varchar(50) default NULL,
  `parentId` int(11) default '0',
  `data` varchar(255) default NULL,
  `status` int(1) default '0',
  `remark` varchar(255) default NULL,
  `sort` int(4) unsigned default '0',
  `authType` int(1) default '0',
  `groupId` int(3) unsigned default '0',
  `often` int(1) default '0' COMMENT '0-不常用 1-常用',
  `isShow` int(1) default '0' COMMENT '0-不常用 1-常用',
  PRIMARY KEY  (`id`),
  KEY `status` (`status`),
  KEY `module` (`module`),
  KEY `auth_type` (`authType`),
  KEY `is_show` (`isShow`),
  KEY `group_id` (`groupId`),
  KEY `sort` (`sort`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=47 ;

--
-- 转存表中的数据 `lt_node`
--

INSERT INTO `lt_node` (`id`, `module`, `moduleName`, `action`, `actionName`, `parentId`, `data`, `status`, `remark`, `sort`, `authType`, `groupId`, `often`, `isShow`) VALUES
(1, '', '首页', '', '', 0, 'fa-home', 0, '', 0, 0, NULL, NULL, NULL),
(6, 'baseDataManage', '导航数据管理', '', '', 0, 'fa-file-text', 1, '', NULL, 0, NULL, NULL, NULL),
(7, 'exitSign', '退出登录', '', '', 0, 'fa-sign-in', 0, '', NULL, 0, NULL, NULL, NULL),
(8, 'SystemSet', '系统设置', '/system/sysSet', '系统设置', 14, '', 1, '', 100, 1, NULL, NULL, NULL),
(9, '', '菜单管理', '/node/index', '', 14, '', 1, '', 90, 1, NULL, NULL, NULL),
(10, 'roleManage', '角色管理', '/role/index', '角色管理', 14, '', 1, '', 0, 1, NULL, NULL, NULL),
(11, 'authManage', '权限管理', '/system/sysAuth', '权限管理', 14, '', 1, '', 0, 1, NULL, NULL, NULL),
(12, 'adminList', '管理员列表', '/admin/index', '管理员列表', 2, '', 1, '', 0, 1, NULL, NULL, NULL),
(14, 'sysManage', '系统管理', '', '', 0, 'fa-cogs', 1, '', 100, 0, NULL, NULL, NULL),
(22, 'basicDataManage', '位置节点信息管理', '/dataBaseManage/company', '', 6, '', 1, '', 0, 1, NULL, NULL, NULL),
(27, 'Add_admin', '增加管理员', '/admin/add', '', 12, '', 1, '增加管理员', 0, 2, NULL, NULL, NULL),
(28, 'modify admin', '修改管理员', '/admin/modify', '修改管理员', 0, '', 1, '', 0, 2, NULL, NULL, NULL),
(29, 'deleteAdmin', '删除管理员', '/admin/delete', '', 12, '', 0, '', 0, 0, NULL, NULL, NULL),
(30, 'deleteAdmin', '删除管理员', '/admin/delete', '', 12, '', 1, '删除管理员', 0, 2, NULL, NULL, NULL),
(43, 'menu_add', '添加菜单', '/node/preInsert', '', 9, '', 1, '添加节点', 0, 2, NULL, NULL, NULL),
(42, 'dataAdd', '添加属性基础数据', '/data/add', '', 22, '', 1, '添加属性基础数据', 0, 2, NULL, NULL, NULL),
(44, 'update', '更新菜单', '/node/update', '', 9, '', 1, '编辑菜单', 0, 2, NULL, NULL, NULL),
(45, 'menu_delete', '删除菜单', '/node/delete', '', 9, '', 1, '删除菜单', 0, 2, NULL, NULL, NULL),
(2, 'adminManage', '管理员管理', NULL, NULL, 0, 'fa-user', 1, NULL, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- 表的结构 `lt_role`
--

CREATE TABLE IF NOT EXISTS `lt_role` (
  `id` smallint(6) unsigned NOT NULL auto_increment,
  `name` varchar(20) NOT NULL,
  `status` tinyint(1) unsigned default NULL,
  `remark` varchar(255) default NULL,
  `createTime` datetime default NULL,
  `updateTime` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `status` (`status`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- 转存表中的数据 `lt_role`
--

INSERT INTO `lt_role` (`id`, `name`, `status`, `remark`, `createTime`, `updateTime`) VALUES
(1, '超级管理员', 1, '超级管理员超级管理员', '2016-07-26 10:27:40', '2016-11-18 20:53:00'),
(2, '普通管理员', 1, '普通管理员普通管理员', '2016-07-26 10:28:25', '2016-07-26 10:28:25'),
(3, '内容管理员', 1, '', '2016-09-10 15:59:28', '2016-09-10 15:59:28');

-- --------------------------------------------------------

--
-- 表的结构 `lt_role_url`
--

CREATE TABLE IF NOT EXISTS `lt_role_url` (
  `id` smallint(6) unsigned NOT NULL auto_increment,
  `roleId` int(11) default NULL,
  `name` varchar(20) NOT NULL,
  `url` varchar(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `lt_role_user`
--

CREATE TABLE IF NOT EXISTS `lt_role_user` (
  `id` smallint(6) unsigned NOT NULL auto_increment,
  `roleId` int(11) default NULL,
  `userId` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `lt_setting`
--

CREATE TABLE IF NOT EXISTS `lt_setting` (
  `name` varchar(100) NOT NULL,
  `data` text NOT NULL,
  KEY `name` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `lt_setting`
--

INSERT INTO `lt_setting` (`name`, `data`) VALUES
('systemName', '3D导航管理系统'),
('systemTitle', '3D导航管理系统'),
('systemKeyword', '数据中心可视化|3D应用'),
('systemRemark', '3D导航管理系统，为您服务，你懂的!'),
('systemStatus', '1'),
('systemIcp', '桂ICP备13003912号'),
('systeDomain', 'huangliangbao.com'),
('logo', '/logo/1480560648063.png');

-- --------------------------------------------------------

--
-- 表的结构 `lt_sysauthoritylist`
--

CREATE TABLE IF NOT EXISTS `lt_sysauthoritylist` (
  `ID` int(11) NOT NULL auto_increment,
  `UserID` int(11) default NULL,
  `RoleID` int(11) default NULL,
  `GroupID` int(11) default NULL,
  `ModuleID` int(11) default NULL,
  `CreateTime` int(11) default NULL,
  `Flag` int(1) default '0' COMMENT '暂时没有定义',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=266 ;

--
-- 转存表中的数据 `lt_sysauthoritylist`
--

INSERT INTO `lt_sysauthoritylist` (`ID`, `UserID`, `RoleID`, `GroupID`, `ModuleID`, `CreateTime`, `Flag`) VALUES
(229, NULL, 1, 1, 3, 1472086181, 1),
(24, NULL, 1, 1, 8, 1374584282, 1),
(135, NULL, 1, 1, 9, 1387006068, 1),
(244, 0, 0, 1, 10, 1472541347, 1),
(48, NULL, 1, 1, 11, 1374592192, 1),
(243, 0, 0, 1, 6, 1472541262, 1),
(263, 0, 0, 1, 27, 1479474373, 1),
(262, 0, 0, 1, 30, 1479474373, 1),
(264, 0, 0, 1, 28, 1479474373, 1),
(212, NULL, 1, 1, 26, 1471835541, 1),
(242, 0, 0, 1, 4, 1472541166, 1),
(225, NULL, 1, 1, 21, 1471915373, 1),
(245, 0, 0, 1, 24, 1472542133, 1),
(211, NULL, 1, 1, 25, 1471835541, 1),
(261, 0, 0, 1, 2, 1479474373, 1),
(49, NULL, 1, 1, 37, 1374592192, 1),
(50, NULL, 1, 1, 38, 1374592192, 1),
(265, 0, 0, 1, 12, 1479474373, 1),
(246, 0, 0, 1, 23, 1472542700, 1),
(54, NULL, 1, 1, 40, 1374592192, 1),
(55, NULL, 1, 1, 41, 1374592192, 1),
(236, 0, 1, 1, 1, 1472524618, 1),
(232, NULL, 1, 1, 43, 1472089227, 1),
(205, NULL, 1, 1, 5, 1471827571, 1),
(204, NULL, 1, 1, 14, 1471685505, 1),
(233, NULL, 1, 1, 44, 1472089333, 1),
(234, NULL, 1, 1, 45, 1472089599, 1),
(235, NULL, 1, 1, 46, 1472090443, 1),
(247, 0, 0, 1, 20, 1473389592, 1),
(248, 0, 0, 1, 19, 1473389592, 1),
(249, 0, 0, 1, 39, 1473389592, 1),
(250, 0, 0, 1, 15, 1473406739, 1),
(251, 0, 0, 1, 42, 1473406799, 1),
(252, 0, 0, 1, 31, 1473406799, 1),
(253, 0, 0, 1, 35, 1473406799, 1),
(254, 0, 0, 1, 22, 1473406799, 1),
(255, 0, 0, 1, 17, 1473406799, 1),
(256, 0, 0, 1, 36, 1473406799, 1),
(257, 0, 0, 1, 18, 1473406799, 1),
(258, 0, 0, 1, 33, 1473406799, 1),
(259, 0, 0, 1, 34, 1473406799, 1),
(260, 0, 0, 1, 16, 1473406799, 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
