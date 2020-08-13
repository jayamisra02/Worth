FROM java:8-jdk-alpine
COPY --from=0 /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "calculator-0.0.1-SNAPSHOT.jar"]