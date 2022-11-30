FROM maven:3.8.5-openjdk-17 AS build
COPY /src /home/app/src
COPY /pom.xml /home/app
COPY pom.xml /home
COPY .env /home/app/src
RUN --mount=type=cache,target=/root/.m2 mvn -f /home/app/pom.xml clean install -Dmaven.test.skip

FROM openjdk:17
ARG JAR_FILE=/home/app/target/*.jar
COPY --from=build ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar"]
HEALTHCHECK --interval=45s --start-period=30s --retries=5 CMD curl -sf 'http://localhost:8080/actuator/health' >/dev/null || exit 1