FROM maven:3.6.0-jdk-12-alpine as builder
COPY ./ /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests=true

FROM openjdk:8-jre
COPY --from=builder /usr/src/app/target/time.jar app.jar
EXPOSE 8080
EXPOSE 9000
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
