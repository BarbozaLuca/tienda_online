FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/server-producto-0.0.1.jar
COPY ${JAR_FILE} app_producto.jar
ENTRYPOINT ["java", "-jar", "app_producto.jar"]