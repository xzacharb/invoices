FROM openjdk:8-jre-alpine
COPY target/auth-svc-0.0.1-SNAPSHOT.jar auth-svc.jar
ENTRYPOINT ["java", "-jar", "/auth-svc.jar"]