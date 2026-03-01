# SpringBoot AI 博客系统

[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE)
[![Stars](https://img.shields.io/github/stars/tangredtea/Spring-Blog?style=social)](https://github.com/tangredtea/Spring-Blog/stargazers)
[![Issues](https://img.shields.io/github/issues/tangredtea/Spring-Blog)](https://github.com/tangredtea/Spring-Blog/issues)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](http://makeapullrequest.com)

> 基于 Spring Boot + MyBatis 构建的个人博客系统，集成 AI 智能辅助、Redis 缓存和简洁的后台管理界面。

[English](README.md) | 简体中文

---

## 功能特性

### AI 智能集成
- **智能摘要** - AI 自动生成文章摘要
- **标签推荐** - 基于内容的 AI 标签建议
- **文章评分** - 质量评估与改进建议
- **智能搜索** - 关键词提取和相关文章推荐

### 内容管理
- **富文本编辑器** - Markdown 编辑器，支持实时预览
- **草稿系统** - 保存未发布的文章为草稿
- **分类与标签** - 灵活的内容组织方式
- **友情链接** - 友链管理
- **评论系统** - 基于 Valine 的无服务器评论
- **留言板** - 访客留言功能

### 后台管理
- **统计概览** - 文章数、浏览量、标签、分类一目了然
- **AI 状态监控** - 从仪表板检查 AI 服务可用性
- **快捷操作** - 常用操作的一键快捷方式
- **最新文章** - 最近发布的文章列表

### 性能优化
- **Redis 缓存** - 加速页面加载
- **数据库索引** - 优化查询性能
- **HikariCP** - 高性能连接池

### 安全特性
- **BCrypt 加密** - 安全的密码哈希（Spring Security Crypto）
- **SQL 注入防护** - MyBatis 参数化查询
- **登录拦截器** - 后台路由保护
- **输入验证** - Bean Validation（JSR-380）

### 附加功能
- **SEO 优化** - 站点地图生成、元标签、结构化数据
- **Markdown 支持** - CommonMark 解析器，支持 GFM 表格和标题锚点
- **异常监控** - 企业微信 Webhook 通知系统错误
- **全局异常处理** - 自定义错误页面和详细日志记录
- **AOP 日志** - 基于面向切面编程的请求/响应日志
- **定时任务** - 自动缓存刷新和维护

---

## 技术栈

| 层级 | 技术 |
|-------|-----------|
| 框架 | Spring Boot 2.7.x |
| ORM | MyBatis |
| 数据库 | MySQL 8.0 |
| 缓存 | Redis |
| 模板引擎 | Thymeleaf |
| 分页 | PageHelper |
| 密码加密 | BCrypt |
| 连接池 | HikariCP |

---

## 快速开始

### 环境要求
- JDK 8+（已在 JDK 21 上测试）
- MySQL 5.7+（推荐 MySQL 8.0）
- Redis 5.0+
- Maven 3.6+

### 安装步骤

1. **克隆仓库**
```bash
git clone https://github.com/tangredtea/Spring-Blog.git
cd Spring-Blog
```

2. **初始化数据库**
```bash
mysql -u root -p < blog.sql
```

3. **配置环境变量**（推荐）
```bash
export DB_USERNAME=root
export DB_PASSWORD=your_password
export REDIS_PASSWORD=your_redis_password  # 如果需要
export AI_API_KEY=your_openai_api_key      # 可选，用于 AI 功能
```

4. **运行应用**
```bash
mvn spring-boot:run
```

5. **访问应用**
- 前台：http://localhost:8080
- 后台管理：http://localhost:8080/admin
- 默认账号：`admin` / `admin123`

### Docker Compose 部署

```bash
# 1. 复制配置文件
cp .env.example .env

# 2. 编辑配置
vim .env

# 3. 启动所有服务
docker-compose up -d
```

---

## 项目结构

```
Spring-Blog/
├── src/main/java/com/blog/
│   ├── controller/      # 控制器（后台 + 前台 + 通用）
│   │   ├── admin/       # 后台管理控制器
│   │   ├── blog/        # 前台博客控制器
│   │   ├── common/      # 通用控制器
│   │   └── SitemapController.java  # SEO 站点地图
│   ├── service/         # 业务逻辑 & AI 服务
│   │   └── impl/        # 服务实现类
│   ├── dao/             # 数据访问层（MyBatis 映射器）
│   ├── entity/          # 实体类（Blog、User、Tag 等）
│   ├── pojo/            # 数据传输对象（DTOs）
│   ├── config/          # 配置（Redis、WebMvc、Settings）
│   ├── interceptor/     # 登录拦截器
│   ├── aspect/          # AOP 日志
│   ├── scheduled/       # 定时任务（缓存刷新）
│   ├── exception/       # 全局异常处理
│   ├── enums/           # 枚举类（BlogStatus 等）
│   └── util/            # 工具类（密码、SEO、Markdown 等）
├── src/main/resources/
│   ├── mapper/          # MyBatis XML 映射文件
│   ├── templates/       # Thymeleaf 模板
│   │   ├── admin/       # 后台管理页面
│   │   ├── fragments/   # 可复用片段
│   │   └── error/       # 错误页面（404、500）
│   ├── static/          # 静态资源（CSS/JS/图片）
│   │   ├── css/         # 样式表
│   │   ├── js/          # JavaScript 文件
│   │   ├── images/      # 图片
│   │   ├── fonts/       # Web 字体
│   │   └── lib/         # 第三方库
│   ├── application.yml  # 主配置文件
│   ├── application-dev.yml   # 开发环境配置
│   ├── application-pro.yml   # 生产环境配置
│   └── messages.properties   # 国际化消息
├── src/test/            # 单元测试
├── blog.sql             # 数据库结构 & 初始数据
├── Dockerfile           # Docker 构建
├── docker-compose.yml   # Docker Compose
├── nginx.conf           # Nginx 配置
└── .env.example         # 环境变量模板
```

---

## 配置说明

### AI 配置（可选）

AI 功能是可选的。要启用它们，请在 `application-dev.yml` 中设置或通过环境变量配置：

```yaml
ai:
  api:
    key: ${AI_API_KEY:}         # OpenAI API 密钥
    url: ${AI_API_URL:https://api.openai.com/v1/chat/completions}
  model: ${AI_MODEL:gpt-3.5-turbo}
```

当未配置 AI 时，系统会优雅地回退到默认行为（不会报错）。

### 站点设置

编辑 `src/main/resources/messages.properties` 自定义你的博客：

```properties
# 基本信息
web_Name=你的博客名称
web_Description=你的博客描述
web_Keywords=Java博客, 技术博客

# 社交链接
web_Github=https://github.com/yourusername
web_Csdn=https://blog.csdn.net/yourusername

# 评论系统（Valine）
valine_AppID=your_leancloud_appid
valine_AppKey=your_leancloud_appkey

# 企业微信 Webhook（可选 - 用于错误通知）
wx_Webhook=https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=your_key
# 设置为 "0" 可禁用 webhook 通知
```

---

## 贡献指南

欢迎贡献！随时提交 issue 和 pull request。

1. Fork 本仓库
2. 创建你的分支 (`git checkout -b feature/amazing-feature`)
3. 提交你的更改 (`git commit -m 'Add amazing feature'`)
4. 推送到分支 (`git push origin feature/amazing-feature`)
5. 开启 Pull Request

---

## 开源协议

[Apache License 2.0](LICENSE) - tangredtea
