# Dockerfile for Books App (Spring Boot) using Java 25 and H2 in-memory
# This Dockerfile expects a built fat jar at target/booksapp-0.0.1-SNAPSHOT.jar
# Build the jar locally first with: mvn -DskipTests package

# Use a Java 25 runtime image
FROM eclipse-temurin:25-jre AS runtime

WORKDIR /app

# Copy the Spring Boot jar produced by mvn package
COPY target/booksapp-0.0.1-SNAPSHOT.jar /app/app.jar

# Configure H2 in-memory datasource (Spring Boot will pick these up if present)
ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:booksdb
ENV SPRING_DATASOURCE_USERNAME=sa
ENV SPRING_DATASOURCE_PASSWORD=password

# JVM tuning (adjust as needed)
ENV JAVA_OPTS="-Xms256m -Xmx512m"

EXPOSE 8080

# Start the app. Using sh -c to allow expansion of JAVA_OPTS
ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar /app/app.jar"]

