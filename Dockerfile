# Dockerfile (place in each microservice folder)
# FROM openjdk:21-jdk-alpine
FROM openjdk:21-slim

WORKDIR /app
COPY target/*.jar app.jar
# You can override this port in docker-compose.yml
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
