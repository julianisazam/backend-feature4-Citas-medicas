FROM maven:3.8-openjdk-17 as builder
WORKDIR /app
COPY . /app
# Construir sin la dependencia del POM padre
RUN mvn clean package -DskipTests -Dspring.boot.version=3.5.0 -Dmaven.test.skip=true

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
