FROM openjdk:17-jdk-slim
WORKDIR /app
ADD target/table-booking-api-1.0.0-SNAPSHOT.jar /app/table-booking-api.jar
ENTRYPOINT ["java", "-jar", "table-booking-api.jar"]