[![AUR](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg)](https://github.com/laowenruo/Spring-Blog/master/LICENSE)
[![stars](https://badgen.net/github/stars//laowenruo/Spring-Blog)](https://github.com//laowenruo/Spring-Blog/stargazers)
[![contributors](https://badgen.net/github/contributors/laowenruo/Spring-Blog)](https://github.com/laowenruo/Spring-Blog/graphs/contributors)
[![help-wanted](https://badgen.net/github/label-issues/laowenruo/Spring-Blog/help%20wanted/open)](https://github.com/laowenruo/Spring-Blog/labels/help%20wanted)
[![issues](https://badgen.net/github/open-issues/laowenruo/Spring-Blog)](https://github.com/laowenruo/Spring-Blog/issues)
[![PRs Welcome](https://badgen.net/badge/PRs/welcome/green)](http://makeapullrequest.com)

# SpringBoot-Blog

🤷‍♂️框架：Springboot

🤷‍♂️数据库持久层：Mybatis

🤷‍♂️文章评论插件：Valine

🤷‍♂️分页插件：PageHelper

🤷‍♂️数据库连接池：hikari

🤷‍♂️数据库：MySQL

🤷‍♂️日志：Log4J

🤷‍♂️后台配置: properties

🤷‍♂️缓存实现: Redis

初始账号密码：admin  123456

------

🙈求大佬们给公众号点个关注，不定期分享后端、Java及中间件等技术、面试干货🙈

<img src="https://isbut-blog.oss-cn-shenzhen.aliyuncs.com/markdown-img/image-20220205231316796.png" alt="image-20220205231316796" style="zoom: 33%;" />

------

运行截图
------

## 前台
>借鉴了其他人的前端进行开发。

![image-20220205225152899](https://isbut-blog.oss-cn-shenzhen.aliyuncs.com/markdown-img/image-20220205225152899.png)

![image-20220205225212091](https://isbut-blog.oss-cn-shenzhen.aliyuncs.com/markdown-img/image-20220205225212091.png)

![image-20220205225238343](https://isbut-blog.oss-cn-shenzhen.aliyuncs.com/markdown-img/image-20220205225238343.png)

![image-20220205225941124](https://isbut-blog.oss-cn-shenzhen.aliyuncs.com/markdown-img/image-20220205225941124.png)

## 后台

![image-20220205230014300](https://isbut-blog.oss-cn-shenzhen.aliyuncs.com/markdown-img/image-20220205230014300.png)

![image-20220205230241937](https://isbut-blog.oss-cn-shenzhen.aliyuncs.com/markdown-img/image-20220205230241937.png)

![image-20220205230311762](https://isbut-blog.oss-cn-shenzhen.aliyuncs.com/markdown-img/image-20220205230311762.png)

![image-20220205230346784](https://isbut-blog.oss-cn-shenzhen.aliyuncs.com/markdown-img/image-20220205230346784.png)

![image-20220205230415709](https://isbut-blog.oss-cn-shenzhen.aliyuncs.com/markdown-img/image-20220205230415709.png)

## docker方式

> 配置完 MySQL 、Redis 等后即可打包镜像运行，具体调优参数可自行修改

```
docker build -t spring-blog:1.0.0 .
docker run -d -p 8080:8080 -v /logs:/logs --name spring-blog spring-blog:1.0.0
```
## Nginx

> 按照nginx.conf配置即可
> 同时也推荐大家了解下 https://github.com/avwo/whistle 这个反向代理，挺好用的

## 更新日志

### 2026-02-27
🤖 **AI 辅助维护启动**
- 配置自动化工作流，支持智能代码审查
- 集成 OpenClaw Agent 进行日常维护
- 优化 GitHub Issue 响应流程

### 历史更新
✅修复了前后端所存在的诸多bug，且更换后端管理界面UI

✅将数据库字段进行重构，采用BIT表示状态位，精简其他数据库字段

✅仅用Redis缓存博文和浏览量，设置定时任务刷新浏览量到数据库

✅用properties实现网站常规设置，用反射来进行更新配置文件

✅精简了无需的js、css和其他前后端代码

✅添加webhook告警、更改日志级别、规范pojo、添加首页缓存、添加Dockerfile、Nginx配置文件(2022.02.22)
## Sponsor
Thanks to JetBrains for the support

![image](https://user-images.githubusercontent.com/47266759/179214465-dc16a9a2-ebde-4998-b154-ea4943574710.png)
