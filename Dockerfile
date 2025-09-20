# Use an official Java 17 runtime
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper files and the pom file
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Copy the rest of your application's source code
COPY src ./src

# Build the application using the Maven wrapper
RUN ./mvnw package -DskipTests

# Expose the port your app runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java","-jar","target/nidhiconnect-backend-0.0.1-SNAPSHOT.jar"]