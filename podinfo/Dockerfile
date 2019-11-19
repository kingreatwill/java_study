# 基于Java8镜像
FROM openjdk:8u191-jre-alpine3.9

# 复制文件到容器
COPY target/podinfo-0.0.1-SNAPSHOT.jar /app.jar

# 声明需要暴露的端口
EXPOSE 9999
EXPOSE 8080

# 配置容器启动后执行的命令
ENTRYPOINT ["java","-jar","/app.jar"]

# docker build -t javapodinfo:0.1 .
# docker run --name javapodinfo -d -p 8080:8080 -p 9999:9999 javapodinfo:0.1
# docker tag javapodinfo:0.1 kingreatwill/javapodinfo:0.1
# docker push kingreatwill/javapodinfo:0.1
# docker rmi kingreatwill/javapodinfo:0.1
# docker rmi javapodinfo:0.1
# docker pull kingreatwill/javapodinfo:0.1
# docker run --name javapodinfo -d -p 8080:8080 -p 9999:9999 kingreatwill/javapodinfo:0.1


