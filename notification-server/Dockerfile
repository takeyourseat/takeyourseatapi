#This file is used for basic docker build image - with comand "docker build -t image.name ."

FROM openjdk:8
ADD target/notification-server-0.0.1-SNAPSHOT.jar notification-server.jar
EXPOSE 8087
ENTRYPOINT ["java", "-Xmx50m", "-jar", "notification-server.jar"]
