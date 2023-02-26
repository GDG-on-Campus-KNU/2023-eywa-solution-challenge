FROM openjdk:11-jdk-slim
CMD ["./gradlew build"]
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]