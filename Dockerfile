# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and the source code
COPY pom.xml .
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Use a smaller base image for the final stage
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built jar file from the build stage
COPY --from=build /app/target/gestion-station-ski-1.0.jar app.jar

# Expose the application port
EXPOSE 9090

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
