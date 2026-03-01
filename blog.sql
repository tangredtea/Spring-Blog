-- --------------------------------------------------------
-- Spring-Blog 初始化数据
-- 适用于 MySQL 8.x
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE IF NOT EXISTS `blog` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `blog`;

-- ----------------------------
-- 用户表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表主键id',
  `username` varchar(255) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '用户密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
REPLACE INTO `t_user` (`id`, `username`, `password`, `nickname`, `email`, `avatar`) VALUES
    (1, 'admin', '$2a$10$obu8GJJJ6CbBr6oS/HIQGeH1E4MsfXiirhC.y0NxiYtMWI5HUoxA6', 'tangredtea', 'tangredtea@gmail.com', 'https://picsum.photos/seed/admin/200/200'),
    (2, 'guest', '$2a$10$b0k6ETX.fw2e2RmtlUSSnORFTkg/2FchBuT12seoGnV0b1iQvyrLK', '访客编辑', 'guest@example.com', 'https://picsum.photos/seed/guest/200/200');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;

-- ----------------------------
-- 分类表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `t_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类主键id',
  `name` varchar(255) NOT NULL COMMENT '分类名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*!40000 ALTER TABLE `t_type` DISABLE KEYS */;
REPLACE INTO `t_type` (`id`, `name`) VALUES
    (1, 'Java后端'),
    (2, 'Spring框架'),
    (3, '数据库'),
    (4, '前端开发'),
    (5, '架构设计'),
    (6, 'DevOps'),
    (7, '读书笔记');
/*!40000 ALTER TABLE `t_type` ENABLE KEYS */;

-- ----------------------------
-- 标签表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `t_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签表主键',
  `name` varchar(255) NOT NULL COMMENT '标签名字',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*!40000 ALTER TABLE `t_tag` DISABLE KEYS */;
REPLACE INTO `t_tag` (`id`, `name`) VALUES
    (1, 'Java'),
    (2, 'Spring Boot'),
    (3, 'MySQL'),
    (4, 'Redis'),
    (5, 'MyBatis'),
    (6, 'Docker'),
    (7, 'Vue.js'),
    (8, '设计模式'),
    (9, '并发编程'),
    (10, 'JVM'),
    (11, 'Linux'),
    (12, '微服务');
/*!40000 ALTER TABLE `t_tag` ENABLE KEYS */;

