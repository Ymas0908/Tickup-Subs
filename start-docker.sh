#!/bin/bash

# Script de démarrage pour l'application Tickup avec Docker

echo "🚀 Démarrage de l'application Tickup..."

# Vérifier si Docker est en cours d'exécution
if ! docker info > /dev/null 2>&1; then
    echo "❌ Docker n'est pas en cours d'exécution. Veuillez démarrer Docker d'abord."
    exit 1
fi

# Arrêter les containers existants
echo "🛑 Arrêt des containers existants..."
docker-compose down

# Construire et démarrer les services
echo "🔨 Construction et démarrage des services..."
docker-compose up --build -d

# Attendre que la base de données soit prête
echo "⏳ Attente du démarrage de la base de données..."
sleep 10

# Vérifier l'état des services
echo "📊 État des services:"
docker-compose ps

echo ""
echo "✅ Application démarrée avec succès!"
echo ""
echo "📱 URLs d'accès:"
echo "   - Application principale: http://localhost:9000"
echo "   - Documentation API: http://localhost:9000/swagger-ui.html"
echo "   - Interface MailHog: http://localhost:8026"
echo "   - Base de données: localhost:3307"
echo ""
echo "📋 Commandes utiles:"
echo "   - Voir les logs: docker-compose logs -f app"
echo "   - Arrêter: docker-compose down"
echo "   - Redémarrer: docker-compose restart"
echo ""
