# Use an official OpenJDK runtime as the base image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container at /app
COPY target/iss-places-of-interest-0.0.1-SNAPSHOT.jar /app

# Specify the command to run your application when the container starts
CMD ["java", "-jar", "iss-places-of-interest-0.0.1-SNAPSHOT.jar"]
