FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY Course_work-0.0.1-SNAPSHOT.jar  my-spring-app.jar

EXPOSE 8080

CMD ["java", "-jar", "my-spring-app.jar"]