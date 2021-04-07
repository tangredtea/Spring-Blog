[![license](https://badgen.net/github/license//laowenruo/Spring-Blog?color=green)](https://github.com//laowenruo/Spring-Blog/main/LICENSE)
[![stars](https://badgen.net/github/stars//laowenruo/Spring-Blog)](https://github.com//laowenruo/Spring-Blog/stargazers)
[![contributors](https://badgen.net/github/contributors/laowenruo/Spring-Blog)](https://github.com/laowenruo/Spring-Blog/graphs/contributors)
[![help-wanted](https://badgen.net/github/label-issues//laowenruo/Spring-Blog/help%20wanted/open)](https://github.com/laowenruo/Spring-Blog/labels/help%20wanted)
[![issues](https://badgen.net/github/open-issues//laowenruo/Spring-Blog)](https://github.com/laowenruo/Spring-Blog/issues)
[![PRs Welcome](https://badgen.net/badge/PRs/welcome/green)](http://makeapullrequest.com)
# Spring-Blog
框架：Springboot

数据库持久层：Mybatis

文章评论插件：Valine

分页插件：PageHelper

后台UI框架：X-admin框架，即LayUI框架

数据库连接池：hikari

数据库：MySQL

日志：Log4J

后台配置:properties

缓存实现:Redis+Spring-cache

------
运行截图
------
首页
![首页](https://t1.picb.cc/uploads/2021/03/24/Zj2xyL.jpg)
------
首页下拉
![首页下拉](https://t1.picb.cc/uploads/2021/03/24/Zj2F5v.png)
------
后台管理
![后台管理](https://t1.picb.cc/uploads/2021/03/24/Zj2PXi.png)
------

除了上述选择外实现的简单优化

✅theamleaf模板渲染缓存

✅网站请求实现Gzip压缩，减小网页体积

修复

✅Controller修复：修复Controller层存在的小问题，修复不带参数访问问题

✅页面修复：时间轴页面、400以及500页面、页脚计时、删除添加提示框、整合页面、文章访问量修复

✅页面开发：编写关于我、友情链接、留言墙页面、友情链接后台管理

✅项目优化：精简了部分无用代码，部分无需引用的css和js资源，减小项目体积，删减了评论模块

✅插件引入：引入了一言，采用随机语录，引用了Valine评论插件

✅SEO优化：底部链接采用no-follow标签，meta标签优化

✅后台设置：将前端相关信息（文字、图片等）设置到properties，降低网页间的耦合度

✅缓存：redis初步缓存加速，结合了SpringCache缓存注解（2021.04.07）

开发者可以选择优化部分（提下建议）

✅你可以采用CDN加速网页静态资源

✅你可以将配置文件里的图床换成更好的，以及一些CDN的js/css

✅你可以采用Nginx实现动静分离

待优化

🔲页面的部分请求采用ajax刷新数据

🔲将数据库中一些频繁增删改字段分离，从而结合Redis实现更好的缓存效果

时间充裕后实现功能：

🔲学习VUE，并且开发一个VUE版本

🔲shiro框架实现博客后台多用户多权限，但不打算提供前台用户注册
