FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/server-CarritoDeC-0.0.1.jar
COPY ${JAR_FILE} app_carrito.jar
ENTRYPOINT ["java", "-jar", "app_carrito.jar"]