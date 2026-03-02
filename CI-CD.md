# Configuration CI/CD pour Tickup-Subs

## GitHub Actions configuré

Le pipeline CI/CD est maintenant configuré avec GitHub Actions dans `.github/workflows/ci-cd.yml`.

## Étapes du pipeline

### 1. **Test** (sur toutes les branches)
- Crée une base de données MariaDB temporaire
- Compile le projet avec JDK 17
- Installe les thèmes Poseidon locaux
- Exécute les tests unitaires

### 2. **Build & Deploy** (seulement sur main)
- Build l'image Docker
- Push sur Docker Hub

### 3. **Deploy** (seulement sur main)
- Déploie sur le serveur de production via SSH

## Secrets GitHub à configurer

Dans votre repository GitHub > Settings > Secrets and variables > Actions:

- `DOCKER_USERNAME`: Votre nom d'utilisateur Docker Hub
- `DOCKER_PASSWORD`: Votre mot de passe Docker Hub ou token d'accès
- `HOST`: Adresse IP de votre serveur de production
- `USERNAME`: Nom d'utilisateur SSH sur le serveur
- `SSH_KEY`: Clé SSH privée pour se connecter au serveur

## Configuration du serveur de production

Sur votre serveur de production:

```bash
# Créer le répertoire du projet
sudo mkdir -p /opt/tickup-subs
cd /opt/tickup-subs

# Copier le docker-compose.yml
# (Assurez-vous que le mot de passe est correct)
```

## Déploiement automatique

- **Push sur `develop`**: Tests uniquement
- **Push sur `main`**: Tests + Build + Déploiement automatique
- **Pull Request**: Tests uniquement

## Pour activer le CI/CD

1. Poussez votre code sur GitHub
2. Configurez les secrets GitHub
3. Le pipeline s'exécutera automatiquement à chaque push
