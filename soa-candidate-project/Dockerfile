# Stage 1: Build Spring Boot application
FROM eclipse-temurin:21-jdk-jammy AS build

WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Copy source code
COPY src ./src

# Fix line endings and give executable permission to Maven wrapper
RUN sed -i 's/\r$//' mvnw && chmod +x mvnw

# Build application
RUN ./mvnw clean package -DskipTests


# Stage 2: Run Spring Boot application
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

EXPOSE 2026

# Copy generated JAR file
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

