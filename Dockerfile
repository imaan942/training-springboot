FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/fleet-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

CMD ["java", "-jar", "app.jar"]