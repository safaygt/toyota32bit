FROM openjdk:17-jdk-slim

COPY ./backendproject/backendproject/target/backendproject-0.0.1-SNAPSHOT.jar /app/backendproject-0.0.1-SNAPSHOT.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "/app/backendproject-0.0.1-SNAPSHOT.jar"]
