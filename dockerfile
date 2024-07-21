FROM openjdk:22-jdk-slim

WORKDIR /app

COPY gradlew /app/gradlew
COPY build.gradle /app/
COPY settings.gradle /app/

COPY gradle /app/gradle

COPY src /app/src

RUN chmod +x /app/gradlew

RUN ./gradlew build --no-daemon

EXPOSE 8080

CMD ["java", "-jar", "build/libs/forum-0.0.1-SNAPSHOT.jar"]