-- ----------------------------
-- 博客表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `t_blog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '博客表主键id',
  `title` varchar(255) DEFAULT NULL COMMENT '博客标题',
  `description` text COMMENT '文章描述',
  `content` mediumtext COMMENT '博文内容',
  `first_picture` varchar(255) DEFAULT NULL COMMENT '博文封面',
  `views` int(11) DEFAULT '0' COMMENT '文章阅读量',
  `flag` bit(1) DEFAULT NULL COMMENT '文章状态位，1：原创，0：转载',
  `appreciation` bit(1) DEFAULT NULL COMMENT '文章状态位，1：开启，0：关闭',
  `share_statement` bit(1) DEFAULT NULL COMMENT '分享状态位，1：开启，0：关闭',
  `commentable` bit(1) DEFAULT NULL COMMENT '评论状态位，1：开启，0：关闭',
  `published` bit(1) DEFAULT NULL COMMENT '发布状态位，1：已发布，0：草稿',
  `recommend` bit(1) DEFAULT NULL COMMENT '推荐状态位，1：开启，0：关闭',
  `is_deleted` bit(1) DEFAULT b'0' COMMENT '删除状态，1：已删除，0：正常',
  `is_top` bit(1) DEFAULT b'0' COMMENT '置顶状态，1：置顶，0：普通',
  `password` varchar(64) DEFAULT NULL COMMENT '文章密码，为空表示公开',
  `create_time` datetime DEFAULT NULL COMMENT '文章创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '文章修改时间',
  `publish_time` datetime DEFAULT NULL COMMENT '文章发布时间',
  `type_id` int(11) DEFAULT NULL COMMENT '关联的分类id',
  `user_id` int(11) DEFAULT NULL COMMENT '关联的用户id',
  `tag_ids` varchar(100) DEFAULT NULL COMMENT '关联标签',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_type_user` (`type_id`,`user_id`) USING BTREE,
  KEY `idx_published` (`published`) USING BTREE,
  KEY `idx_is_deleted` (`is_deleted`) USING BTREE,
  KEY `idx_create_time` (`create_time`) USING BTREE,
  KEY `idx_views` (`views` DESC) USING BTREE,
  KEY `idx_recommend_update` (`recommend`,`update_time` DESC) USING BTREE,
  FULLTEXT KEY `ft_title_content` (`title`,`description`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

/*!40000 ALTER TABLE `t_blog` DISABLE KEYS */;
REPLACE INTO `t_blog` (`id`, `title`, `description`, `content`, `first_picture`, `views`, `flag`, `appreciation`, `share_statement`, `commentable`, `published`, `recommend`, `is_deleted`, `is_top`, `create_time`, `update_time`, `publish_time`, `type_id`, `user_id`, `tag_ids`) VALUES
    (1, 'Spring Boot 3.x 新特性详解与迁移指南',
     '本文将全面介绍 Spring Boot 3.x 带来的重大变化，包括 Java 17 基线、Jakarta EE 9+ 迁移、GraalVM 原生镜像支持等核心特性，帮助开发者平稳升级。',
     '## Spring Boot 3.x 新特性\n\n### 1. Java 17 基线\n\nSpring Boot 3.x 将最低 Java 版本提升至 17，可以使用 Records、Sealed Classes、Pattern Matching 等新语法特性。\n\n```java\npublic record UserDTO(String name, String email) {}\n```\n\n### 2. Jakarta EE 9+ 迁移\n\n所有 `javax.*` 包名已改为 `jakarta.*`，这是最大的破坏性变更。\n\n```java\n// Before\nimport javax.servlet.http.HttpServletRequest;\n// After\nimport jakarta.servlet.http.HttpServletRequest;\n```\n\n### 3. GraalVM 原生镜像\n\nSpring Boot 3.x 内建对 GraalVM Native Image 的支持，应用启动时间可缩短至毫秒级。\n\n### 4. 可观测性增强\n\n引入 Micrometer Observation API，统一了 Metrics 和 Tracing 的编程模型。\n\n### 总结\n\nSpring Boot 3.x 是一次重大升级，建议在新项目中直接采用，老项目逐步迁移。',
     'https://picsum.photos/seed/springboot3/800/450', 1286, b'1', b'1', b'1', b'1', b'1', b'1', b'0', b'1',
     '2025-11-10 09:30:00', '2026-01-15 14:20:00', '2025-11-10 10:00:00', 2, 1, '1,2'),

    (2, '深入理解 JVM 垃圾回收机制：从 CMS 到 ZGC',
     '详细分析 JVM 中主流垃圾回收器的工作原理，包括 CMS、G1、ZGC 的对比，以及在生产环境中的调优经验。',
     '## JVM 垃圾回收机制\n\n### 垃圾回收算法基础\n\n- **标记-清除**：最基础的 GC 算法\n- **标记-整理**：解决内存碎片问题\n- **复制算法**：新生代常用策略\n\n### CMS 收集器\n\nConcurrent Mark Sweep，以最短停顿时间为目标的收集器，适合对响应时间敏感的应用。\n\n### G1 收集器\n\n将堆内存分割为多个 Region，兼顾吞吐量和停顿时间。JDK 9 起成为默认 GC。\n\n### ZGC\n\n- 停顿时间不超过 10ms\n- 支持 TB 级堆内存\n- JDK 15 开始可用于生产\n\n```bash\njava -XX:+UseZGC -Xmx16g -jar app.jar\n```\n\n### 调优建议\n\n1. 优先选择 G1 或 ZGC\n2. 合理设置堆大小\n3. 关注 GC 日志分析',
     'https://picsum.photos/seed/jvmgc/800/450', 952, b'1', b'1', b'1', b'1', b'1', b'1', b'0', b'0',
     '2025-10-05 15:00:00', '2025-12-20 10:30:00', '2025-10-05 16:00:00', 1, 1, '1,10'),

    (3, 'MySQL 索引优化实战：从慢查询到秒级响应',
     '通过真实案例演示 MySQL 慢查询分析和索引优化过程，涵盖 EXPLAIN 解读、联合索引设计、覆盖索引等核心技巧。',
     '## MySQL 索引优化\n\n### 问题背景\n\n生产环境某查询耗时超过 5 秒，影响用户体验。\n\n### EXPLAIN 分析\n\n```sql\nEXPLAIN SELECT * FROM orders \nWHERE user_id = 1001 AND status = 1 \nORDER BY create_time DESC LIMIT 20;\n```\n\n`type: ALL` 表示全表扫描，需要优化。\n\n### 优化方案\n\n#### 1. 建立联合索引\n\n```sql\nALTER TABLE orders ADD INDEX idx_user_status_time(user_id, status, create_time);\n```\n\n#### 2. 使用覆盖索引\n\n只查询索引中包含的列，避免回表。\n\n#### 3. 分页优化\n\n深分页使用游标方式替代 OFFSET。\n\n### 优化结果\n\n查询时间从 5.2s 降至 12ms，性能提升 400 倍。',
     'https://picsum.photos/seed/mysqlindex/800/450', 738, b'1', b'1', b'1', b'1', b'1', b'0', b'0', b'0',
     '2025-09-18 11:00:00', '2025-11-05 08:45:00', '2025-09-18 12:00:00', 3, 1, '3,1'),

    (4, 'Redis 分布式锁的正确实现方式',
     '对比分析基于 SETNX、Redisson、RedLock 三种 Redis 分布式锁方案的优缺点，给出生产环境推荐实践。',
     '## Redis 分布式锁\n\n### 为什么需要分布式锁？\n\n在微服务架构下，多个实例可能同时操作同一资源，需要分布式锁保证互斥。\n\n### 方案一：SETNX + EXPIRE\n\n```java\nBoolean locked = redisTemplate.opsForValue()\n    .setIfAbsent(\"lock:order:\" + orderId, requestId, 30, TimeUnit.SECONDS);\n```\n\n存在的问题：锁过期但业务未执行完。\n\n### 方案二：Redisson\n\n```java\nRLock lock = redissonClient.getLock(\"lock:order:\" + orderId);\ntry {\n    lock.lock(30, TimeUnit.SECONDS);\n    // 业务逻辑\n} finally {\n    lock.unlock();\n}\n```\n\nRedisson 内置看门狗机制，自动续期。\n\n### 方案三：RedLock\n\n适用于 Redis 集群场景，需要在多数节点加锁成功。\n\n### 推荐\n\n单机/哨兵模式用 Redisson，集群模式考虑 RedLock。',
     'https://picsum.photos/seed/redislock/800/450', 623, b'1', b'1', b'1', b'1', b'1', b'1', b'0', b'0',
     '2025-12-01 10:30:00', '2026-01-08 16:00:00', '2025-12-01 11:00:00', 1, 1, '1,4'),

    (5, '使用 Docker Compose 编排 Spring Boot 微服务',
     '手把手教你用 Docker Compose 将 Spring Boot 应用、MySQL、Redis、Nginx 组合成完整的微服务部署方案。',
     '## Docker Compose 微服务部署\n\n### 项目结构\n\n```\nproject/\n├── docker-compose.yml\n├── app/\n│   └── Dockerfile\n├── nginx/\n│   └── nginx.conf\n└── mysql/\n    └── init.sql\n```\n\n### docker-compose.yml\n\n```yaml\nversion: \"3.8\"\nservices:\n  app:\n    build: ./app\n    ports:\n      - \"8080:8080\"\n    depends_on:\n      - mysql\n      - redis\n    environment:\n      - SPRING_PROFILES_ACTIVE=prod\n\n  mysql:\n    image: mysql:8.0\n    volumes:\n      - mysql_data:/var/lib/mysql\n    environment:\n      MYSQL_ROOT_PASSWORD: secret\n      MYSQL_DATABASE: blog\n\n  redis:\n    image: redis:7-alpine\n    ports:\n      - \"6379:6379\"\n\n  nginx:\n    image: nginx:alpine\n    ports:\n      - \"80:80\"\n    volumes:\n      - ./nginx/nginx.conf:/etc/nginx/nginx.conf\n```\n\n### 一键启动\n\n```bash\ndocker compose up -d\n```',
     'https://picsum.photos/seed/docker/800/450', 512, b'1', b'1', b'1', b'1', b'1', b'0', b'0', b'0',
     '2026-01-20 14:00:00', '2026-02-10 09:15:00', '2026-01-20 15:00:00', 6, 1, '6,2,11'),

    (6, 'Vue 3 组合式 API 完全指南',
     '深入讲解 Vue 3 Composition API 的核心概念，包括 ref、reactive、computed、watch 的使用场景和最佳实践。',
     '## Vue 3 Composition API\n\n### 为什么要用组合式 API？\n\nOptions API 在组件复杂后，相关逻辑分散在 data/methods/computed 中，难以维护。Composition API 按逻辑关注点组织代码。\n\n### ref vs reactive\n\n```javascript\nimport { ref, reactive } from \"vue\";\n\n// 基本类型用 ref\nconst count = ref(0);\n\n// 对象用 reactive\nconst user = reactive({\n  name: \"张三\",\n  age: 25\n});\n```\n\n### 自定义 Hook\n\n```javascript\nexport function useCounter(initial = 0) {\n  const count = ref(initial);\n  const increment = () => count.value++;\n  const decrement = () => count.value--;\n  return { count, increment, decrement };\n}\n```\n\n### 与 TypeScript 配合\n\nVue 3 对 TypeScript 支持更好，推荐使用 `<script setup lang=\"ts\">`。',
     'https://picsum.photos/seed/vue3/800/450', 487, b'1', b'1', b'1', b'1', b'1', b'0', b'0', b'0',
     '2025-08-22 09:00:00', '2025-10-15 11:30:00', '2025-08-22 10:00:00', 4, 1, '7'),

    (7, '设计模式在 Spring 框架中的应用',
     '结合 Spring 源码分析常用设计模式：工厂模式、代理模式、观察者模式、模板方法模式等在框架中的精妙运用。',
     '## Spring 中的设计模式\n\n### 1. 工厂模式 — BeanFactory\n\nSpring IoC 容器本身就是一个超级工厂，管理所有 Bean 的创建和生命周期。\n\n```java\nApplicationContext context = new ClassPathXmlApplicationContext(\"beans.xml\");\nUserService service = context.getBean(UserService.class);\n```\n\n### 2. 代理模式 — AOP\n\nSpring AOP 通过 JDK 动态代理或 CGLIB 实现切面编程。\n\n```java\n@Aspect\n@Component\npublic class LogAspect {\n    @Around(\"execution(* com.blog.service..*.*(..))\")\n    public Object log(ProceedingJoinPoint pjp) throws Throwable {\n        long start = System.currentTimeMillis();\n        Object result = pjp.proceed();\n        log.info(\"耗时: {}ms\", System.currentTimeMillis() - start);\n        return result;\n    }\n}\n```\n\n### 3. 观察者模式 — ApplicationEvent\n\nSpring 事件机制实现组件间松耦合通信。\n\n### 4. 模板方法 — JdbcTemplate\n\n封装了 JDBC 样板代码，用户只需关注 SQL 和结果映射。',
     'https://picsum.photos/seed/designpattern/800/450', 395, b'1', b'1', b'1', b'1', b'1', b'1', b'0', b'0',
     '2025-07-15 13:00:00', '2025-09-22 17:00:00', '2025-07-15 14:00:00', 2, 1, '2,8,1'),

    (8, 'Java 并发编程：线程池核心原理与调优',
     '从 ThreadPoolExecutor 源码出发，解析线程池的工作流程、拒绝策略、参数配置，以及生产环境常见问题排查。',
     '## Java 线程池\n\n### 为什么不用 Executors 创建线程池？\n\n`Executors.newFixedThreadPool()` 使用无界队列，可能导致 OOM。推荐手动创建 `ThreadPoolExecutor`。\n\n### 核心参数\n\n```java\nnew ThreadPoolExecutor(\n    4,                // corePoolSize\n    8,                // maximumPoolSize\n    60L,              // keepAliveTime\n    TimeUnit.SECONDS,\n    new LinkedBlockingQueue<>(1000),  // 有界队列\n    new ThreadPoolExecutor.CallerRunsPolicy()  // 拒绝策略\n);\n```\n\n### 工作流程\n\n1. 核心线程未满 → 创建新线程\n2. 核心线程满 → 入队列\n3. 队列满 → 创建非核心线程（不超过 max）\n4. 全满 → 执行拒绝策略\n\n### 调优建议\n\n- IO 密集型：coreSize = CPU * 2\n- CPU 密集型：coreSize = CPU + 1\n- 使用有界队列，设置合理容量',
     'https://picsum.photos/seed/threadpool/800/450', 341, b'1', b'1', b'1', b'1', b'1', b'0', b'0', b'0',
     '2025-06-28 16:00:00', '2025-08-10 14:20:00', '2025-06-28 17:00:00', 1, 1, '1,9'),

    (9, '从零搭建 CI/CD 流水线：GitHub Actions 实践',
     '介绍如何使用 GitHub Actions 为 Spring Boot 项目搭建自动化构建、测试、部署流水线，实现代码推送即部署。',
     '## GitHub Actions CI/CD\n\n### Workflow 文件\n\n```yaml\nname: CI/CD Pipeline\non:\n  push:\n    branches: [main]\n\njobs:\n  build:\n    runs-on: ubuntu-latest\n    steps:\n      - uses: actions/checkout@v4\n      - uses: actions/setup-java@v4\n        with:\n          java-version: 17\n          distribution: temurin\n\n      - name: Build & Test\n        run: mvn clean verify\n\n      - name: Build Docker Image\n        run: docker build -t myapp:${{ github.sha }} .\n\n      - name: Deploy\n        run: |\n          ssh deploy@server \"docker pull myapp && docker compose up -d\"\n```\n\n### 最佳实践\n\n1. 分离 build 和 deploy job\n2. 使用 GitHub Secrets 管理密钥\n3. 添加缓存加速构建\n4. 设置通知（Slack/邮件）',
     'https://picsum.photos/seed/cicd/800/450', 278, b'1', b'1', b'1', b'1', b'1', b'0', b'0', b'0',
     '2026-02-05 10:00:00', '2026-02-20 15:30:00', '2026-02-05 11:00:00', 6, 1, '6,11'),

    (10, '《Effective Java》读书笔记：最值得记住的 10 条建议',
     '精选 Joshua Bloch 经典著作《Effective Java》中最实用的编程建议，结合现代 Java 特性给出具体代码示例。',
     '## Effective Java 精选\n\n### 1. 用静态工厂方法代替构造器\n\n```java\npublic static Boolean valueOf(boolean b) {\n    return b ? Boolean.TRUE : Boolean.FALSE;\n}\n```\n\n### 2. 遇到多个构造器参数时考虑 Builder 模式\n\n```java\nUser user = User.builder()\n    .name(\"张三\")\n    .age(25)\n    .email(\"zhangsan@example.com\")\n    .build();\n```\n\n### 3. 用枚举实现单例\n\n```java\npublic enum Singleton {\n    INSTANCE;\n    public void doSomething() { ... }\n}\n```\n\n### 4. 优先使用泛型\n\n### 5. 用 Optional 代替 null\n\n```java\nOptional<User> user = userService.findById(id);\nuser.ifPresent(u -> log.info(\"Found: {}\", u.getName()));\n```\n\n### 6. 谨慎使用 Stream\n\n### 7. 优先使用标准异常\n\n### 8. 使用 try-with-resources\n\n### 9. 用接口定义类型\n\n### 10. 使类和成员的可访问性最小化',
     'https://picsum.photos/seed/effectivejava/800/450', 189, b'1', b'0', b'1', b'1', b'1', b'0', b'0', b'0',
     '2026-02-18 20:00:00', '2026-02-25 10:00:00', '2026-02-18 21:00:00', 7, 1, '1,8');
/*!40000 ALTER TABLE `t_blog` ENABLE KEYS */;

-- ----------------------------
-- 博客-标签关联表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `t_blog_tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '关联表主键',
  `tag_id` int(11) DEFAULT NULL COMMENT '标签id',
  `blog_id` bigint(20) DEFAULT NULL COMMENT '博文id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*!40000 ALTER TABLE `t_blog_tags` DISABLE KEYS */;
REPLACE INTO `t_blog_tags` (`id`, `tag_id`, `blog_id`) VALUES
    (1, 1, 1),   -- Spring Boot 3.x → Java
    (2, 2, 1),   -- Spring Boot 3.x → Spring Boot
    (3, 1, 2),   -- JVM GC → Java
    (4, 10, 2),  -- JVM GC → JVM
    (5, 3, 3),   -- MySQL 索引 → MySQL
    (6, 1, 3),   -- MySQL 索引 → Java
    (7, 1, 4),   -- Redis 分布式锁 → Java
    (8, 4, 4),   -- Redis 分布式锁 → Redis
    (9, 6, 5),   -- Docker Compose → Docker
    (10, 2, 5),  -- Docker Compose → Spring Boot
    (11, 11, 5), -- Docker Compose → Linux
    (12, 7, 6),  -- Vue 3 → Vue.js
    (13, 2, 7),  -- 设计模式 in Spring → Spring Boot
    (14, 8, 7),  -- 设计模式 in Spring → 设计模式
    (15, 1, 7),  -- 设计模式 in Spring → Java
    (16, 1, 8),  -- 线程池 → Java
    (17, 9, 8),  -- 线程池 → 并发编程
    (18, 6, 9),  -- CI/CD → Docker
    (19, 11, 9), -- CI/CD → Linux
    (20, 1, 10), -- Effective Java → Java
    (21, 8, 10); -- Effective Java → 设计模式
/*!40000 ALTER TABLE `t_blog_tags` ENABLE KEYS */;

-- ----------------------------
-- 留言表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `t_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '留言表主键id',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `parent_message_id` bigint(20) DEFAULT NULL COMMENT '父留言id',
  `admin_message` bit(1) NOT NULL COMMENT '是否为管理员评论',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*!40000 ALTER TABLE `t_message` DISABLE KEYS */;
REPLACE INTO `t_message` (`id`, `nickname`, `email`, `content`, `avatar`, `create_time`, `parent_message_id`, `admin_message`) VALUES
    -- 顶级留言
    (1, '陈同学', 'chenxiao@163.com', '博主写得太好了，Spring Boot 3 那篇帮我顺利完成了项目迁移，感谢分享！', 'https://picsum.photos/seed/user1/100/100', '2026-01-20 10:15:00', -1, b'0'),
    (2, '李开发', 'lidev@gmail.com', '请问博主，Redis 分布式锁在集群环境下 RedLock 真的可靠吗？Martin Kleppmann 的那篇质疑文章怎么看？', 'https://picsum.photos/seed/user2/100/100', '2026-01-25 14:30:00', -1, b'0'),
    (3, 'tangredtea', 'tangredtea@gmail.com', '好问题！RedLock 确实存在争议，对于强一致性场景建议用 ZooKeeper 或 etcd。一般业务场景 Redisson 单节点锁就够用了。', 'https://picsum.photos/seed/admin/200/200', '2026-01-25 16:45:00', 2, b'1'),
    (4, '王小明', 'wangxm@qq.com', '刚入门 Java 后端，请问学习路线怎么规划比较好？看了博主的文章感觉知识体系很清晰。', 'https://picsum.photos/seed/user3/100/100', '2026-02-01 09:20:00', -1, b'0'),
    (5, 'tangredtea', 'tangredtea@gmail.com', '建议路线：Java 基础 → MySQL → Spring Boot → Redis → 项目实战。不用贪多，每个阶段做一个小项目巩固。', 'https://picsum.photos/seed/admin/200/200', '2026-02-01 11:00:00', 4, b'1'),
    (6, '赵工程师', 'zhaodev@outlook.com', '线程池那篇写得很清楚！补充一点：Spring Boot 中可以用 @Async + 自定义 ThreadPoolTaskExecutor 更方便。', 'https://picsum.photos/seed/user4/100/100', '2026-02-05 17:30:00', -1, b'0'),
    (7, '刘学生', 'liustudent@edu.cn', '大四准备春招了，看了设计模式那篇文章很有启发。请问面试一般会考哪些设计模式？', 'https://picsum.photos/seed/user5/100/100', '2026-02-10 20:15:00', -1, b'0'),
    (8, 'tangredtea', 'tangredtea@gmail.com', '面试高频：单例、工厂、策略、观察者、代理。结合 Spring 源码聊会加分不少，加油！', 'https://picsum.photos/seed/admin/200/200', '2026-02-10 22:00:00', 7, b'1'),
    (9, '周运维', 'zhouops@company.com', 'Docker Compose 那篇正好是我需要的，之前一直手动部署太痛苦了。已经按照教程跑起来了，完美！', 'https://picsum.photos/seed/user6/100/100', '2026-02-15 08:45:00', -1, b'0'),
    (10, '孙前端', 'sunfe@gmail.com', 'Vue 3 那篇讲得很透彻，特别是 ref 和 reactive 的区别。希望博主可以出一篇 Pinia 状态管理的教程。', 'https://picsum.photos/seed/user7/100/100', '2026-02-18 15:20:00', -1, b'0'),
    (11, '李开发', 'lidev@gmail.com', '感谢博主的回复！确实 Redisson 对大部分场景够用了。又学到了。', 'https://picsum.photos/seed/user2/100/100', '2026-01-26 09:00:00', 2, b'0'),
    (12, '吴 DBA', 'wudba@company.com', 'MySQL 索引优化那篇非常实用，我们线上也遇到过类似的深分页问题，游标方案确实好用。', 'https://picsum.photos/seed/user8/100/100', '2026-02-22 11:30:00', -1, b'0');
/*!40000 ALTER TABLE `t_message` ENABLE KEYS */;

-- ----------------------------
-- 友链表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `t_friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '友链表主键',
  `blog_address` varchar(255) NOT NULL COMMENT '友链地址',
  `blog_name` varchar(255) NOT NULL COMMENT '友链名字',
  `picture_address` varchar(255) NOT NULL COMMENT '友链图标',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*!40000 ALTER TABLE `t_friend` DISABLE KEYS */;
REPLACE INTO `t_friend` (`id`, `blog_address`, `blog_name`, `picture_address`, `create_time`) VALUES
    (1, 'https://www.ruanyifeng.com/blog/', '阮一峰的网络日志', 'https://picsum.photos/seed/ruanyf/200/200', '2025-06-15 10:00:00'),
    (2, 'https://tech.meituan.com/', '美团技术团队', 'https://picsum.photos/seed/meituan/200/200', '2025-07-20 14:30:00'),
    (3, 'https://coolshell.cn/', '酷壳 – CoolShell', 'https://picsum.photos/seed/coolshell/200/200', '2025-08-10 09:00:00'),
    (4, 'https://javaguide.cn/', 'JavaGuide', 'https://picsum.photos/seed/javaguide/200/200', '2025-09-05 16:00:00'),
    (5, 'https://www.hollischuang.com/', 'Hollis的博客', 'https://picsum.photos/seed/hollis/200/200', '2025-10-12 11:20:00'),
    (6, 'https://mp.weixin.qq.com/s/xxx', '沉默王二', 'https://picsum.photos/seed/wanger/200/200', '2025-11-28 13:45:00');
/*!40000 ALTER TABLE `t_friend` ENABLE KEYS */;

-- ----------------------------
-- 系统配置表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `t_settings` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '配置ID',
  `config_key` varchar(100) NOT NULL COMMENT '配置键名',
  `config_value` text COMMENT '配置值',
  `description` varchar(255) DEFAULT NULL COMMENT '配置说明',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_config_key` (`config_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统配置表';

INSERT IGNORE INTO `t_settings` (`config_key`, `config_value`, `description`) VALUES
('site.name', 'SpringBoot AI Blog', '网站名称'),
('site.description', '基于 Spring Boot + AI 的智能博客系统', '网站描述'),
('site.keywords', 'Spring Boot,Blog,AI,Java', '网站关键词'),
('site.logo', '', '网站Logo URL'),
('site.favicon', '', '网站图标 URL'),
('site.beian', '', '备案号'),
('site.footer', '© 2024 SpringBoot AI Blog. All rights reserved.', '页脚信息'),
('comment.enabled', 'true', '是否开启评论'),
('comment.audit', 'false', '评论是否需要审核'),
('ai.enabled', 'false', '是否启用AI功能'),
('ai.auto_summary', 'false', '是否自动生成摘要'),
('ai.auto_tags', 'false', '是否自动推荐标签');

-- ----------------------------
-- 操作日志表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `t_operation_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `user_id` int(11) DEFAULT NULL COMMENT '操作用户ID',
  `username` varchar(100) DEFAULT NULL COMMENT '操作用户名',
  `operation` varchar(255) DEFAULT NULL COMMENT '操作描述',
  `method` varchar(500) DEFAULT NULL COMMENT '请求方法',
  `params` text COMMENT '请求参数',
  `ip` varchar(50) DEFAULT NULL COMMENT 'IP地址',
  `duration` int(11) DEFAULT NULL COMMENT '执行时长(ms)',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态：0失败 1成功',
  `error_msg` text COMMENT '错误信息',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='操作日志表';

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
