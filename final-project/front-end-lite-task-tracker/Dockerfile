FROM bellsoft/liberica-openjdk-alpine:21.0.4-9
WORKDIR /usr/src/app
COPY /target/front-end-lite-task-tracker.jar .
EXPOSE 8081
CMD ["java", "-jar", "front-end-lite-task-tracker.jar"]