#!/bin/sh

# 添加MySQL驱动
cp /drivers/mysql-connector-java-8.0.25.jar /opt/atlassian/jira/lib
cp /drivers/atlassian-agent.jar /opt/atlassian/jira/
RUN echo 'export CATALINA_OPTS="-javaagent:/opt/atlassian/jira/atlassian-agent.jar ${CATALINA_OPTS}"' >> /opt/atlassian/jira/bin/setenv.sh
# 设置权限，没必要，jar包默认root权限即可
# chown -R jira:jira /opt/atlassian/jira/lib/mysql-connector-java-8.0.25.jar
echo "copy mysql driver finished"

# start jira
/entrypoint.py
