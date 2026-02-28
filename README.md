# EduPlatLearn

Plateforme d'apprentissage en ligne - API REST Spring Boot

## Description

EduPlatLearn est une API REST développée avec Spring Boot pour gérer une plateforme éducative. Elle permet de gérer des cours, des modules, des leçons et différents types de ressources pédagogiques (vidéos, textes, fichiers).

## Prérequis

- Java 17 ou supérieur
- Maven 3.6 ou supérieur
- MySQL 8.0 ou supérieur
- WAMP/XAMPP ou tout autre serveur MySQL

## Technologies utilisées

- Spring Boot 3.x
- Spring Data JPA
- Spring Web
- MySQL
- Hibernate
- Swagger/OpenAPI (SpringDoc)
- Lombok (optionnel)

## Structure du projet

```
eduplatlearn/
├── src/
│   ├── main/
│   │   ├── java/ci/eduplatlearn/
│   │   │   ├── api/
│   │   │   │   ├── controller/      # Contrôleurs REST
│   │   │   │   └── exception/       # Gestion des exceptions
│   │   │   ├── dto/                 # Data Transfer Objects
│   │   │   │   ├── cours/
│   │   │   │   ├── Enseignant/
│   │   │   │   ├── module/
│   │   │   │   ├── lecon/
│   │   │   │   ├── fichier/
│   │   │   │   ├── texte/
│   │   │   │   └── video/
│   │   │   ├── entity/              # Entités JPA
│   │   │   ├── repository/          # Repositories Spring Data
│   │   │   ├── service/             # Services métier
│   │   │   │   └── impl/
│   │   │   └── infrastructure/
│   │   │       ├── config/          # Configuration
│   │   │       └── seeder/          # Données initiales
│   │   └── resources/
│   │       ├── application.yml      # Configuration principale
│   │       └── application-mysql.yml # Configuration MySQL
│   └── test/
└── pom.xml
```

## Installation

### 1. Cloner le projet

```bash
git clone <url-du-depot>
cd eduplatlearn
```

### 2. Configurer la base de données

Créez une base de données MySQL :

```sql
CREATE DATABASE eduplatlearn CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 3. Configuration

Modifiez le fichier `src/main/resources/application-mysql.yml` avec vos paramètres :

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/eduplatlearn?useSSL=false&serverTimezone=UTC
    username: votre_utilisateur
    password: votre_mot_de_passe
```

### 4. Compiler le projet

```bash
mvn clean install
```

### 5. Lancer l'application

```bash
mvn spring-boot:run
```

Ou avec le wrapper Maven :

```bash
# Windows
mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

L'application démarre sur `http://localhost:8080`

## Documentation API (Swagger)

Une fois l'application lancée, accédez à la documentation interactive Swagger UI :

```
http://localhost:8080/swagger-ui.html
```

ou

```
http://localhost:8080/swagger-ui/index.html
```

## Endpoints principaux

### Cours

- `GET /api/cours` - Liste tous les cours (paginée)
- `GET /api/cours/{id}` - Détails d'un cours
- `POST /api/cours` - Créer un cours
- `PUT /api/cours/{id}` - Mettre à jour un cours
- `DELETE /api/cours/{id}` - Supprimer un cours
- `GET /api/cours/{coursId}/modules` - Liste les modules d'un cours
- `GET /api/cours/{coursId}/enseignants` - Liste les enseignants d'un cours
- `POST /api/cours/{coursId}/enseignants/{enseignantId}` - Attribuer un enseignant à un cours
- `DELETE /api/cours/{coursId}/enseignants/{enseignantId}` - Retirer un enseignant d'un cours

### Enseignants

- `GET /api/enseignants` - Liste tous les enseignants (paginée)
- `GET /api/enseignants/{id}` - Détails d'un enseignant
- `POST /api/enseignants` - Créer un enseignant
- `PUT /api/enseignants/{id}` - Mettre à jour un enseignant
- `DELETE /api/enseignants/{id}` - Supprimer un enseignant
- `GET /api/enseignants/{enseignantId}/cours` - Liste les cours d'un enseignant
- `GET /api/enseignants/cours/{coursId}` - Liste les enseignants d'un cours

