#This file is used for basic docker build image - with comand "docker build -t image.name ."

FROM openjdk:8
ADD target//authorization-server-0.0.1-SNAPSHOT.jar authorization-server.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "authorization-server.jar"]