FROM openjdk:8

ADD maven/application-logs-0.0.1-SNAPSHOT.jar application-logs.jar
RUN sh -c 'touch /application-logs.jar'
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "application-logs.jar"]