### Modules

- `GET /api/modules` - Liste tous les modules (paginée)
- `GET /api/modules/{id}` - Détails d'un module
- `POST /api/modules` - Créer un module
- `PUT /api/modules/{id}` - Mettre à jour un module
- `DELETE /api/modules/{id}` - Supprimer un module
- `GET /api/modules/cours/{coursId}` - Liste les modules d'un cours
- `GET /api/modules/{moduleId}/lecons` - Liste les leçons d'un module

### Leçons

- `GET /api/lecons` - Liste toutes les leçons (paginée)
- `GET /api/lecons/{id}` - Détails d'une leçon
- `POST /api/lecons` - Créer une leçon
- `PUT /api/lecons/{id}` - Mettre à jour une leçon
- `DELETE /api/lecons/{id}` - Supprimer une leçon
- `GET /api/lecons/module/{moduleId}` - Liste les leçons d'un module

### Ressources (Vidéo, Texte, Fichier)

- `GET /api/videos` - Liste toutes les vidéos
- `GET /api/videos/{id}` - Détails d'une vidéo
- `POST /api/videos` - Créer une vidéo
- `PUT /api/videos/{id}` - Mettre à jour une vidéo
- `DELETE /api/videos/{id}` - Supprimer une vidéo
- `GET /api/videos/lecon/{leconId}` - Liste les vidéos d'une leçon

Les endpoints pour `/api/textes` et `/api/fichiers` suivent la même structure.

## Exemples d'utilisation

### Créer un cours

```bash
curl -X POST http://localhost:8080/api/cours \
  -H "Content-Type: application/json" \
  -d '{
    "titre": "Introduction à Java",
    "description": "Cours complet sur Java",
    "niveau": "Débutant",
    "publie": true
  }'
```

### Créer un enseignant

```bash
curl -X POST http://localhost:8080/api/enseignants \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Dupont",
    "prenom": "Jean",
    "email": "jean.dupont@example.com",
    "bio": "Développeur senior avec 10 ans d'\''expérience"
  }'
```

### Attribuer un enseignant à un cours

```bash
curl -X POST http://localhost:8080/api/cours/1/enseignants/1
```

### Lister les cours avec pagination

```bash
curl "http://localhost:8080/api/cours?page=0&size=10&sort=titre,asc"
```

## Modèle de données

### Relations principales

- Un **Cours** contient plusieurs **Modules** (OneToMany)
- Un **Module** contient plusieurs **Leçons** (OneToMany)
- Une **Leçon** possède une **Ressource** (OneToOne) qui peut être :
  - Une Vidéo
  - Un Texte
  - Un Fichier
- Un **Cours** peut avoir plusieurs **Enseignants** (ManyToMany)
- Un **Enseignant** peut enseigner plusieurs **Cours** (ManyToMany)

## Données de test (Seeder)

### Fonctionnement du seeder

L'application contient un seeder (`DatabaseSeeder`) situé dans `src/main/java/ci/eduplatlearn/infrastructure/seeder/DatabaseSeeder.java`. Ce composant implémente l'interface `CommandLineRunner` et s'exécute automatiquement au démarrage de l'application.

### Données générées

Le seeder crée automatiquement un jeu de données complet pour tester l'application :

**Enseignants (3)** :
- Marie Martin - Experte en développement web
- Jean Dupont - Spécialiste bases de données
- Sophie Bernard - Développeuse full-stack

**Cours (3)** :
- Introduction à Spring Boot (Débutant)
- Développement Web Avancé (Intermédiaire)
- Architecture Microservices (Avancé)

**Pour chaque cours** :
- 2 modules avec des titres et descriptions
- Chaque module contient 2 leçons
- Chaque leçon a une ressource associée (vidéo, texte ou fichier)

**Relations** :
- Chaque cours est associé à un enseignant
- Les enseignants sont liés aux cours via la table de jointure `enseignant_cours`

### Ordre d'exécution

Le seeder respecte l'ordre suivant pour éviter les violations de contraintes :

