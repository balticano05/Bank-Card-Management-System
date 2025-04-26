FROM maven:3.9.6-sapmachine-21 AS build

RUN mvn clean package -DskipTests

FROM openjdk:21-jdk

COPY --from=build target/bank-card-management-system-0.0.1-SNAPSHOT.jar ./backend.jar

ENTRYPOINT ["java", "-jar", "backend.jar"]