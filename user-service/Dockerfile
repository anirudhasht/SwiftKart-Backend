FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy the prebuilt JAR
COPY target/user-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
