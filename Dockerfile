# Use a base image with Java and Maven pre-installed
FROM maven:3.8.4-openjdk-17 AS builder

# Set the working directory
WORKDIR /app

# Copy the pom.xml and build dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the application
RUN mvn clean package

# Use an official OpenJDK runtime as the base image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container at /app
COPY --from=builder /app/target/iss-places-of-interest-0.0.1-SNAPSHOT.jar /app

# Specify the command to run your application when the container starts
CMD ["java", "-jar", "iss-places-of-interest-0.0.1-SNAPSHOT.jar"]
