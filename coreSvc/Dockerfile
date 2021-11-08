# Build the application first using Maven
FROM maven:3.8-openjdk-11 as build
WORKDIR /app
COPY . .
RUN mvn install

# Inject the JAR file into a new container to keep the file small
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/target/core-svc-0.0.1-SNAPSHOT.jar core-svc.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar core-svc.jar"]