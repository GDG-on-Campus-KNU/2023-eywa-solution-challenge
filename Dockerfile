FROM openjdk:11-jdk-slim
CMD ["./gradlew build"]
COPY build/libs/*-SNAP.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]