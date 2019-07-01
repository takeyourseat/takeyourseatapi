FROM openjdk:8

ADD maven/oauth-server-0.0.1-SNAPSHOT.jar oauth-server.jar
RUN sh -c 'touch /oauth-server.jar'
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "oauth-server.jar"]