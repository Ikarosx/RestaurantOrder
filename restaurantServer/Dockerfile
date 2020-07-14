FROM ikarosx.cn:15000/jdk1.8
ENTRYPOINT ["java", "-jar", "/app.jar"]
ARG JAR_FILE
ADD ${JAR_FILE} /app.jar
EXPOSE 80