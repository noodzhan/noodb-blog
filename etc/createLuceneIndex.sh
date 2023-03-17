curl -X POST  http://localhost:8095/api/article/createIndex

curl -X POST -H "Accept: application/json" -H "Content-type: application/json" -d "{keyword:\"mybatis\"}" http://localhost:8095/api/article/search