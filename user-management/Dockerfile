FROM openjdk:8
ADD target/user-management-0.0.1-SNAPSHOT.jar user-management.jar
EXPOSE 8085
ENTRYPOINT ["java", "-Xmx50m", "-jar", "user-management.jar"]
