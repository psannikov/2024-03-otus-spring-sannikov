FROM bellsoft/liberica-openjdk-alpine:21.0.4-9
WORKDIR /usr/src/app
COPY /target/hw17-docker.jar .
EXPOSE 8080
CMD ["java", "-jar", "hw17-docker.jar"]