# noodb

## Project setup

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

### 部署

前端

```shell
scp -i ~/.ssh/id_rsa -r /Users/noodzhan/IdeaProjects/noodb/front-end/dist ubuntu@1.15.231.74:/home/ubuntu/nblog/front-end
```

后台

```shell

scp -i ~/.ssh/id_rsa /Users/noodzhan/IdeaProjects/noodb/back-end/build/libs/back-end-1.0.0.jar ubuntu@1.15.231.74:/home/ubuntu/nblog/noodb-blog-jar

```
