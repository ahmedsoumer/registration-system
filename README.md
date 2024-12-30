# Système d'Inscription

## Aperçu

Le **Système d'Inscription** se compose de deux microservices :

1. **Service de Configuration** - Gère les configurations pour la génération des numéros d'inscription.
2. **Service de Numérotation** - Génère des numéros d'inscription en fonction des configurations fournies.

Ce système permet aux utilisateurs de générer des numéros d'inscription formatés basés sur des configurations prédéfinies telles que le prénom, le nom de famille, la date de naissance et le compteur.

## Fonctionnalités

- **Service de Configuration** :
  - Sauvegarder et récupérer des configurations.
  - Fournir une configuration par défaut.
  
- **Service de Numérotation** :
  - Générer des numéros d'inscription basés sur les informations de l'utilisateur (prénom, nom de famille, date de naissance, compteur) et un identifiant de configuration.

## Technologies Utilisées

- **Spring Boot** pour la création des microservices.
- **Spring Web** pour les API RESTful.
- **Spring Data JPA** pour gérer les interactions avec la base de données.
- **OpenAPI** pour la documentation des API.
- **GitHub** pour le contrôle de version.

## Architecture microservice

- **Service de Configuration** :
  - Gère la sauvegarde et la récupération des configurations.
  - Fournit une configuration par défaut.
  
- **Service de Numérotation** :
  - Génère un numéro d'inscription formaté en fonction des données utilisateur et de l'ID de configuration.

## Démarrage

### Prérequis

Assurez-vous d'avoir les outils suivants installés :

- JDK 17 ou supérieur
- Maven ou Gradle
- Docker (facultatif, pour la containerisation)
- Postman (pour tester les points de terminaison de l'API)

### Configuration

1. Clonez le dépôt :
   ```bash
   git clone https://github.com/ahmedsoumer/registration-system.git
   cd registration-system
