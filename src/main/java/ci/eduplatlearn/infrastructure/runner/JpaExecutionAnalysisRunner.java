//package ci.eduplatlearn.infrastructure.runner;
//
//import ci.eduplatlearn.entity.*;
//import ci.eduplatlearn.entity.Module;
//import ci.eduplatlearn.repository.*;
//import jakarta.transaction.Transactional;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@Transactional
//public class JpaExecutionAnalysisRunner implements CommandLineRunner {
//
//    private final CoursRepository coursRepository;
//    private final ModuleRepository moduleRepository;
//    private final RessourceRepository ressourceRepository;
//    private final EnseignantRepository enseignantRepository;
//    private final LeconRepository leconRepository;
//
//
//    public JpaExecutionAnalysisRunner(CoursRepository coursRepository, ModuleRepository moduleRepository, RessourceRepository ressourceRepository, EnseignantRepository enseignantRepository, LeconRepository leconRepository) {
//        this.coursRepository = coursRepository;
//        this.moduleRepository = moduleRepository;
//        this.ressourceRepository = ressourceRepository;
//        this.enseignantRepository = enseignantRepository;
//        this.leconRepository = leconRepository;
//    }
//
//
//    @Override
//    public void run(String... args) {
//
//        slide6();
//
////        nettoyage
////        moduleRepository.deleteAll();
////        coursRepository.deleteAll();
////
////        for(int i = 1; i <= 3; i++) {
////            Cours cours = new Cours();
////            cours.setTitre("Cours " + i);
////            cours.setDescription("Cours demo N + 1 - " + i);
////            cours.setNiveau("Master 1");
////            cours.setPublie(true);
////            coursRepository.save(cours);
////
////            for(int j = 1; j <= 2; j++) {
////                Module module = new Module();
////                module.setTitre("Module " + j + " du cours " + i);
////                module.setDescription("Module demo " + j + " du cours " + i);
////                module.setOrdre(j);
////                module.setCours(cours);
////                cours.getModules().add(module);
////                moduleRepository.save(module);
////            }
////        }
////
////        System.out.println("===== SLIDE 5 - N+1 PROBLEME =====");
////        List<Cours> coursS5 = coursRepository.findAll();
////        for(Cours c : coursS5) {
////            System.out.println("Cours : " + c.getTitre());
////            int nombreModules = c.getModules().size();
////            System.out.println("Nombre de modules : " + nombreModules);
////        }
//
//
//
////       CREATE Cours
////        Cours newCours = new Cours();
////        newCours.setTitre("Nouveau Cours");
////        newCours.setDescription("Description du nouveau cours");
////        newCours.setNiveau("M1");
////        newCours.setPublie(true);
////
////        Cours saved = coursRepository.save(newCours);
////        Long id = saved.getId();
////        System.out.println("Cours créé avec ID : " + id);
//
//// CREATE Module
////        Module newModule = new Module();
////        newModule.setTitre("Nouveau Module");
////        newModule.setDescription("Description du nouveau module");
////        newModule.setOrdre(1);
//////        association du module au cours
////        newModule.setCours(newCours);
////        Module savedModule = moduleRepository.save(newModule);
////        Long moduleId = savedModule.getId();
//
//
//    //ajout de module à un cours existant
////        Cours coursS4 = coursRepository.findById(1L).orElseThrow();
////        System.out.println("Cours : " + coursS4.getTitre());
////        Module module = new Module();
////        module.setTitre("Module 1 - lazy loading");
////        module.setDescription("Description du module lazy loading");
////        module.setOrdre(1);
////ASSOCIATION AVEC COURS EXISTANT
////        module.setCours(coursS4);
//////Coherence coté objet
////        coursS4.getModules().add(module);
//////persistance
////        moduleRepository.save(module);
////        System.out.println("slide 4 - Lazy LOADING ======");
//
//
//
//        listerCours();
////        listerModules();
////        listerRessources();
////        listerEnseignants();
////        listerLecons();
//    }
//
//    public void listerCours() {
//        List<Cours> cours = coursRepository.findAll();
//
//        cours.forEach(c -> {
////            nombre total de modules associés à ce cours
//            int nombreModules = c.getModules().size();
//            System.out.println("Nombre de modules : " + nombreModules);
//        });
//    }
//
//    public void listerModules() {
//        List<Module> modules = moduleRepository.findAll();
//        modules.forEach(m -> {
//        });
//    }
//
//    public void listerRessources() {
//        List<Ressource> ressources = ressourceRepository.findAll();
//        ressources.forEach(r -> {
//        });
//    }
//
//    public void listerEnseignants() {
//        List<Enseignant> enseignants = enseignantRepository.findAll();
//        enseignants.forEach(e -> {
//
//        });
//    }
//
//    public void listerLecons() {
//        List<Lecon> lecons = leconRepository.findAll();
//        lecons.forEach(l -> {
//        });
//    }
//
//    private void slide6(){
//        System.out.println("===== DEBUT SLIDE 6 - N+1 SOLUTION =====");
//        if(coursRepository.count() < 3){
//            System.out.println("dataSet insuffisant : execute d'abord le slide 5 pour la démonstration du slide 6");
//            return;
//        }
//        List<Cours> coursList = coursRepository.findAllWithModule();
//        for (Cours c : coursList) {
//            System.out.println("Cours : " + c.getTitre());
//            int nombreModules = c.getModules().size();
//            System.out.println("Nombre de modules (sans N + 1) : " + nombreModules);
//        }
//        System.out.println("===== FIN SLIDE 6 - N+1 SOLUTION =====");
//    }
//}
