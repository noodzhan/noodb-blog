#!/bin/sh
echo "guard application $(date)"
# 判断node是否启动
node_start=$(lsof -i:3000 |wc -l)
if test $node_start -ge 1
then
	echo 'node is running'
else 
	echo 'node is stop'
	echo 'try startup nuxt app'
	cd ../nuxtapp
	nohup npm run start &

fi
# 判断java程序是否启动
java_start=$(ps aux|grep java|grep back-end|wc -l)
if test $java_start -ge 1
then
	echo 'java is running'
else 
	echo 'java is stop'
	echo 'try startup back-end'
	cd /home/ubuntu/nblog/nood-blog-jar
	java -jar -Dspring.profiles.active=prod back-end-1.0.0.jar &
fi
# 判断docker程序数据库是否启动
mysql_start=$(docker ps |grep mysql |wc -l)
if test $mysql_start -ge 1
then 
	echo 'mysql container is running'
else 	
	docker ps 
	echo 'mysql is stop'
	echo 'try startup docker container mysql'
	mysql_container_id=$(docker ps -a |grep mysql |awk '{print $1}')
	docker start "$mysql_container_id"

fi
# 判断docker calibre是否启动
calibre_start=$(docker ps |grep calibre|wc -l)
if test $calibre_start -ge 1
then
	echo 'calibre_web is running'
else
	echo 'calibre_web is stop'
	echo 'try start calibre'
	calibre_container_id=$(docker ps -a |grep calibre |awk '{print $1}')
	docker start "$calibre_container_id"
	
fi

