FROM adoptopenjdk/openjdk8:latest
ADD /target/account-service-0.0.1-SNAPSHOT.jar /app/AccountService.jar
EXPOSE 8081
CMD ["java", "-jar", "/app/AccountService.jar"]