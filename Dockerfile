FROM openjdk:17-jdk-alpine
COPY /target/*.jar healthwise.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "healthwise.jar"]