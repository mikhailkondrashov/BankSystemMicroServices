FROM adoptopenjdk/openjdk8:latest
ADD /target/common-service-0.0.1-SNAPSHOT.jar /app/CommonService.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/CommonService.jar"]