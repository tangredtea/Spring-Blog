# SpringBoot AI Blog - Docker 镜像
FROM openjdk:11-jre-slim

# 作者信息
LABEL maintainer="tangredtea <tangredtea@gmail.com>"
LABEL description="AI驱动的智能博客系统 - Spring Boot + MyBatis + AI集成"

# 设置时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 创建工作目录
WORKDIR /app

# 将可执行的 jar 包放到容器中
COPY target/blog-1.0.0.jar app.jar

# 暴露服务端口
EXPOSE 8080

# 创建日志目录
RUN mkdir -p /logs
VOLUME ["/logs"]

# 环境变量配置
ENV JAVA_OPTS="-Dfile.encoding=UTF-8 -Duser.timezone=Asia/Shanghai"
ENV JVM_OPTS="-Xmx512m -Xms512m -Xmn256m -XX:+UseG1GC"
ENV APP_OPTS=""

# AI 配置（可选）
ENV AI_API_KEY=""
ENV AI_API_URL="https://api.openai.com/v1/chat/completions"
ENV AI_MODEL="gpt-3.5-turbo"

# 数据库配置（运行时通过环境变量传入）
ENV DB_HOST="mysql"
ENV DB_PORT="3306"
ENV DB_NAME="blog"
ENV DB_USERNAME="root"
ENV DB_PASSWORD=""
ENV REDIS_HOST="redis"
ENV REDIS_PORT="6379"
ENV REDIS_PASSWORD=""

# 健康检查
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# 运行程序
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS $JVM_OPTS $APP_OPTS -jar app.jar"]
