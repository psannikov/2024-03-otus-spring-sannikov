FROM bellsoft/liberica-openjdk-alpine:21.0.4-9
WORKDIR /usr/src/app
COPY /target/lite-task-tracker.jar .
EXPOSE 8080
CMD ["java", "-jar", "lite-task-tracker.jar"]