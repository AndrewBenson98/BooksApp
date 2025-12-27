# ============================
# 1. Build Stage
# ============================
FROM eclipse-temurin:25-jdk AS build

WORKDIR /app

# Copy Maven descriptor first (better caching)
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Download dependencies (cached unless pom.xml changes)
RUN ./mvnw dependency:go-offline

# Copy source and build the application
COPY src src
RUN ./mvnw clean package -DskipTests

# ============================
# 2. Runtime Stage
# ============================
FROM eclipse-temurin:25-jre

WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your Spring Boot app uses
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
