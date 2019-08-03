#This file is used for fafbric8 pluggin -  with comand "mvn clean install docker:build"

FROM openjdk:8

ADD maven/place-notification-builder-0.0.1-SNAPSHOT.jar place-notification-builder.jar
RUN sh -c 'touch /place-notification-builder.jar'
EXPOSE 8088
ENTRYPOINT ["java", "-Xmx50m", "-jar", "place-notification-builder.jar"]
