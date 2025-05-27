# Usar una imagen base de Maven para construir la aplicación
FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar los archivos del proyecto
COPY . .

# Construir la aplicación Spring Boot
RUN mvn clean package -DskipTests

# Usar una imagen ligera de Java para ejecutar la aplicación
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copiar el JAR generado desde la fase de compilación
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto de la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]