/*
MySQL Data Transfer
Source Host: localhost
Source Database: mhw_db
Target Host: localhost
Target Database: mhw_db
Date: 2015/3/2 0:35:19
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_message
-- ----------------------------
CREATE TABLE `t_message` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键',
  `chuanzhen` varchar(255) default NULL COMMENT '传真',
  `dianhua` varchar(255) default NULL COMMENT '电话',
  `dizhi` varchar(255) default NULL COMMENT '地址',
  `neirong` text COMMENT '留言内容',
  `shijian` varchar(255) default NULL COMMENT '时间',
  `xingming` varchar(255) default NULL COMMENT '姓名',
  `youbian` varchar(255) default NULL COMMENT '邮编',
  `youjianl` varchar(255) default NULL COMMENT '邮件',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_pic
-- ----------------------------
CREATE TABLE `t_pic` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键',
  `info` varchar(255) default NULL COMMENT '图片说明',
  `path` varchar(255) default NULL COMMENT '图片路径',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
CREATE TABLE `t_product` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键',
  `createtime` varchar(255) default NULL COMMENT '添加时间',
  `jieshao` varchar(255) default NULL COMMENT '介绍',
  `path` varchar(255) default NULL COMMENT '产品图片路径',
  `pname` varchar(255) default NULL COMMENT '产品名',
  `tuijian` varchar(255) default NULL COMMENT '是否推荐',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_qiye
-- ----------------------------
CREATE TABLE `t_qiye` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键',
  `chuanzhen` varchar(255) default NULL COMMENT '传真',
  `dianhua` varchar(255) default NULL COMMENT '电话',
  `dizhi` varchar(255) default NULL COMMENT '地址',
  `jianjie` text COMMENT '企业介绍',
  `lianxiren` varchar(255) default NULL COMMENT '联系人',
  `logo` varchar(255) default NULL COMMENT '企业logo',
  `mingchen` varchar(255) default NULL COMMENT '企业名称',
  `shouji` varchar(255) default NULL COMMENT '手机',
  `youbian` varchar(255) default NULL COMMENT '邮编',
  `youxiang` varchar(255) default NULL COMMENT '邮箱',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键',
  `password` varchar(255) default NULL COMMENT '密码',
  `role` int(11) NOT NULL default '0' COMMENT '用户角色',
  `username` varchar(255) default NULL COMMENT '用户名‘',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_xinwen
-- ----------------------------
CREATE TABLE `t_xinwen` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键',
  `biaoti` varchar(255) default NULL COMMENT '标题',
  `createtime` varchar(255) default NULL COMMENT '添加时间',
  `neirong` text COMMENT '内容',
  `tuijian` varchar(255) default NULL COMMENT '是否推荐',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_zhaopin
-- ----------------------------
CREATE TABLE `t_zhaopin` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键',
  `createtime` varchar(255) default NULL COMMENT '添加时间',
  `jieshao` text COMMENT '介绍',
  `tuijian` varchar(255) default NULL COMMENT '推荐',
  `zhiwei` varchar(255) default NULL COMMENT '职位名称',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `t_message` VALUES ('1', '11345', '10010', '乌干达坎帕拉', '张三丰留言了', '2015-03-02 00:31:47', '张三丰', '112599', '123456@qq.com');
INSERT INTO `t_pic` VALUES ('1', '展示图片一', 'banner01.jpg');
INSERT INTO `t_pic` VALUES ('2', '展示图片二', '20150301213115.jpg');
INSERT INTO `t_pic` VALUES ('3', '展示图片三', 'banner03.jpg');
INSERT INTO `t_product` VALUES ('2', '2015-03-01 21:48:42', '产品2介绍产品2介绍\r\n产品2介绍\r\n产品2介绍\r\n产品2介绍\r\n产品2介绍\r\n产品2介绍\r\n产品2介绍\r\n产品2介绍\r\n\r\n', '20150301214842.jpg', '产品2', '未推荐');
INSERT INTO `t_product` VALUES ('3', '2015-03-01 21:51:10', '产品3介绍产品3介绍产品3介绍产品3介绍\r\n产品3介绍产品3介绍\r\n产品3介绍产品3介绍\r\n产品3介绍产品3介绍\r\n产品3介绍产品3介绍\r\n产品3介绍产品3介绍\r\n产品3介绍产品3介绍\r\n\r\n\r\n', '20150301215110.jpg', '产品3', '推荐');
INSERT INTO `t_product` VALUES ('4', '2015-03-01 21:53:52', '产品4介绍产品4介绍产品4介绍产品4介绍产品4介绍产品4介绍产品4介绍产品4介绍产品4介绍产品4介绍产品4介绍产品4介绍产品4介绍\r\n产品4介绍产品4介绍产品4介绍产品4介绍产品4介绍产品4介绍产品4介绍产品4介绍产品4介绍产品4介绍', '20150301215352.jpg', '产品4', '推荐');
INSERT INTO `t_product` VALUES ('5', '2015-03-01 21:54:18', '产品5介绍', '20150301215418.jpg', '产品5', '推荐');
INSERT INTO `t_qiye` VALUES ('1', '119978', '10010', '中央大街', '公司以“专注网站，用心服务”为核心价值，一切以用户需求为中心，希望通过专业水平和不懈努力，重塑企业网络形象，为企业产品推广文化发展提供服务指导；', '王经理', 'logo.jpg', '企业门户网站', '13988889999', '114599', '123456@qq.com');
INSERT INTO `t_user` VALUES ('1', 'admin', '1', 'admin');
INSERT INTO `t_xinwen` VALUES ('1', '新闻动态', '2015-03-01 21:39:28', '最新公告新闻信息！最新公告新闻信息！最新公告新闻信息！最新公告新闻信息！最新公告新闻信息！最新公告新闻信息！最新公告新闻信息！最新公告新闻信息！<IMG border=0 src=\"/mhw/uploadfile/IMAGE/20150301/301243414a4b98ba2429a313f4891c72.jpg\">', '未推荐');
INSERT INTO `t_xinwen` VALUES ('2', '公司最新动态', '2015-03-01 21:45:29', '提供网站策划服务，优质、用心的服务赢得了众多企业的信赖和好评，在地区逐渐树立起公司良好品牌。公司不仅仅提供专业的网站策划服务，同时还建立了完善的<A href=\"http://www.baidu.com/s?wd=%E5%94%AE%E5%90%8E%E6%9C%8D%E5%8A%A1%E4%BD%93%E7%B3%BB&amp;hl_tag=textlink&amp;tn=SE_hldp01350_v6v6zkg6\" rel=nofollow target=_blank jQuery11020059440168365040846=\"184\" data-word=\"2\" log=\"pos:innerLink\" decor-none?>售后服务体系</A>，为<A href=\"http://www.baidu.com/s?wd=%E4%BC%81%E4%B8%9A%E5%8F%91%E5%B1%95&amp;hl_tag=textlink&amp;tn=SE_hldp01350_v6v6zkg6\" rel=nofollow target=_blank jQuery11020059440168365040846=\"185\" data-word=\"13\" log=\"pos:innerLink\" decor-none?>企业发展</A>中遇到的问题和困难提供指导帮助。我们相信，通过我们的不断努力和追求，一定能够实现与中小企业的互利<A href=\"http://www.baidu.com/s?wd=%E5%85%B1%E8%B5%A2&amp;hl_tag=textlink&amp;tn=SE_hldp01350_v6v6zkg6\" rel=nofollow target=_blank jQuery11020059440168365040846=\"186\" data-word=\"18\" log=\"pos:innerLink\" decor-none?>共赢</A>！', '未推荐');
INSERT INTO `t_zhaopin` VALUES ('1', '2015-03-01 21:56:27', '招聘软件工程师若干名，待遇优厚', '未推荐', '软件工程师');
INSERT INTO `t_zhaopin` VALUES ('2', '2015-03-01 21:57:14', '招聘测试师', '未推荐', '测试师');
