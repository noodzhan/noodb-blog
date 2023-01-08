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
java_jeecgboot_start=$(ps aux|grep java|grep jeecg|wc -l)
if test $java_jeecgboot_start -ge 1
then
	echo 'jeecgboot is running'
else 
	echo 'jeecgboot java  process is stop'
	echo 'try startup jeecgboot'
	cd /home/ubuntu/jeecgboot/
	nohup java -jar -Xmx512m jeecg-system-start-3.4.4.jar > catalina.log 2>&1 &
fi

# 判断docker程序数据库是否启动
mysql_start=$(sudo docker ps |grep noodb-mysql |wc -l)
if test $mysql_start -ge 1
then 
	echo 'mysql container is running'
else 	
	sudo docker ps 
	echo 'mysql is stop'
	echo 'try startup docker container mysql'
	mysql_container_id=$(sudo docker ps -a |grep noodb-mysql |awk '{print $1}')
	sudo docker start "$mysql_container_id"

fi
# 判断docker程序redis是否启动
redis_start=$(sudo docker ps |grep redis |wc -l)
if test $redis_start -ge 1
then 
	echo 'redis container is running'
else 	
	sudo docker ps 
	echo 'redis is stop'
	echo 'try startup docker container redis'
	redis_container_id=$(sudo docker ps -a |grep redis |awk '{print $1}')
	sudo docker start "$redis_container_id"

fi

# 判断docker calibre是否启动
calibre_start=$(sudo docker ps |grep calibre|wc -l)
if test $calibre_start -ge 1
then
	echo 'calibre_web is running'
else
	echo 'calibre_web is stop'
	echo 'try start calibre'
	calibre_container_id=$(docker ps -a |grep calibre |awk '{print $1}')
	sudo docker start "$calibre_container_id"
	
fi

