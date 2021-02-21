--DROP TABLE IF EXISTS isa;

--DROP TABLE data_source;
CREATE TABLE IF NOT EXISTS data_source
(
	id BIGINT(20) NOT NULL auto_increment COMMENT '主键ID',
	name VARCHAR(60) NULL DEFAULT NULL COMMENT '名称',
	database_type VARCHAR(20) NULL DEFAULT NULL COMMENT '数据库类型',
	host VARCHAR(20) NULL DEFAULT NULL COMMENT '地址',
	port VARCHAR(10) NULL DEFAULT NULL COMMENT '端口',
	database VARCHAR(20) NULL DEFAULT NULL COMMENT '数据库',
	user VARCHAR(60) NULL DEFAULT NULL COMMENT '用户',
	password VARCHAR(20) NULL DEFAULT NULL COMMENT '密码',
	PRIMARY KEY (id)
);

--DROP TABLE issue;
CREATE TABLE IF NOT EXISTS issue
(
id BIGINT(20) NOT NULL auto_increment COMMENT '主键ID',
name VARCHAR(60) NULL DEFAULT NULL COMMENT '名称',
PRIMARY KEY (id)
);

--DROP TABLE analysis_process;
CREATE TABLE IF NOT EXISTS analysis_process
(
id BIGINT(20) NOT NULL auto_increment COMMENT '主键ID',
name VARCHAR(60) NULL DEFAULT NULL COMMENT '名称',
type VARCHAR(10) NULL DEFAULT NULL COMMENT '类型',
content VARCHAR(4096) NULL DEFAULT NULL COMMENT '内容',
in_params VARCHAR(60) NULL DEFAULT NULL COMMENT '入参',
out_params VARCHAR(60) NULL DEFAULT NULL COMMENT '出参',
describe VARCHAR(1024) NULL DEFAULT '' COMMENT '说明',
PRIMARY KEY (id)
);

--DROP TABLE analysis_flow;
CREATE TABLE IF NOT EXISTS analysis_flow
(
id BIGINT(20) NOT NULL auto_increment COMMENT '主键ID',
name VARCHAR(60) NULL DEFAULT NULL COMMENT '名称',
object BIGINT(20) NULL DEFAULT NULL COMMENT '对象ID',
first_sequence BIGINT(20) NULL DEFAULT NULL COMMENT '首个节点,',
PRIMARY KEY (id)
);

--DROP TABLE analysis_sequence;
CREATE TABLE IF NOT EXISTS analysis_sequence
(
id BIGINT(20) NOT NULL auto_increment COMMENT '主键ID',
analysis_flow BIGINT(20) NOT NULL COMMENT '分析流',
pre_node BIGINT(20) NULL DEFAULT NULL COMMENT '上一个节点',
next_node BIGINT(20) NULL DEFAULT NULL COMMENT '下一个节点',
PRIMARY KEY (id)
);
