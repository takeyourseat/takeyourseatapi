#This file is used for fafbric8 pluggin -  with comand "mvn clean install docker:build"

FROM openjdk:8

ADD maven/authorization-server-0.0.1-SNAPSHOT.jar authorization-server.jar
RUN sh -c 'touch /authorization-server.jar'
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "authorization-server.jar"]