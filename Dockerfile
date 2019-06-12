FROM openjdk:8-jre
ADD target/time.jar app.jar
EXPOSE 8080
EXPOSE 9000
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
