#This file is used for fafbric8 pluggin -  with comand "mvn clean install docker:build"

FROM openjdk:8

ADD target/notification-server-0.0.1-SNAPSHOT.jar notification-server.jar
RUN sh -c 'touch /notification-server.jar'
EXPOSE 8087
ENTRYPOINT ["java", "-Xmx50m", "-jar", "notification-server.jar"]
