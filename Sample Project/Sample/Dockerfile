FROM maven:3.8-openjdk-17
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
#COPY target/springAppforDockerWithMysql-0.0.1-SNAPSHOT.jar app.jar
COPY --from=0 /app/target/Sample-0.0.1-SNAPSHOT.jar /app/myapp.jar
#EXPOSE 8080
ENTRYPOINT ["java", "-jar", "myapp.jar"]
