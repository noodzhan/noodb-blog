CREATE USER jira IDENTIFIED BY '123456';
CREATE DATABASE jira CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,REFERENCES,ALTER,INDEX on jira.* TO 'jira'@'%';
flush privileges;