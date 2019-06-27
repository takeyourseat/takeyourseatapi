FROM openjdk:8

ADD target/oauth-server-0.0.1-SNAPSHOT.jar oauth-server.jar
#RUN sh -c 'touch /oauth-server.jar'
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "oauth-server.jar"]