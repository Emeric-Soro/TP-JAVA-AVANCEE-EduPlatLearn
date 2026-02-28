package ci.eduplatlearn.infrastructure.seeder;

import ci.eduplatlearn.entity.Cours;
import ci.eduplatlearn.entity.Enseignant;
import ci.eduplatlearn.entity.Fichier;
import ci.eduplatlearn.entity.Lecon;
import ci.eduplatlearn.entity.Texte;
import ci.eduplatlearn.entity.Video;
import ci.eduplatlearn.entity.Module;
import ci.eduplatlearn.repository.CoursRepository;
import ci.eduplatlearn.repository.EnseignantRepository;
import ci.eduplatlearn.repository.FichierRepository;
import ci.eduplatlearn.repository.LeconRepository;
import ci.eduplatlearn.repository.ModuleRepository;
import ci.eduplatlearn.repository.TexteRepository;
import ci.eduplatlearn.repository.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final CoursRepository coursRepository;
    private final EnseignantRepository enseignantRepository;
    private final ModuleRepository moduleRepository;
    private final LeconRepository leconRepository;
    private final VideoRepository videoRepository;
    private final TexteRepository texteRepository;
    private final FichierRepository fichierRepository;

    public DatabaseSeeder(
            CoursRepository coursRepository,
            EnseignantRepository enseignantRepository,
            ModuleRepository moduleRepository,
            LeconRepository leconRepository,
            VideoRepository videoRepository,
            TexteRepository texteRepository,
            FichierRepository fichierRepository
    ) {
        this.coursRepository = coursRepository;
        this.enseignantRepository = enseignantRepository;
        this.moduleRepository = moduleRepository;
        this.leconRepository = leconRepository;
        this.videoRepository = videoRepository;
        this.texteRepository = texteRepository;
        this.fichierRepository = fichierRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (coursRepository.count() > 0) {
            System.out.println("Base de données déjà remplie. Seeder ignoré");
            return;
        }

        System.out.println("Début du seeding de la base de données...");

        Enseignant enseignant1 = createEnseignant("Soro", "Emeric Jamel", "soroemeric@gmail.com",
            "DataEngeener et Spécialite en developpement backend.");
        Enseignant enseignant2 = createEnseignant("Akandan Aho", "Paul Emmanuel", "ahopaul18@gmail.com",
            "Specialiste en bases de donnees et architectures distribuees.");
        Enseignant enseignant3 = createEnseignant("Irié", "Adjo Jemima", "iannejemima@gmail.com",
            "Developpeur Full Stack passionne par les technologies web modernes.");
        Enseignant enseignant4 = createEnseignant("Assy", "Eliel", "assyeliel@gmail.com", "Expert Front-End React et Angular.");
        Enseignant enseignant5 = createEnseignant("Diarrassouba", "Aminata", "aminataD@gmail.com", "Data Scientist et formatrice Python.");
        Enseignant enseignant6 = createEnseignant("Kone", "Ibrahim", "ibrahimkone@gmail.com", "Ingenieur Cloud et DevOps certifie AWS.");
        Enseignant enseignant7 = createEnseignant("Toure", "Fatou", "fatoutoure@gmail.com", "Designer UX/UI avec 8 ans d'experience.");
        Enseignant enseignant8 = createEnseignant("Bamba", "Moussa", "moussabamba@gmail.com", "Expert en cybersecurite et pentesting.");


        Cours cours1 = createCours("Programmation Java Avancee",
            "Maitrisez les concepts avances de Java : streams, lambdas, multithreading, et plus encore.",
            "Avance", true);
        Cours cours2 = createCours("Spring Boot - De Zero a Heros",
            "Apprenez a creer des applications web robustes avec Spring Boot, JPA, et REST API.",
            "Intermediaire", true);
        Cours cours3 = createCours("Bases de Donnees Relationnelles",
            "Decouvrez SQL, la modelisation de donnees et l'optimisation des requetes.",
            "Debutant", true);
        Cours cours4 = createCours("Architecture Microservices",
            "Architecturez et deployez des applications microservices modernes.",
            "Avance", false);
        Cours cours5 = createCours("Masterclass React JS", "Devenez un pro de React, des Hooks et du State Management.", "Intermediaire", true);
        Cours cours6 = createCours("Data Science avec Python", "Analyse de donnees avec Pandas, Numpy et visualisation.", "Avance", true);
        Cours cours7 = createCours("DevOps et AWS Cloud", "Deployez et gerez des infrastructures performantes sur AWS.", "Avance", true);
        Cours cours8 = createCours("Fondamentaux du Design UX/UI", "Apprenez a designer des interfaces intuitives avec Figma.", "Debutant", true);
        Cours cours9 = createCours("Cybersecurite Web (OWASP)", "Protegez vos applications contre les failles courantes.", "Avance", false);


        coursRepository.save(cours1);
        coursRepository.save(cours2);
        coursRepository.save(cours3);
        coursRepository.save(cours4);
        coursRepository.save(cours5);
        coursRepository.save(cours6);
        coursRepository.save(cours7);
        coursRepository.save(cours8);
        coursRepository.save(cours9);

        // Associer enseignants aux cours (côté propriétaire = Enseignant)
        enseignant1.getCours().add(cours1);
        enseignant1.getCours().add(cours2);
        enseignant1.getCours().add(cours4);

        enseignant2.getCours().add(cours3);
        enseignant2.getCours().add(cours4);

        enseignant3.getCours().add(cours2);

        enseignant4.getCours().add(cours5);
        enseignant5.getCours().add(cours6);
        enseignant6.getCours().add(cours7);
        enseignant7.getCours().add(cours8);
        enseignant8.getCours().add(cours9);

        // Sauvegarder les enseignants pour persister les relations
        enseignantRepository.save(enseignant1);
        enseignantRepository.save(enseignant2);
        enseignantRepository.save(enseignant3);
        enseignantRepository.save(enseignant4);
        enseignantRepository.save(enseignant5);
        enseignantRepository.save(enseignant6);
        enseignantRepository.save(enseignant7);
        enseignantRepository.save(enseignant8);

        Module module1 = createModule("Introduction a Spring Boot",
            "Decouvrez les fondamentaux de Spring Boot et creez votre premiere application.",
            1, cours2);
        Module module2 = createModule("Spring Data JPA",
            "Apprenez a gerer la persistance des donnees avec JPA et Hibernate.",
            2, cours2);
        Module module3 = createModule("Creation d'API REST",
            "Construisez des API RESTful completes avec Spring Boot.",
            3, cours2);
        Module module4 = createModule("Programmation Fonctionnelle en Java",
            "Maitrisez les streams, lambdas et l'API fonctionnelle de Java.",
            1, cours1);
        Module module5 = createModule("Multithreading et Concurrence",
            "Apprenez a gerer le multithreading et la programmation concurrente.",
            2, cours1);
        Module module6 = createModule("Les bases de React", "Introduction aux composants et au JSX.", 1, cours5);
        Module module7 = createModule("Manipulation de donnees avec Pandas", "Maitrisez les DataFrames en Python.", 1, cours6);
        Module module8 = createModule("Introduction a AWS EC2", "Creer et gerer des instances virtuelles.", 1, cours7);
        Module module9 = createModule("Prototypage avec Figma", "Creer des maquettes interactives de vos apps.", 1, cours8);
        Module module10 = createModule("Les failles d'injection", "Comprendre et prevenir les injections SQL.", 1, cours9);

        Lecon lecon1 = createLecon("Installation et Configuration",
            "Installez Spring Boot et configurez votre environnement de developpement.",
            1, 30, module1);
        Lecon lecon2 = createLecon("Premiere Application Spring Boot",
            "Creez votre premiere application Hello World avec Spring Boot.",
            2, 45, module1);
        Lecon lecon3 = createLecon("Structure d'un Projet Spring Boot",
            "Comprenez l'architecture et la structure d'un projet Spring Boot.",
            3, 40, module1);
        Lecon lecon4 = createLecon("Configuration de JPA",
            "Configurez Spring Data JPA et connectez-vous a une base de donnees.",
            1, 35, module2);
        Lecon lecon5 = createLecon("Creation des Entites",
            "Apprenez a creer et mapper des entites JPA.",
            2, 50, module2);
        Lecon lecon6 = createLecon("Repositories Spring Data",
            "Utilisez les repositories pour effectuer des operations CRUD.",
            3, 45, module2);
        Lecon lecon7 = createLecon("Les Controleurs REST",
            "Creez vos premiers controleurs REST avec @RestController.",
            1, 40, module3);
        Lecon lecon8 = createLecon("Validation des Donnees",
            "Validez les donnees d'entree avec Bean Validation.",
            2, 35, module3);
        Lecon lecon9 = createLecon("Introduction au JSX", "Syntaxe et regles de base du JSX.", 1, 20, module6);
        Lecon lecon10 = createLecon("Les Composants", "Creer des composants React reutilisables.", 2, 25, module6);
        Lecon lecon11 = createLecon("Gestion du State", "Utilisation du hook useState.", 3, 30, module6);
        Lecon lecon12 = createLecon("Introduction a Pandas", "Installation et structure Series.", 1, 20, module7);
        Lecon lecon13 = createLecon("Les DataFrames", "Manipulation de tableaux de donnees complexes.", 2, 35, module7);
        Lecon lecon14 = createLecon("Filtrage de donnees", "Trier, grouper et filtrer avec Pandas.", 3, 25, module7);
        Lecon lecon15 = createLecon("Concept du Cloud Computing", "Definitions du IaaS, PaaS, SaaS.", 1, 20, module8);
        Lecon lecon16 = createLecon("Lancer une instance EC2", "Creation pas a pas d'un serveur.", 2, 40, module8);
        Lecon lecon17 = createLecon("Securite EC2", "Configurer le pare-feu et les Security Groups.", 3, 30, module8);
        Lecon lecon18 = createLecon("Interface de Figma", "Decouverte des outils de l'interface Figma.", 1, 15, module9);
        Lecon lecon19 = createLecon("Design System de base", "Creer des styles de couleur et de typographie.", 2, 45, module9);
        Lecon lecon20 = createLecon("Animations", "Lier les ecrans entre eux pour le prototypage.", 3, 40, module9);
        Lecon lecon21 = createLecon("Qu'est-ce qu'une injection SQL ?", "Theorie et exemples concrets d'attaques.", 1, 30, module10);
        Lecon lecon22 = createLecon("Prevention Backend", "Utiliser les requetes preparees en Java et PHP.", 2, 35, module10);
        Lecon lecon23 = createLecon("Atelier pratique : Test", "Tester les vulnerabilites d'un champ input.", 3, 40, module10);


        // 5. Créer les ressources - UNE SEULE ressource par leçon (OneToOne)
        // Videos
        Video video1 = new Video("Installation Spring Boot - Tutoriel", lecon1,
            "https://www.youtube.com/watch?v=spring-boot-install", 1800);
        videoRepository.save(video1);

        Video video2 = new Video("Creation d'une application Hello World", lecon2,
            "https://www.youtube.com/watch?v=spring-boot-hello", 2700);
        videoRepository.save(video2);

        Video video3 = new Video("Configuration de JPA - Demo pratique", lecon4,
            "https://www.youtube.com/watch?v=spring-jpa-config", 2100);
        videoRepository.save(video3);

        Video video4 = new Video("Tutoriel JSX React", lecon9, "https://www.youtube.com/watch?v=react-jsx", 1200);
        videoRepository.save(video4);

        Video video5 = new Video("Les bases de Pandas", lecon12, "https://www.youtube.com/watch?v=python-pandas", 1500);
        videoRepository.save(video5);

        Video video6 = new Video("Intro au Cloud AWS", lecon15, "https://www.youtube.com/watch?v=aws-intro", 1800);
        videoRepository.save(video6);

        Video video7 = new Video("Decouverte de Figma", lecon18, "https://www.youtube.com/watch?v=figma-ui", 900);
        videoRepository.save(video7);

        Video video8 = new Video("Demo Injection SQL", lecon21, "https://www.youtube.com/watch?v=sqli-demo", 1600);
        videoRepository.save(video8);

        // Textes
        Texte texte1 = new Texte("Architecture d'un Projet Spring Boot", lecon3,
            "Structure projet: controller, service, repository, entity, dto. Configuration: application.properties, pom.xml.");
        texteRepository.save(texte1);

        Texte texte2 = new Texte("Introduction aux Entites JPA", lecon5,
            "Entites JPA: classes representant tables BD. Annotations: @Entity, @Id, @GeneratedValue, @Column, @ManyToOne, @OneToMany.");
        texteRepository.save(texte2);

        Texte texte3 = new Texte("Guide Repositories Spring Data", lecon6,
            "Les repositories Spring Data permettent d'effectuer des operations CRUD sans ecrire de code SQL.");
        texteRepository.save(texte3);

        Texte texte4 = new Texte("Guide des Composants React", lecon10, "Un composant React est une fonction JavaScript qui retourne du markup JSX...");
        texteRepository.save(texte4);

        Texte texte5 = new Texte("Documentation DataFrames", lecon13, "Le DataFrame est la structure principale de Pandas, comparable a une table SQL...");
        texteRepository.save(texte5);

        Texte texte6 = new Texte("Tutoriel Creation EC2", lecon16, "Pour lancer une EC2, choisissez d'abord une AMI, puis le type d'instance...");
        texteRepository.save(texte6);

        Texte texte7 = new Texte("Guidelines Design System", lecon19, "Les couleurs primaires, secondaires, et les echelles typographiques...");
        texteRepository.save(texte7);

        Texte texte8 = new Texte("Bonnes pratiques de prevention", lecon22, "Il faut toujours echapper les entrees utilisateurs et utiliser des PreparedStatement...");
        texteRepository.save(texte8);

        // Fichiers
        Fichier fichier1 = new Fichier("Code Source - API REST Complete", lecon7,
            "rest-api-example.zip", "/uploads/cours/spring-boot/module3/rest-api-example.zip");
        fichierRepository.save(fichier1);

        Fichier fichier2 = new Fichier("Exercices Pratiques - Validation", lecon8,
            "validation-exercises.pdf", "/uploads/cours/spring-boot/module3/validation-exercises.pdf");
        fichierRepository.save(fichier2);

        Fichier fichier3 = new Fichier("Code Source - useState React", lecon11, "react-state.zip", "/uploads/cours/react/react-state.zip");
        fichierRepository.save(fichier3);

        Fichier fichier4 = new Fichier("Dataset Titanic CSV", lecon14, "titanic-data.csv", "/uploads/cours/python/titanic-data.csv");
        fichierRepository.save(fichier4);

        Fichier fichier5 = new Fichier("Template CloudFormation JSON", lecon17, "ec2-template.json", "/uploads/cours/aws/ec2-template.json");
        fichierRepository.save(fichier5);

        Fichier fichier6 = new Fichier("Maquette UI Figma Export", lecon20, "maquette.fig", "/uploads/cours/design/maquette.fig");
        fichierRepository.save(fichier6);

        Fichier fichier7 = new Fichier("Script de test Python", lecon23, "test-sqli.py", "/uploads/cours/secu/test-sqli.py");
        fichierRepository.save(fichier7);

        System.out.println("Seeding termine avec succes !");
        System.out.println("Statistiques :");
        System.out.println("   - Enseignants : " + enseignantRepository.count());
        System.out.println("   - Cours : " + coursRepository.count());
        System.out.println("   - Modules : " + moduleRepository.count());
        System.out.println("   - Lecons : " + leconRepository.count());
        System.out.println("   - Videos : " + videoRepository.count());
        System.out.println("   - Textes : " + texteRepository.count());
        System.out.println("   - Fichiers : " + fichierRepository.count());
        System.out.println("   - Total Ressources : " + (videoRepository.count() + texteRepository.count() + fichierRepository.count()));
    }

    private Enseignant createEnseignant(String nom, String prenom, String email, String bio) {
        Enseignant enseignant = new Enseignant();
        enseignant.setNom(nom);
        enseignant.setPrenom(prenom);
        enseignant.setEmail(email);
        enseignant.setBio(bio);
        return enseignantRepository.save(enseignant);
    }

    private Cours createCours(String titre, String description, String niveau, Boolean publie) {
        Cours cours = new Cours();
        cours.setTitre(titre);
        cours.setDescription(description);
        cours.setNiveau(niveau);
        cours.setPublie(publie);
        return cours;
    }

    private Module createModule(String titre, String description, Integer ordre, Cours cours) {
        Module module = new Module();
        module.setTitre(titre);
        module.setDescription(description);
        module.setOrdre(ordre);
        module.setCours(cours);
        return moduleRepository.save(module);
    }

    private Lecon createLecon(String titre, String resume, Integer ordre, Integer dureeMinutes, Module module) {
        Lecon lecon = new Lecon(titre, resume, ordre, dureeMinutes);
        lecon.setModule(module);
        return leconRepository.save(lecon);
    }
}
