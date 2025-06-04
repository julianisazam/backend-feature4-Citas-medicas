FROM maven:3.8-openjdk-17 as builder

WORKDIR /app
# Copia primero el POM padre (asumiendo que estás ejecutando docker build desde el directorio raíz del proyecto)
COPY pom.xml /app/

# Copia el código del servicio
COPY maintenance-alerts-service/pom.xml /app/maintenance-alerts-service/
COPY maintenance-alerts-service/src /app/maintenance-alerts-service/src/

# Compila el proyecto
RUN cd /app && mvn clean package -DskipTests -pl maintenance-alerts-service

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/maintenance-alerts-service/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]