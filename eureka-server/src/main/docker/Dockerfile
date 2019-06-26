FROM openjdk:8

ADD maven/eureka-server-0.0.1-SNAPSHOT.jar eureka-server.jar
RUN sh -c 'touch /eureka-server.jar'
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "eureka-server.jar"]