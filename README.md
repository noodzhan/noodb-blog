# noodb.com

## 设计初衷
      开发该个人博客网站，主要是为了记录工作或学习中的，一些个人笔记，便于以后出现问题可以快速定位；设计初衷就是为了类似wiki一样，留下一些个人学习记录。以后有时间，查询改为全文检索。

### 技术实现

    springboot 2.4.5
    MySQL 8.0
    MybatisPlus 3.4.3
    gradle 7.1.1
    vue 2.6.11
    antd 1.7.7
    marked 2.0.7 
    mavon-editor 2.9.1
    

### 项目启动

前端 （front-end）
```
npm install
```

### Compiles and hot-reloads for development

```
npm run serve
```

### Compiles and minifies for production

```
npm run build
```

### Lints and fixes files

```
npm run lint
```
后台 （back-end）

开发启动
```shell
java -jar -Dspring.profiles.active=dev back-end.jar 
```
### 部署

前端

```shell
scp -i ~/.ssh/id_rsa -r /Users/noodzhan/IdeaProjects/noodb/front-end/dist ubuntu@1.15.231.74:/home/ubuntu/nblog/front-end
```

后台

```shell

scp -i ~/.ssh/id_rsa /Users/noodzhan/IdeaProjects/noodb/back-end/build/libs/back-end-1.0.0.jar ubuntu@1.15.231.74:/home/ubuntu/nblog/noodb-blog-jar

```
