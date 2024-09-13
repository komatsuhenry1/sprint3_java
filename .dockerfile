# Etapa 1: Construir o artefato com Maven ou Gradle
# Escolha a imagem base conforme o seu sistema de construção (Maven ou Gradle)
FROM maven:3.8.6-openjdk-17 AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo pom.xml e o diretório src para o contêiner
COPY pom.xml .
COPY src ./src

# Executa o comando para construir o artefato
RUN mvn clean package -DskipTests

# Etapa 2: Criar a imagem final com a aplicação
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho
WORKDIR /app

# Copia o artefato construído da etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Exponha a porta em que a aplicação irá rodar
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
