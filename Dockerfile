# Start from a base image with JDK
FROM openjdk:11-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven wrapper files (if used)
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .

# Copy the source code into the container
COPY src/ ./src

# Make the Maven wrapper executable
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose the port that your application will run on
EXPOSE 9090

# Specify the command to run the JAR file
CMD ["java", "-jar", "target/gestion-station-ski-1.0.jar"]
