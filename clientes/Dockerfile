FROM openjdk:17-jdk-slim as build
MAINTAINER jcabelloc@gmail.com

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

# 2da Etapa
FROM openjdk:17-jdk-slim

VOLUME /tmp

# Copy unpackaged application to new container
ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# Ejecutar la aplicacion
ENTRYPOINT ["java","-cp","app:app/lib/*","com.abc.clientes.ClientesApplication"]
