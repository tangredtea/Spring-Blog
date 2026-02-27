# SpringBoot Blog 🚀

[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE)
[![Stars](https://img.shields.io/github/stars/tangredtea/Spring-Blog?style=social)](https://github.com/tangredtea/Spring-Blog/stargazers)
[![Issues](https://img.shields.io/github/issues/tangredtea/Spring-Blog)](https://github.com/tangredtea/Spring-Blog/issues)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](http://makeapullrequest.com)

> 基于 Spring Boot + MyBatis 的个人博客系统，持续维护中。
> 
> 适合作为 **期末项目 / 毕业设计 / 学习参考**

## ✨ 特性

- 📝 Markdown 文章编辑与预览
- 🏷️ 文章分类与标签管理
- 💬 评论系统（Valine）
- 🔍 全文搜索功能
- 📊 后台数据统计
- 🎨 响应式前端界面
- ⚡ Redis 缓存加速
- 🔒 安全加固（BCrypt 加密、输入验证）

## 🛠️ 技术栈

| 层级 | 技术 |
|------|------|
| 框架 | Spring Boot 2.7.x |
| ORM | MyBatis |
| 数据库 | MySQL 8.0 |
| 缓存 | Redis |
| 模板引擎 | Thymeleaf |
| 分页 | PageHelper |
| 密码加密 | BCrypt |
| 连接池 | HikariCP |

## 🚀 快速开始

### 环境要求
- JDK 1.8+
- MySQL 5.7+
- Redis 5.0+
- Maven 3.6+

### 安装步骤

1. **克隆项目**
```bash
git clone https://github.com/tangredtea/Spring-Blog.git
cd Spring-Blog
```

2. **创建数据库**
```bash
mysql -u root -p < blog.sql
```

3. **配置环境变量**（推荐）
```bash
export DB_USERNAME=root
export DB_PASSWORD=your_password
export REDIS_PASSWORD=your_redis_password  # 如果有
```

4. **运行项目**
```bash
mvn spring-boot:run
```

5. **访问应用**
- 前台：http://localhost:8080
- 后台：http://localhost:8080/admin
- 默认账号：`admin` / `123456`

### Docker 部署

```bash
# 构建镜像
docker build -t spring-blog:1.0.0 .

# 运行容器
docker run -d -p 8080:8080 \
  -e DB_USERNAME=root \
  -e DB_PASSWORD=password \
  --name spring-blog \
  spring-blog:1.0.0
```

## 📁 项目结构

```
Spring-Blog/
├── src/main/java/com/blog/
│   ├── controller/      # 控制器层
│   ├── service/         # 业务层
│   ├── dao/             # 数据访问层
│   ├── entity/          # 实体类
│   ├── config/          # 配置类
│   ├── util/            # 工具类
│   └── exception/       # 异常处理
├── src/main/resources/
│   ├── mapper/          # MyBatis XML
│   ├── templates/       # Thymeleaf 模板
│   ├── static/          # 静态资源
│   └── application*.yml # 配置文件
└── blog.sql             # 数据库脚本
```

## 🔐 安全特性

- ✅ BCrypt 密码加密
- ✅ SQL 注入防护
- ✅ XSS 攻击防护
- ✅ 输入参数校验
- ✅ 全局异常处理

## 📈 更新日志

### 2026-02-27 v1.1.0
- 🔒 升级 Spring Boot 至 2.7.18，修复安全漏洞
- 🔒 替换 MD5 为 BCrypt 密码加密
- 🔒 添加全局异常处理
- 🔧 优化配置文件安全性
- 📝 完善 README 文档

### 2022-02-22 v1.0.0
- ✨ 初始版本发布
- 📝 文章管理功能
- 🏷️ 分类标签功能
- 💬 评论系统
- 📊 后台统计

## 🤝 贡献指南

欢迎提交 Issue 和 Pull Request！

1. Fork 本仓库
2. 创建分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送分支 (`git push origin feature/AmazingFeature`)
5. 创建 Pull Request

## 📄 许可证

[Apache License 2.0](LICENSE) © tangredtea

---

⭐ 如果这个项目对你有帮助，请给个 Star 支持一下！
