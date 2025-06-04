FROM maven:3.8-openjdk-17 as builder

# Copia primero el POM padre
WORKDIR /app
COPY ../pom.xml .

# Copia el módulo del servicio
WORKDIR /app/maintenance-alerts-service
COPY ./maintenance-alerts-service/pom.xml .
COPY ./maintenance-alerts-service/src ./src

# Compila el proyecto
RUN cd /app && mvn clean package -DskipTests -pl maintenance-alerts-service

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/maintenance-alerts-service/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]