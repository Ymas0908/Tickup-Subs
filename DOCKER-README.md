# Dockerisation de l'application Tickup

## 🐳 Architecture Docker

L'application est maintenant conteneurisée avec les services suivants :

### Services

- **app** : Application Spring Boot principale (port 9000)
- **mariadb** : Base de données MariaDB (port 3307)
- **mailhog** : Service de test d'emails (ports 1025/8025)

## 🚀 Démarrage rapide

### Windows
```bash
# Exécuter le script de démarrage
start-docker.bat
```

### Linux/Mac
```bash
# Rendre le script exécutable
chmod +x start-docker.sh

# Exécuter le script
./start-docker.sh
```

### Manuel
```bash
# Construire et démarrer
docker-compose up --build -d

# Voir les logs
docker-compose logs -f app
```

## 📱 Accès aux services

| Service | URL | Description |
|---------|-----|-------------|
| Application | http://localhost:9000 | Application principale |
| API Docs | http://localhost:9000/swagger-ui.html | Documentation Swagger |
| MailHog | http://localhost:8026 | Interface email de test |
| MariaDB | localhost:3307 | Base de données |

## ⚙️ Configuration

### Variables d'environnement

Les variables suivantes peuvent être configurées dans `docker-compose.yml` :

```yaml
environment:
  SPRING_DATASOURCE_URL: jdbc:mariadb://mariadb:3306/tickupDB
  SPRING_DATASOURCE_USERNAME: root
  SPRING_DATASOURCE_PASSWORD: root
  SPRING_PROFILES_ACTIVE: docker
```

### Configuration des emails

Le profil Docker utilise MailHog pour les tests d'emails :

```yaml
spring:
  mail:
    host: mailhog
    port: 1025
```

## 🔧 Commandes utiles

```bash
# Voir l'état des services
docker-compose ps

# Voir les logs en temps réel
docker-compose logs -f app

# Redémarrer un service
docker-compose restart app

# Arrêter tous les services
docker-compose down

# Reconstruire l'image
docker-compose build --no-cache app

# Accéder au container de l'application
docker-compose exec app sh

# Accéder à la base de données
docker-compose exec mariadb mysql -u root -p tickupDB
```

## 📁 Structure des fichiers

```
├── docker-compose.yml          # Configuration Docker Compose
├── Dockerfile                  # Configuration de l'image
├── .dockerignore              # Fichiers ignorés par Docker
├── start-docker.sh            # Script de démarrage (Linux/Mac)
├── start-docker.bat           # Script de démarrage (Windows)
└── adapters/src/main/resources/
    └── application-docker.yml # Configuration Spring pour Docker
```

## 🛠️ Résolution des problèmes

### Port déjà utilisé

Si le port 9000 est déjà utilisé :

```bash
# Trouver le processus utilisant le port
netstat -ano | findstr :9000

# Arrêter le processus (remplacer PID par le numéro trouvé)
taskkill /F /PID <PID>

# Ou changer le port dans docker-compose.yml
ports:
  - "9001:9000"  # Utiliser le port 9001 au lieu de 9000
```

### Problèmes de mémoire

Si vous rencontrez des problèmes de mémoire :

```yaml
# Dans docker-compose.yml
services:
  app:
    environment:
      - JAVA_OPTS=-Xmx256m -Xms128m
```

### Logs de débogage

Pour activer les logs de débogage :

```bash
# Modifier application-docker.yml
logging:
  level:
    org.tickup: DEBUG
    org.springframework.web: DEBUG
```

## 🔄 Développement

Pour le développement local, vous pouvez utiliser le profil `dev` :

```bash
# Démarrer avec le profil de développement
docker-compose -f docker-compose.yml -f docker-compose.dev.yml up
```

## 📊 Monitoring

Pour monitorer les ressources utilisées :

```bash
# Voir l'utilisation des ressources
docker stats

# Voir l'utilisation du disque
docker system df
```

## 🧹 Nettoyage

Pour nettoyer les ressources Docker :

```bash
# Arrêter et supprimer les containers
docker-compose down -v

# Supprimer les images non utilisées
docker image prune -f

# Supprimer les volumes non utilisés
docker volume prune -f
```
