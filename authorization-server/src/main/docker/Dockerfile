# Start with a base image containing Java runtime (mine java 8)
FROM openjdk:8

# Add the application's jar to the container
ADD maven/authorization-server-0.0.1-SNAPSHOT.jar authorization-server.jar

# Download and install a dependency
RUN sh -c 'touch /authorization-server.jar'

# Make port 8086 available to the world outside this container
EXPOSE 8086

# Run the jar file
ENTRYPOINT ["java", "-jar", "authorization-server.jar"]