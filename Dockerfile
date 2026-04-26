# Stage 1: Build
FROM maven:3.9.9-eclipse-temurin-17 AS builder
WORKDIR /build

# Copy only project folder
COPY ecommerce /build

# Build the jar
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy jar from builder
COPY --from=builder /build/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]