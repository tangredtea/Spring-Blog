# SpringBoot AI Blog

[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE)
[![Stars](https://img.shields.io/github/stars/tangredtea/Spring-Blog?style=social)](https://github.com/tangredtea/Spring-Blog/stargazers)
[![Issues](https://img.shields.io/github/issues/tangredtea/Spring-Blog)](https://github.com/tangredtea/Spring-Blog/issues)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](http://makeapullrequest.com)

> A personal blog system built with Spring Boot + MyBatis, featuring AI-powered content assistance, Redis caching, and a clean admin dashboard.

English | [简体中文](README_CN.md)

---

## Features

### AI Integration
- **Smart Summary** - Auto-generate article summaries via AI
- **Tag Suggestions** - AI-recommended tags based on content
- **Article Scoring** - Quality assessment with improvement suggestions
- **Smart Search** - Keyword extraction and related article recommendations

### Content Management
- **Rich Editor** - Markdown editor with live preview
- **Draft System** - Save unpublished articles as drafts
- **Category & Tags** - Flexible content organization
- **Friend Links** - Blogroll management
- **Comment System** - Valine-based serverless comments
- **Message Board** - Visitor guestbook

### Admin Dashboard
- **Statistics Overview** - Article count, views, tags, categories at a glance
- **AI Status Monitor** - Check AI service availability from dashboard
- **Quick Actions** - One-click shortcuts for common operations
- **Recent Articles** - Latest published posts table

### Performance
- **Redis Caching** - Accelerated page loading
- **Database Indexing** - Optimized query performance
- **HikariCP** - High-performance connection pool

### Security
- **BCrypt Encryption** - Secure password hashing (Spring Security Crypto)
- **SQL Injection Protection** - MyBatis parameterized queries
- **Login Interceptor** - Admin route protection
- **Input Validation** - Bean Validation (JSR-380)

### Additional Features
- **SEO Optimization** - Sitemap generation, meta tags, structured data
- **Markdown Support** - CommonMark parser with GFM tables and heading anchors
- **Exception Monitoring** - WeChat Work webhook notifications for system errors
- **Global Exception Handling** - Custom error pages with detailed logging
- **AOP Logging** - Request/response logging with aspect-oriented programming
- **Scheduled Tasks** - Automatic cache refresh and maintenance

---

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Framework | Spring Boot 2.7.x |
| ORM | MyBatis |
| Database | MySQL 8.0 |
| Cache | Redis |
| Template Engine | Thymeleaf |
| Pagination | PageHelper |
| Password Encryption | BCrypt |
| Connection Pool | HikariCP |

---

## Quick Start

### Prerequisites
- JDK 8+ (tested with JDK 21)
- MySQL 5.7+ (recommended MySQL 8.0)
- Redis 5.0+
- Maven 3.6+

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/tangredtea/Spring-Blog.git
cd Spring-Blog
```

2. **Initialize the database**
```bash
mysql -u root -p < blog.sql
```

3. **Configure environment variables** (recommended)
```bash
export DB_USERNAME=root
export DB_PASSWORD=your_password
export REDIS_PASSWORD=your_redis_password  # if applicable
export AI_API_KEY=your_openai_api_key      # optional, for AI features
```

4. **Run the application**
```bash
mvn spring-boot:run
```

5. **Access the application**
- Frontend: http://localhost:8080
- Admin panel: http://localhost:8080/admin
- Default credentials: `admin` / `admin123`

### Docker Compose Deployment

```bash
# 1. Copy config
cp .env.example .env

# 2. Edit config
vim .env

# 3. Start all services
docker-compose up -d
```

---

## Project Structure

```
Spring-Blog/
├── src/main/java/com/blog/
│   ├── controller/      # Controllers (admin + frontend + common)
│   │   ├── admin/       # Admin panel controllers
│   │   ├── blog/        # Frontend blog controllers
│   │   ├── common/      # Common controllers
│   │   └── SitemapController.java  # SEO sitemap
│   ├── service/         # Business logic & AI services
│   │   └── impl/        # Service implementations
│   ├── dao/             # Data access layer (MyBatis mappers)
│   ├── entity/          # Entity classes (Blog, User, Tag, etc.)
│   ├── pojo/            # Plain Old Java Objects (DTOs)
│   ├── config/          # Configuration (Redis, WebMvc, Settings)
│   ├── interceptor/     # Login interceptor
│   ├── aspect/          # AOP logging
│   ├── scheduled/       # Scheduled tasks (cache refresh)
│   ├── exception/       # Global exception handling
│   ├── enums/           # Enumerations (BlogStatus, etc.)
│   └── util/            # Utilities (Password, SEO, Markdown, etc.)
├── src/main/resources/
│   ├── mapper/          # MyBatis XML mappers
│   ├── templates/       # Thymeleaf templates
│   │   ├── admin/       # Admin panel pages
│   │   ├── fragments/   # Reusable fragments
│   │   └── error/       # Error pages (404, 500)
│   ├── static/          # Static resources (CSS/JS/images)
│   │   ├── css/         # Stylesheets
│   │   ├── js/          # JavaScript files
│   │   ├── images/      # Images
│   │   ├── fonts/       # Web fonts
│   │   └── lib/         # Third-party libraries
│   ├── application.yml  # Main configuration
│   ├── application-dev.yml   # Development config
│   ├── application-pro.yml   # Production config
│   └── messages.properties   # i18n messages
├── src/test/            # Unit tests
├── blog.sql             # Database schema & seed data
├── Dockerfile           # Docker build
├── docker-compose.yml   # Docker Compose
├── nginx.conf           # Nginx configuration
└── .env.example         # Environment variables template
```

---

## Configuration

### AI Configuration (Optional)

AI features are optional. To enable them, set the following in `application-dev.yml` or via environment variables:

```yaml
ai:
  api:
    key: ${AI_API_KEY:}         # OpenAI API key
    url: ${AI_API_URL:https://api.openai.com/v1/chat/completions}
  model: ${AI_MODEL:gpt-3.5-turbo}
```

When AI is not configured, the system gracefully falls back to default behavior (no errors).

### Site Settings

Edit `src/main/resources/messages.properties` to customize your blog:

```properties
# Basic Info
web_Name=Your Blog Name
web_Description=Your blog description
web_Keywords=Java Blog, Tech Blog

# Social Links
web_Github=https://github.com/yourusername
web_Csdn=https://blog.csdn.net/yourusername

# Comment System (Valine)
valine_AppID=your_leancloud_appid
valine_AppKey=your_leancloud_appkey

# WeChat Work Webhook (Optional - for error notifications)
wx_Webhook=https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=your_key
# Set to "0" to disable webhook notifications
```

---

## Contributing

Contributions are welcome! Feel free to open issues and pull requests.

1. Fork the repository
2. Create your branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## License

[Apache License 2.0](LICENSE) - tangredtea
