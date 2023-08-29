FROM openjdk:17.0.2-jdk

WORKDIR /app
ADD bin/xmall-auth-api.jar app.jar
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
