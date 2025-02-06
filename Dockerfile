FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} bbus-be.jar

ENTRYPOINT ["java", "-jar", "bbus-be.jar"]

EXPOSE 8080

