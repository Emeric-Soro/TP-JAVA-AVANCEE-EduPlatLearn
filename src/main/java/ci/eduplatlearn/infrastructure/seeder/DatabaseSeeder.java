package ci.eduplatlearn.infrastructure.seeder;

import ci.eduplatlearn.entity.Cours;
import ci.eduplatlearn.entity.Enseignant;
import ci.eduplatlearn.entity.Fichier;
import ci.eduplatlearn.entity.Lecon;
import ci.eduplatlearn.entity.Texte;
import ci.eduplatlearn.entity.Video;
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
            System.out.println("âœ… Base de donnÃ©es dÃ©jÃ  remplie. Seeder ignorÃ©.");
            return;
        }

        System.out.println("ðŸŒ± DÃ©but du seeding de la base de donnÃ©es...");

        Enseignant enseignant1 = createEnseignant("Soro", "Emeric Jamel", "soroemeric@gmail.com",
            "Expert en developpement Java et Spring Boot avec 10 ans d'experience.");
        Enseignant enseignant2 = createEnseignant("Kouassi", "Marie", "marie.kouassi@eduplatlearn.ci",
            "Specialiste en bases de donnees et architectures distribuees.");
        Enseignant enseignant3 = createEnseignant("Traore", "Abdoulaye", "abdoulaye.traore@eduplatlearn.ci",
            "Developpeur Full Stack passionne par les technologies web modernes.");

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

        coursRepository.save(cours1);
        coursRepository.save(cours2);
        coursRepository.save(cours3);
        coursRepository.save(cours4);

        // Associer enseignants aux cours (côté propriétaire = Enseignant)
        enseignant1.getCours().add(cours1);
        enseignant1.getCours().add(cours2);
        enseignant1.getCours().add(cours4);

        enseignant2.getCours().add(cours3);
        enseignant2.getCours().add(cours4);

        enseignant3.getCours().add(cours2);

        // Sauvegarder les enseignants pour persister les relations
        enseignantRepository.save(enseignant1);
        enseignantRepository.save(enseignant2);
        enseignantRepository.save(enseignant3);

        ci.eduplatlearn.entity.Module module1 = createModule("Introduction a Spring Boot",
            "Decouvrez les fondamentaux de Spring Boot et creez votre premiere application.",
            1, cours2);
        ci.eduplatlearn.entity.Module module2 = createModule("Spring Data JPA",
            "Apprenez a gerer la persistance des donnees avec JPA et Hibernate.",
            2, cours2);
        ci.eduplatlearn.entity.Module module3 = createModule("Creation d'API REST",
            "Construisez des API RESTful completes avec Spring Boot.",
            3, cours2);

        ci.eduplatlearn.entity.Module module4 = createModule("Programmation Fonctionnelle en Java",
            "Maitrisez les streams, lambdas et l'API fonctionnelle de Java.",
            1, cours1);
        ci.eduplatlearn.entity.Module module5 = createModule("Multithreading et Concurrence",
            "Apprenez a gerer le multithreading et la programmation concurrente.",
            2, cours1);

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

        // 5. Créer les ressources - UNE SEULE ressource par leçon (OneToOne)
        // Videos pour lecon1, lecon2, lecon4
        Video video1 = new Video("Installation Spring Boot - Tutoriel", lecon1,
            "https://www.youtube.com/watch?v=spring-boot-install", 1800);
        videoRepository.save(video1);

        Video video2 = new Video("Creation d'une application Hello World", lecon2,
            "https://www.youtube.com/watch?v=spring-boot-hello", 2700);
        videoRepository.save(video2);

        Video video3 = new Video("Configuration de JPA - Demo pratique", lecon4,
            "https://www.youtube.com/watch?v=spring-jpa-config", 2100);
        videoRepository.save(video3);

        // Textes pour lecon3, lecon5, lecon6
        Texte texte1 = new Texte("Architecture d'un Projet Spring Boot", lecon3,
            "Structure projet: controller, service, repository, entity, dto. Configuration: application.properties, pom.xml.");
        texteRepository.save(texte1);

        Texte texte2 = new Texte("Introduction aux Entites JPA", lecon5,
            "Entites JPA: classes representant tables BD. Annotations: @Entity, @Id, @GeneratedValue, @Column, @ManyToOne, @OneToMany.");
        texteRepository.save(texte2);

        Texte texte3 = new Texte("Guide Repositories Spring Data", lecon6,
            "Les repositories Spring Data permettent d'effectuer des operations CRUD sans ecrire de code SQL.");
        texteRepository.save(texte3);

        // Fichiers pour lecon7, lecon8
        Fichier fichier1 = new Fichier("Code Source - API REST Complete", lecon7,
            "rest-api-example.zip", "/uploads/cours/spring-boot/module3/rest-api-example.zip");
        fichierRepository.save(fichier1);

        Fichier fichier2 = new Fichier("Exercices Pratiques - Validation", lecon8,
            "validation-exercises.pdf", "/uploads/cours/spring-boot/module3/validation-exercises.pdf");
        fichierRepository.save(fichier2);

        System.out.println("✅ Seeding termine avec succes !");
        System.out.println("📊 Statistiques :");
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

    private ci.eduplatlearn.entity.Module createModule(String titre, String description, Integer ordre, Cours cours) {
        ci.eduplatlearn.entity.Module module = new ci.eduplatlearn.entity.Module();
        module.setTitre(titre);
        module.setDescription(description);
        module.setOrdre(ordre);
        module.setCours(cours);
        return moduleRepository.save(module);
    }

    private Lecon createLecon(String titre, String resume, Integer ordre, Integer dureeMinutes, ci.eduplatlearn.entity.Module module) {
        Lecon lecon = new Lecon(titre, resume, ordre, dureeMinutes);
        lecon.setModule(module);
        return leconRepository.save(lecon);
    }
}
