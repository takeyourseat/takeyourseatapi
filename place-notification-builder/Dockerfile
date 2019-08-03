#This file is used for basic docker build image - with comand "docker build -t image.name ."

FROM openjdk:8
ADD target/place-notification-builder-0.0.1-SNAPSHOT.jar place-notification-builder.jar
EXPOSE 8088
ENTRYPOINT ["java", "-Xmx50m", "-jar", "place-notification-builder.jar"]
