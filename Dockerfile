
FROM openjdk:17
WORKDIR /
CMD ["./gradlew", "clean", "bootJar"]
COPY build/libs/*.jar app.jar
ADD main-taxis.csv /
ADD main-customers.csv /

EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://your-mongodb:27017/db-name", "-jar", "/app.jar"]