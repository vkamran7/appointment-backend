FROM maven:3.6.1-jdk-8-alpine as MAVEN_BUILD

COPY ./ ./

RUN mvn clean package


FROM openjdk:8-jre-alpine3.9

COPY --from=MAVEN_BUILD /target/appointment-0.0.1-SNAPSHOT.jar /appointment-app.jar

CMD ["java", "-jar", "/appointment-app.jar"]