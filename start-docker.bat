@echo off
REM Script de démarrage pour l'application Tickup avec Docker (Windows)

echo 🚀 Démarrage de l'application Tickup...

REM Vérifier si Docker est en cours d'exécution
docker info >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Docker n'est pas en cours d'exécution. Veuillez démarrer Docker Desktop d'abord.
    pause
    exit /b 1
)

REM Arrêter les containers existants
echo 🛑 Arrêt des containers existants...
docker-compose down

REM Construire et démarrer les services
echo 🔨 Construction et démarrage des services...
docker-compose up --build -d

REM Attendre que la base de données soit prête
echo ⏳ Attente du démarrage de la base de données...
timeout /t 10 /nobreak >nul

REM Vérifier l'état des services
echo 📊 État des services:
docker-compose ps

echo.
echo ✅ Application démarrée avec succès!
echo.
echo 📱 URLs d'accès:
echo    - Application principale: http://localhost:9000
echo    - Documentation API: http://localhost:9000/swagger-ui.html
echo    - Interface MailHog: http://localhost:8026
echo    - Base de données: localhost:3307
echo.
echo 📋 Commandes utiles:
echo    - Voir les logs: docker-compose logs -f app
echo    - Arrêter: docker-compose down
echo    - Redémarrer: docker-compose restart
echo.
pause