1. Création des enseignants
2. Création des cours
3. Attribution des enseignants aux cours
4. Création des modules (liés aux cours)
5. Création des leçons (liées aux modules)
6. Création des ressources (liées aux leçons)

### Désactiver le seeder

Pour désactiver le seeder en développement, plusieurs options :

**Option 1 : Commenter l'annotation `@Component`**
```java
// @Component
public class DatabaseSeeder implements CommandLineRunner {
    // ...
}
```

**Option 2 : Ajouter une condition dans la méthode `run()`**
```java
@Override
public void run(String... args) throws Exception {
    if (shouldSeed()) {
        // ... code du seeder
    }
}

private boolean shouldSeed() {
    // Vérifier si des données existent déjà
    return enseignantRepository.count() == 0;
}
```

**Option 3 : Utiliser un profil Spring**

Ajoutez `@Profile("dev")` sur la classe et lancez l'application avec le profil approprié :
```bash
mvn spring-boot:run -Dspring.profiles.active=dev
```

### Réinitialiser les données

Pour repartir avec des données fraîches :

1. Supprimez la base de données :
```sql
DROP DATABASE eduplatlearn;
CREATE DATABASE eduplatlearn CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. Relancez l'application - le seeder recréera toutes les données automatiquement.

### Personnaliser le seeder

Vous pouvez modifier le fichier `DatabaseSeeder.java` pour :
- Ajouter plus de données
- Créer des scénarios de test spécifiques
- Charger des données depuis des fichiers JSON/CSV
- Adapter les données à vos besoins métier

Exemple pour ajouter un cours :
```java
Cours nouveauCours = new Cours();
nouveauCours.setTitre("Mon nouveau cours");
nouveauCours.setDescription("Description du cours");
nouveauCours.setNiveau("Débutant");
nouveauCours.setPublie(true);
coursRepository.save(nouveauCours);
```

## Gestion des erreurs

L'API retourne des codes HTTP standard :

- `200 OK` - Succès
- `201 Created` - Ressource créée
- `204 No Content` - Suppression réussie
- `400 Bad Request` - Données invalides
- `404 Not Found` - Ressource non trouvée
- `500 Internal Server Error` - Erreur serveur

Les erreurs sont formatées en JSON avec un message descriptif.

## Tests

Pour exécuter les tests :

```bash
mvn test
```

## Compilation pour production

Pour créer un JAR exécutable :

```bash
mvn clean package
```

Le fichier JAR sera généré dans `target/eduplatlearn-<version>.jar`

Pour l'exécuter :

```bash
java -jar target/eduplatlearn-<version>.jar
```

## Configuration avancée

### Changer le port

Dans `application.yml` :

```yaml
server:
  port: 8081
```

### Activer/désactiver le SQL logging

Dans `application.yml` :

```yaml
spring:
  jpa:
    show-sql: true
    properties:
      hibernate:
        format_sql: true
```

### Configuration du pool de connexions

Dans `application-mysql.yml` :

```yaml
spring:
  datasource:
    hikari:
      maximum-pool-size: 10
      minimum-idle: 5
      connection-timeout: 30000
```

## Dépannage

### Erreur de connexion à la base de données

Vérifiez que :
- MySQL est démarré (via WAMP/XAMPP)
- La base de données existe
- Les identifiants sont corrects dans `application-mysql.yml`
- Le port MySQL est bien 3306

### Port 8080 déjà utilisé

Changez le port dans `application.yml` ou arrêtez l'autre application.

### Erreurs de compilation

Assurez-vous d'avoir :
- Java 17 ou supérieur
- Maven correctement configuré
- Toutes les dépendances téléchargées (`mvn clean install`)

## Contribution

1. Fork le projet
2. Créez une branche pour votre fonctionnalité (`git checkout -b feature/nouvelle-fonctionnalite`)
3. Committez vos changements (`git commit -am 'Ajout nouvelle fonctionnalité'`)
4. Poussez vers la branche (`git push origin feature/nouvelle-fonctionnalite`)
5. Créez une Pull Request



