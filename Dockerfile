# 用jdk容器执行这个程序
FROM openjdk:11

# 作者
MAINTAINER coder_ryan <1173779946@qq.com>

# VOLUME 指定了临时文件目录为/tmp。
# 其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
VOLUME /tmp

# 将可执行的jar包放到容器当中去
ADD target/blog-1.0.0.jar app.jar

# 暴露服务端口
EXPOSE 8080

# 暴露日志目录，Java程序运行的错误日志就在这个里面
VOLUME '/logs'

# JVM 调优参数
ENV JAVA_OPTION="-Dfile.encoding=UTF-8 -Xmx512m -Xms512m -Xmn256m -XX:+UseParallelGC -XX:+PrintGCDetails -XX:+PrintGCCause -XX:+PrintHeapAtGC -Xloggc:/logs/blog.gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:+DisableExplicitGC"

# 运行程序
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar $JAVA_OPTION /app.jar"]
