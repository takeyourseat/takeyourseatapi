FROM openjdk:8

ADD maven/authorization-server-0.0.1-SNAPSHOT.jar authorization-server.jar
RUN sh -c 'touch /authorization-server.jar'
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "authorization-server.jar"]