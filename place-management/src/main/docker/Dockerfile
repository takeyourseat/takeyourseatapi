FROM openjdk:8

ADD maven/place-management-0.0.1-SNAPSHOT.jar place-management.jar
RUN sh -c 'touch /place-management.jar'
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "place-management.jar"]