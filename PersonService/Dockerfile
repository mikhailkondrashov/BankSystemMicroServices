FROM adoptopenjdk/openjdk8:latest
ADD /target/personservice-0.0.1-SNAPSHOT.jar /app/PersonService.jar
EXPOSE 8082
CMD ["java", "-jar", "/app/PersonService.jar"]