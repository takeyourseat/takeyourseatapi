FROM openjdk:8

ADD target/place-management-0.0.1-SNAPSHOT.jar place-management.jar
#RUN sh -c 'touch /place-management.jar'
EXPOSE 8084
ENTRYPOINT ["java", "-Xmx50m", x"-jar", "place-management.jar"]
