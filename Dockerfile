# Use Eclipse Temurin Java 17 (works on Render)
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy maven wrapper and source code
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# Make mvnw executable
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
