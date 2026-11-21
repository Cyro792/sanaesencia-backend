# Etapa 1: Compilación
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Runtime)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENV ORACLE_WALLET_PATH=/app/wallet
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
