# SpringBoot Blog Pro 🤖✨

[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE)
[![Stars](https://img.shields.io/github/stars/tangredtea/Spring-Blog?style=social)](https://github.com/tangredtea/Spring-Blog/stargazers)
[![Issues](https://img.shields.io/github/issues/tangredtea/Spring-Blog)](https://github.com/tangredtea/Spring-Blog/issues)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](http://makeapullrequest.com)
[![Tests](https://img.shields.io/badge/tests-24%20passed-green)]()

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
- **Gzip 压缩** - 减少传输体积

### 🔒 安全加固
- **BCrypt 加密** - 比 MD5 更安全的密码存储
- **环境变量配置** - 敏感信息不泄露
- **SQL 注入防护** - MyBatis `#{}` 占位符
- **XSS 攻击防护** - Thymeleaf 自动转义
- **全局异常处理** - 统一错误处理机制

---

## 🛠️ 技术栈

| 层级 | 技术 | 版本 |
|------|------|------|
| 框架 | Spring Boot | 2.7.18 |
| ORM | MyBatis | 2.3.1 |
| 数据库 | MySQL | 8.0.33 |
| 缓存 | Redis | Lettuce |
| 模板引擎 | Thymeleaf | 3.x |
| 分页 | PageHelper | 1.4.6 |
| 密码加密 | BCrypt (Spring Security) | 5.x |
| 连接池 | HikariCP | 4.x |
| 验证 | Hibernate Validator | 6.x |
| 测试 | JUnit 5 + Mockito | 5.x |

---

## 🚀 快速开始

### 📋 环境要求

- JDK 1.8+
- MySQL 5.7+ / 8.0+
- Redis 5.0+
- Maven 3.6+

### 📦 安装步骤

#### 1. 克隆项目
```bash
git clone https://github.com/tangredtea/Spring-Blog.git
cd Spring-Blog
```

#### 2. 创建数据库
```bash
mysql -u root -p < blog.sql
```

#### 3. 配置环境变量（⚠️ 重要）
```bash
# 数据库配置
export DB_USERNAME=root
export DB_PASSWORD=your_mysql_password

# Redis 配置（如果有密码）
export REDIS_PASSWORD=your_redis_password

# AI 配置（可选，用于 AI 功能）
export AI_API_KEY=your_openai_api_key
export AI_API_URL=https://api.openai.com/v1/chat/completions
export AI_MODEL=gpt-3.5-turbo
```

#### 4. 运行项目
```bash
# 开发模式
mvn spring-boot:run

# 或者打包后运行
mvn clean package
java -jar target/blog-1.0.0.jar
```

#### 5. 访问应用
- 🌐 前台：http://localhost:8080
- 🔐 后台：http://localhost:8080/admin
- 🤖 AI 助手：http://localhost:8080/admin/ai-assistant
- 🗺️ 站点地图：http://localhost:8080/sitemap.xml

**默认账号**：`admin` / `123456`

---

## 🐳 Docker 部署

### 使用 Docker Compose（推荐）

创建 `docker-compose.yml`：

```yaml
version: '3.8'
services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      - DB_USERNAME=root
      - DB_PASSWORD=password
      - REDIS_HOST=redis
      - AI_API_KEY=${AI_API_KEY}
    depends_on:
      - mysql
      - redis
    volumes:
      - ./logs:/logs

  mysql:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: password
      MYSQL_DATABASE: blog
    volumes:
      - mysql_data:/var/lib/mysql
      - ./blog.sql:/docker-entrypoint-initdb.d/init.sql

  redis:
    image: redis:alpine
    volumes:
      - redis_data:/data

volumes:
  mysql_data:
  redis_data:
```

启动：
```bash
docker-compose up -d
```

### 单独构建镜像

```bash
# 构建
docker build -t spring-blog:1.0.0 .

# 运行
docker run -d -p 8080:8080 \
  -e DB_USERNAME=root \
  -e DB_PASSWORD=password \
  -e AI_API_KEY=your_key \
  --name spring-blog \
  spring-blog:1.0.0
```

---

## 📁 项目结构

```
Spring-Blog/
├── src/main/java/com/blog/
│   ├── controller/           # 控制器层
│   │   ├── IndexController.java
│   │   ├── BlogController.java
│   │   ├── SitemapController.java      ✨ SEO
│   │   └── admin/
│   │       ├── AdminController.java
│   │       ├── AIController.java       ✨ AI
│   │       └── ...
│   ├── service/              # 业务层
│   │   ├── AIService.java               ✨ AI
│   │   ├── SitemapService.java          ✨ SEO
│   │   ├── SmartSearchService.java      ✨ 智能搜索
│   │   └── ...
│   ├── dao/                  # 数据访问层
│   ├── entity/               # 实体类
│   ├── config/               # 配置类
│   ├── util/                 # 工具类
│   │   ├── PasswordUtils.java           ✨ BCrypt
│   │   ├── SEOUtils.java                ✨ SEO
│   │   └── ...
│   ├── exception/            # 异常处理 ✨
│   └── enums/                # 枚举类 ✨
├── src/main/resources/
│   ├── mapper/               # MyBatis XML
│   ├── templates/            # Thymeleaf 模板
│   │   ├── index.html
│   │   ├── blog.html
│   │   └── admin/
│   │       ├── ai-assistant.html       ✨ AI助手
│   │       └── ...
│   ├── static/               # 静态资源
│   │   ├── css/
│   │   │   └── dark-mode.css           ✨ 暗黑模式
│   │   └── js/
│   │       └── theme.js                ✨ 主题切换
│   └── application*.yml      # 配置文件
├── src/test/java/com/blog/   # 单元测试 ✨
│   ├── service/
│   │   ├── AIServiceTest.java
│   │   └── SitemapServiceTest.java
│   └── util/
│       ├── PasswordUtilsTest.java
│       └── SEOUtilsTest.java
├── blog.sql                  # 数据库脚本
├── Dockerfile                # Docker 配置
├── docker-compose.yml        # Docker Compose
└── README.md                 # 本文件
```

---

## 🧪 运行测试

```bash
# 运行所有测试
mvn test

# 运行特定测试类
mvn test -Dtest=AIServiceTest
mvn test -Dtest=PasswordUtilsTest

# 生成测试报告
mvn surefire-report:report
```

### 测试覆盖

| 测试类 | 方法数 | 覆盖功能 |
|--------|--------|----------|
| AIServiceTest | 5 | AI 摘要、标签推荐、评分 |
| PasswordUtilsTest | 6 | BCrypt 加密、验证 |
| SEOUtilsTest | 9 | Meta 标签、OG、JSON-LD |
| SitemapServiceTest | 4 | 站点地图生成 |

**总计：24 个测试用例**

---

## 🔐 安全配置

### 必须配置的环境变量

```bash
# 数据库（必须）
DB_USERNAME=root
DB_PASSWORD=your_secure_password

# Redis（如果设置了密码）
REDIS_PASSWORD=your_redis_password

# AI 功能（可选）
AI_API_KEY=sk-your-openai-api-key
```

### 安全建议

1. **生产环境务必修改默认密码**
2. **使用强密码策略**（已集成 BCrypt）
3. **定期更新依赖**（关注 GitHub Security Alerts）
4. **启用 HTTPS**（生产环境）
5. **配置防火墙**，仅开放必要端口

---

## 📈 更新日志

### v2.0.0 (2026-02-27) - 重大更新 🎉

#### 🤖 AI 功能
- 集成 OpenAI API，支持 GPT-3.5/4
- 智能文章摘要生成
- 标签自动推荐
- 文章质量评分
- AI 助手管理后台

#### 🔍 SEO/AEO 优化
- 自动生成 sitemap.xml
- Schema.org 结构化数据
- Open Graph / Twitter Card
- 面包屑导航优化
- FAQ 结构化数据

#### 🎨 用户体验
- 暗黑模式支持
- 主题偏好记忆
- 响应式布局优化

#### 🔒 安全升级
- MD5 → BCrypt 密码加密
- 全局异常处理
- 输入参数校验
- 配置文件安全化

#### 🧪 质量保证
- 新增 24 个单元测试
- 依赖安全升级
- 代码规范优化

### v1.1.0 (2022-02-22)
- 修复安全漏洞
- 优化数据库性能
- 添加 Docker 支持

### v1.0.0 (2021-03-20)
- 初始版本发布
- 基础博客功能
- 后台管理系统

---

## 🤝 贡献指南

欢迎提交 Issue 和 Pull Request！

### 开发流程

1. **Fork** 本仓库
2. **创建分支** (`git checkout -b feature/AmazingFeature`)
3. **编写代码**（遵循阿里巴巴 Java 规范）
4. **添加测试**（覆盖率 > 80%）
5. **提交更改** (`git commit -m 'Add some AmazingFeature'`)
6. **推送分支** (`git push origin feature/AmazingFeature`)
7. **创建 Pull Request**

### 代码规范

- 遵循 [阿里巴巴 Java 开发手册](https://github.com/alibaba/p3c)
- 使用 4 空格缩进
- 类名 UpperCamelCase，方法名 lowerCamelCase
- 常量使用 UPPER_SNAKE_CASE

---

## 📝 待办事项

- [ ] 评论审核功能
- [ ] 邮件通知服务
- [ ] 统计分析面板
- [ ] 图片上传云存储（OSS）
- [ ] 文章导出 PDF/Markdown
- [ ] 多语言支持
- [ ] PWA 离线访问

---

## 📄 许可证

[Apache License 2.0](LICENSE) © [tangredtea](https://github.com/tangredtea)

---

## 💖 致谢

感谢以下开源项目：

- [Spring Boot](https://spring.io/projects/spring-boot)
- [MyBatis](https://mybatis.org/)
- [Thymeleaf](https://www.thymeleaf.org/)
- [OpenAI](https://openai.com/) (AI 功能)

---

⭐ **如果这个项目对你有帮助，请给个 Star 支持一下！**

📧 **联系方式**：如有问题，欢迎提交 Issue 或发送邮件
