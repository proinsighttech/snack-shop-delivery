FROM maven:3.9.6-eclipse-temurin-21-alpine

#WORKDIR /app

# image layer
COPY pom.xml /app/

# Image layer: with the application
COPY . /app
RUN mvn clean install

#ARG JAR_FILE
ARG JAR_FILE=snack-shop-api-0.0.1-SNAPSHOT.jar

COPY target/${JAR_FILE} /app/api.jar

COPY ./wait-for-it.sh /app/wait-for-it.sh 

RUN chmod +x /app/wait-for-it.sh

EXPOSE 9000

CMD ["./wait-for-it.sh", "snack-shop-mysql:3306", "-t", "45", "--", "java", "-jar", "api.jar"]


FROM maven:3.9.6-eclipse-temurin-21-alpine AS MAVEN_BUILD
COPY pom.xml /build/
COPY . /build/
WORKDIR /build/
RUN pwd
RUN ls -a
RUN mvn clean install -DskipTests


FROM eclipse-temurin:21.0.2_13-jre-alpine
WORKDIR /app
RUN pwd
EXPOSE 9000
COPY --from=MAVEN_BUILD /build/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]