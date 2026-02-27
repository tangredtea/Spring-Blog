# SpringBoot AI Blog 🤖✨

[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE)
[![Stars](https://img.shields.io/github/stars/tangredtea/Spring-Blog?style=social)](https://github.com/tangredtea/Spring-Blog/stargazers)
[![Issues](https://img.shields.io/github/issues/tangredtea/Spring-Blog)](https://github.com/tangredtea/Spring-Blog/issues)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](http://makeapullrequest.com)

> 🚀 基于 Spring Boot + MyBatis 的**智能化**个人博客系统
> 
> 集成 AI 助手、SEO 优化、暗黑模式等现代特性
>
> 适合作为 **期末项目 / 毕业设计 / 企业级博客**

---

## ✨ 核心特性

### 🤖 AI 智能功能（新）
- **智能摘要生成** - AI 自动生成文章摘要
- **标签推荐** - 根据内容智能推荐标签
- **文章质量评分** - AI 评估并给出改进建议
- **智能搜索** - 关键词提取和相关文章推荐

### 🔍 SEO/AEO 优化（新）
- **站点地图** - 自动生成 `sitemap.xml`
- **结构化数据** - Schema.org JSON-LD 标记
- **Open Graph** - 社交媒体分享优化
- **Twitter Card** - Twitter 分享卡片
- **Meta 标签** - 自动优化标题、描述、关键词

### 🎨 用户体验（新）
- **暗黑模式** - 一键切换，护眼舒适
- **响应式设计** - 完美适配移动端
- **主题记忆** - 记住用户偏好

### 📝 文章管理增强
- **草稿箱** - 保存未发布的文章
- **置顶功能** - 重要文章置顶显示
- **密码保护** - 私密文章访问控制
- **回收站** - 误删文章可恢复

### 💬 互动功能
- **评论系统** - Valine 无后端评论
- **留言板** - 访客留言互动
- **友链管理** - 友情链接展示

### ⚡ 性能优化
- **Redis 缓存** - 加速页面加载
- **数据库索引** - 优化查询性能

---

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

---

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
export AI_API_KEY=your_openai_api_key      # 可选，用于AI功能
```

4. **运行项目**
```bash
mvn spring-boot:run
```

5. **访问应用**
- 前台：http://localhost:8080
- 后台：http://localhost:8080/admin
- 默认账号：`admin` / `123456`

### Docker Compose 部署（推荐）

```bash
# 1. 复制配置
cp .env.example .env

# 2. 编辑配置
vim .env

# 3. 一键启动
docker-compose up -d
```

---

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
├── src/test/            # 单元测试
├── blog.sql             # 数据库脚本
├── Dockerfile           # Docker 构建
├── docker-compose.yml   # Docker Compose
└── .env.example         # 环境变量示例
```

---

## 🔐 安全特性

- ✅ BCrypt 密码加密
- ✅ SQL 注入防护
- ✅ XSS 攻击防护
- ✅ 输入参数校验
- ✅ 全局异常处理

---

## 🤝 贡献指南

欢迎提交 Issue 和 Pull Request！

1. Fork 本仓库
2. 创建分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送分支 (`git push origin feature/AmazingFeature`)
5. 创建 Pull Request

---

## 📄 许可证

[Apache License 2.0](LICENSE) © tangredtea

---

⭐ 如果这个项目对你有帮助，请给个 Star 支持一下！
