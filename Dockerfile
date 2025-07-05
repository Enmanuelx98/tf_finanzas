# Etapa 1: Build
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/backend.jar /backend.jar
ENTRYPOINT ["java", "-jar", "/backend.jar"]