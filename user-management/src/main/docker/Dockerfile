FROM openjdk:8

ADD maven/user-management-0.0.1-SNAPSHOT.jar user-management.jar
RUN sh -c 'touch /user-management.jar'
EXPOSE 8085
ENTRYPOINT ["java", "-Xmx50m", "-jar", "user-management.jar"]
