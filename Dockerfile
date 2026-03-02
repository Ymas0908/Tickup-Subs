# Étape 1 : Build Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copier uniquement le pom.xml pour cache des dépendances
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# Copier les modules et le reste du code
COPY . .

# Build multi-modules (skip tests pour rapidité)
RUN mvn clean package -DskipTests -B