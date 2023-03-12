FROM openjdk:17-jdk-slim-buster
EXPOSE 8080
ADD target/spring-boot1-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]