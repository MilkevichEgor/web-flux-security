FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /home/app
COPY . .
RUN ./gradlew build -x test

FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /home/app/build/libs /app/libs
EXPOSE 8083
CMD ["java", "-jar", "/app/libs/webfluxsecurity-0.0.1-SNAPSHOT.jar"]