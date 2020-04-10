# quartz页面管理
本项目主要使用spring boot集成quartz定时任务,实现简单的定时任务的管理,包括:
1. 任务job类扫描功能
2. 新增定时任务
3. 删除定时任务
4. 暂停定时任务
5. 恢复定时任务
6. 修改定时任务启动时间
7. 定时任务半持久化操作
8. 定时任务列表查询

主要表sql
```sql
CREATE TABLE `job_trigger` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(64) DEFAULT NULL,
  `job_group` varchar(64) DEFAULT NULL,
  `job_class` varchar(64) DEFAULT NULL,
  `trigger_name` varchar(255) DEFAULT NULL,
  `trigger_Group` varchar(255) DEFAULT NULL,
  `cron_expression` varchar(255) DEFAULT NULL,
  `trigger_state` varchar(10) DEFAULT NULL,
  `ctime` bigint(13) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_uni` (`job_name`,`job_group`,`description`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
```

