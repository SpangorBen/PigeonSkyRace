# Pigeon Sky Race - Application de Gestion des Compétitions

## Contexte du Projet

Dans le cadre de la **compétition annuelle** organisée par la **Fédération Marocaine des Pigeons Voyageurs**, plusieurs éleveurs de pigeons participent durant la saison à diverses compétitions réparties sur trois étapes : **vitesse**, **demi fond** et **fond** (si les conditions climatiques le permettent). Ce projet vise à développer une application permettant de gérer l'intégralité de cette compétition.

## Fonctionnalités Principales

### 1. **Enregistrement des Éleveurs**
   - Les éleveurs peuvent s'enregistrer en fournissant les informations suivantes :
     - Nom de colombier (unique)
     - Nom d'utilisateur
     - Mot de passe
     - Coordonnées GPS du colombier

### 2. **Authentification des Éleveurs**
   - Après l'enregistrement, les éleveurs peuvent se connecter à leur compte pour gérer leurs pigeons.

### 3. **Gestion des Pigeons**
   - Chaque pigeon est caractérisé par :
     - Numéro de bague (unique)
     - Sexe
     - Âge
     - Couleur
     - Image (optionnelle)

### 4. **Gestion des Compétitions**
   - L’organisateur peut définir les compétitions pour chaque étape de la saison avec :
     - Nom de la course
     - Coordonnées GPS du point de lâcher
     - Date et heure de départ
     - Distance prévisionnelle

### 5. **Ajout des Pigeons Participants**
   - L’organisateur peut ajouter les pigeons participants de chaque éleveur via les numéros de bague avant chaque compétition.

### 6. **Clôture et Calcul des Résultats**
   - L’organisateur peut clôturer la compétition via l'API et uploader les données collectées (heure d'arrivée et numéro de bague).
   - Les résultats sont calculés en utilisant la **formule de Haversine** pour la distance et en calculant le temps de vol, puis la vitesse.

### 7. **Classement et Attribution des Points**
   - Le classement des pigeons est basé sur la vitesse, ajustée selon la distance parcourue. Des **points de performance** sont attribués en fonction du classement, et un **classement général** est calculé en cumulant les points des 5 premiers pigeons de chaque colombier.

### 8. **Affichage et Export des Résultats**
   - Les résultats de chaque course sont affichés dans un tableau et partagés avec tous les participants, incluant les informations suivantes :
     - Classement
     - Colombier
     - Numéro de bague
     - Heure d’arrivée
     - Distance
     - Vitesse
     - Points
   - Les résultats peuvent être exportés au format PDF.

## Exigences Techniques

- **Backend** : Spring Boot pour développer l'API.
- **Base de données** : MongoDB pour stocker les données des éleveurs, pigeons et compétitions.
- **Architecture** : L'application doit être organisée en **couches** :
  - **Controller Layer** : Gère les requêtes HTTP.
  - **Service Layer** : Contient la logique métier.
  - **DAO Layer** : Accède à la base de données via MongoDB.
  - **Model Layer** : Contient les entités et modèles.
- **Validation des Données** : La validation des données est obligatoire pour assurer la qualité et l'intégrité des informations.
- **Gestion des Exceptions** : La gestion des erreurs doit être centralisée.
- **Tests** : Des tests unitaires doivent être inclus pour garantir le bon fonctionnement de l'application.
- **Configuration** : Le fichier de configuration doit être au format **YAML**.

## Installation

### Prérequis

- **Java** 17+
- **Maven** pour la gestion des dépendances
- **MongoDB** installé et configuré

### Cloner le Dépôt

```bash
git clone https://github.com/yourusername/pigeon-sky-race.git
cd pigeon-sky-race
```

### uml conception link:
**[class diagram](https://drive.google.com/file/d/1naQ3MV2Gqcqq_y1P8uYFT2oL6CzpKYal/view?usp=sharin)**

