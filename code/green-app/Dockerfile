# builder - first stage to build the application
FROM maven:3.8.6-openjdk-11-slim AS build-env

ARG VERSION=1.0.0

ADD ./pom.xml pom.xml
ADD ./src src/
RUN mvn versions:set -DnewVersion=$VERSION
RUN mvn clean package

# runtime - build final runtime image
FROM openjdk:11-jre-slim

ENV PORT=8080

# add debugging utilities
RUN apt-get update && apt-get install -y --no-install-recommends \
    ca-certificates \
    curl \
    jq \
    less \
    vim \
	&& rm -rf /var/lib/apt/lists/*

# add the application's jar to the container
COPY --from=build-env target/*.jar /apps/app.jar

# run application
EXPOSE $PORT
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.cloud.kubernetes.secrets.enabled=true", "-Dspring.cloud.kubernetes.secrets.paths=/secrets", "-jar","/apps/app.jar"]% 