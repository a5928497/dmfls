# VERSION 0.0.1
# Author: lizili
# 基础镜像使用openjdk
FROM openjdk:8-jdk-alpine
# 作者
MAINTAINER lizili <lizili@qq.com>
RUN echo "Asia/Shanghai" > /etc/timezone
# VOLUME 指定了临时文件目录为/tmp。
# 其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
# VOLUME /tmp
# 创建容器后工作目录
ENV spring.profiles.active="prod"
ENV WORKSPACE /data/
WORKDIR $WORKSPACE
# 创建工作目录
RUN mkdir -p $WORKSPACE
# 将jar包添加到容器中并更名为app.jar
ADD dmfls.jar /app.jar
# 容器暴露端口
EXPOSE 80
# 容器启动时要运行的命令
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
