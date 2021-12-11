# noodb.com

## 设计初衷

开发该个人博客网站，主要是为了记录工作或学习中的，一些个人笔记，便于以后出现问题可以快速定位；设计初衷就是为了类似wiki一样，留下一些个人学习记录。以后有时间，查询改为全文检索。

## 总体预览

![img.png](REAEME_preview_home.png)

![img_1.png](README_preview_edit.png)

![img_2.png](README_preview_login.png)

### 技术实现

    springboot 2.4.5
    MySQL 8.0
    MybatisPlus 3.4.3
    gradle 7.1.1
    vue 2.6.11
    antd 1.7.7
    marked 2.0.7 
    mavon-editor 2.9.1

### 更新日志

#### 1.0.0

1. 【新增】博客首页，分页渲染博客列表
2. 【新增】查看博客详情
3. 【新增】临时推荐文档，书籍；捐赠支持功能
4. 【新增】 支持博客编辑，必须是登录后才能修改

### 1.0.1 （版本计划）

1. 【优化】登录逻辑，完善博客编辑功能
2. 【优化】博客目录树
3. 【新增】全文搜索
4. 【新增】发布博客
5. 【优化】首页展示推荐书籍和文档样式

### 1.0.2 （版本计划）

1. 【新增】博客支持上传图片
2. 【新增】推荐文档和书籍改为动态方式

### 项目启动

前端 （front-end）

```
npm install

npm run serve
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
