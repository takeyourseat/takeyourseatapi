FROM openjdk:8
ADD target/oauth-service.jar oauth-service.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "oauth-service.jar"]