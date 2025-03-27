# Stage 1: Build with Maven
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Create non-root user
RUN addgroup -S spring && adduser -S spring -G spring
USER spring

# Copy built JAR (with proper ownership)
COPY --from=builder --chown=spring:spring /build/target/spring-boot-crud-example-2-*.jar app.jar

# Health check (using wget available in alpine)
HEALTHCHECK --interval=30s --timeout=10s --retries=3 \
    CMD wget -qO- http://localhost:9191/health || exit 1

# Runtime config (override via docker-compose/env files)
ENV SPRING_DATASOURCE_URL="jdbc:mysql://mysql-container:3306/javatechie" \
    SPRING_DATASOURCE_USERNAME="appuser" \
    SPRING_PROFILES_ACTIVE="prod"

ENTRYPOINT ["java", "-jar", "app.jar"]