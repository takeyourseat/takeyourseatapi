FROM openjdk:8

ADD maven/application-logs-0.0.1-SNAPSHOT.jar application-logs-service.jar
RUN sh -c 'touch /application-logs-service.jar'
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "users-mysql.jar"]