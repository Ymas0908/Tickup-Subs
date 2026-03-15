# Étape 1 : Build Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copier les fichiers de configuration Maven en premier pour optimiser le cache
COPY pom.xml .
COPY domain/pom.xml ./domain/
COPY adapters/pom.xml ./adapters/

# Télécharger les dépendances (mise en cache)
RUN mvn dependency:go-offline -B

# Copier les fichiers sources
COPY domain ./domain/
COPY adapters ./adapters/

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

# Créer un utilisateur non-root pour la sécurité
RUN addgroup -g 1001 -S spring && \
    adduser -u 1001 -S spring -G spring

WORKDIR /app

# Installer les utilitaires nécessaires
RUN apk add --no-cache tzdata

# Définir le fuseau horaire
ENV TZ=Europe/Paris

# Copier le JAR construit depuis l'étape de build
COPY --from=build /app/adapters/target/*.jar app.jar

# Créer le répertoire de logs
RUN mkdir -p /app/logs && \
    chown -R spring:spring /app

# Changer vers l'utilisateur non-root
USER spring

# Exposer le port
EXPOSE 9000

# Variables d'environnement pour l'application
ENV JAVA_OPTS="-Xmx512m -Xms256m -XX:+UseG1GC -XX:+UseContainerSupport"

# Lancer l'application avec des options optimisées
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
