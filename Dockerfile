FROM adoptopenjdk/openjdk11-openj9
ARG JAR_FILE=target/testDemoSpringPostgres-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]