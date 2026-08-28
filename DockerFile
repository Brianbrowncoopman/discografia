# Stage 1: Construcción
FROM gradle:8.5-jdk21 AS builder

WORKDIR /app

# Copiar archivos de Gradle y código fuente
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src ./src

# Dar permisos al wrapper y compilar el fat-jar sin tests para agilizar el build
RUN chmod +x ./gradlew
RUN ./gradlew bootJar -x test --no-daemon

# Stage 2: Ejecución
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copiar el JAR generado desde la carpeta build/libs/
COPY --from=builder /app/build/libs/discografia-1.jar app.jar

# Render asigna el puerto mediante la variable PORT
ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]


# ojo DockerFile 1= Dockerfile