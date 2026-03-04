# Test de Génération de Ticket et Envoi par Mail

## Étapes pour tester l'API

### 1. Démarrer l'application
```bash
# Compiler le projet
mvn clean install -DskipTests

# Démarrer l'application
mvn spring-boot:run
```

### 2. Tester l'API avec curl ou Postman

#### Test 1: Festival de Musique
```bash
curl -X POST http://localhost:8080/api/v1/tickets/generate \
  -H "Content-Type: application/json" \
  -d '{
    "eventName": "Festival de Musique 2026",
    "participantName": "Jean Dupont",
    "ticketNumber": "TKT-2026-001",
    "eventDate": "15 Mars 2026 - 20:00",
    "location": "Palais des Congrès, Abidjan",
    "category": "VIP",
    "price": 15000.00,
    "email": "jean.dupont@example.com"
  }'
```

#### Test 2: Conférence Tech
```bash
curl -X POST http://localhost:8080/api/v1/tickets/generate \
  -H "Content-Type: application/json" \
  -d '{
    "eventName": "Conférence Tech 2026",
    "participantName": "Marie Koné",
    "ticketNumber": "TKT-2026-002",
    "eventDate": "20 Mars 2026 - 09:00",
    "location": "Centre de Conférence, Cocody",
    "category": "Standard",
    "price": 7500.00,
    "email": "marie.kone@example.com"
  }'
```

### 3. Vérifications

#### Vérifier les logs de l'application
- Le ticket PDF devrait être généré
- L'email devrait être envoyé via Mailtrap
- Les logs devraient montrer le succès ou l'erreur

#### Vérifier la boîte Mailtrap
- Se connecter à https://mailtrap.io
- Vérifier l'inbox pour les emails de test
- Confirmer que le contenu HTML est correct

### 4. Résultats attendus

#### Succès
```json
{
  "message": "Ticket généré et envoyé avec succès à jean.dupont@example.com"
}
```

#### Erreur
```json
{
  "message": "Erreur: [détail de l'erreur]"
}
```

### 5. Débogage

Si l'email n'est pas envoyé, vérifier:
1. Configuration Mailtrap dans `application.properties`
2. Logs de l'application pour les erreurs SMTP
3. Connectivité réseau

Si le ticket n'est pas généré, vérifier:
1. Dépendances iText et ZXing dans les POM
2. Logs pour les erreurs de génération PDF
3. Permissions du système de fichiers

### 6. Test avec des données réelles

Pour tester avec des données réelles:
1. Remplacer l'email par votre adresse email de test
2. Utiliser des événements et participants réels
3. Vérifier la réception de l'email

## Configuration requise

- Spring Boot application démarrée sur port 8080
- Mailtrap configuré avec les credentials
- Base de données MariaDB accessible
- Java 17+ installé
