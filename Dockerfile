# https://spring.io/guides/gs/spring-boot-docker/

FROM openjdk:8-jdk-alpine

# This is where a Spring Boot creates working directories for Tomcat by default
VOLUME /tmp

# Copy the build archive into the container
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.jahnelgroup.thymeleafstarter.AppKt"]