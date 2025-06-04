FROM maven:3.8-openjdk-17 as builder

WORKDIR /app
# Copia directamente el POM del microservicio
COPY pom.xml /app/

# Copia el código del microservicio
COPY src /app/src/

# Configura repositorio central para resolver dependencias (incluyendo el parent POM)
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]