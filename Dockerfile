# Étape 1 : Build Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copier tous les fichiers du projet
COPY . .

# Installer les JAR locaux du thème Poseidon dans le repository Maven local
RUN mvn install:install-file \
    -Dfile=poseidon-theme-6.0.0-jakarta.jar \
    -DgroupId=org.primefaces.themes \
    -DartifactId=poseidon-jakarta \
    -Dversion=6.0.0 \
    -Dpackaging=jar

RUN mvn install:install-file \
    -Dfile=poseidon-theme-6.0.0.jar \
    -DgroupId=org.primefaces.themes \
    -DartifactId=poseidon \
    -Dversion=6.0.0 \
    -Dpackaging=jar

# Build multi-modules (skip tests pour rapidité)
RUN mvn clean package -DskipTests -B

# Étape 2 : Image d'exécution
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copier le JAR construit depuis l'étape de build
COPY --from=build /app/adapters/target/*.jar app.jar

# Exposer le port
EXPOSE 9000

# Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
