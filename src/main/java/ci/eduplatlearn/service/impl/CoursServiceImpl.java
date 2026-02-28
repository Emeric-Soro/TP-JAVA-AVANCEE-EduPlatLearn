package ci.eduplatlearn.service.impl;

import ci.eduplatlearn.api.exception.ResourceNotFoundException;
import ci.eduplatlearn.dto.cours.CoursCreateRequestDTO;
import ci.eduplatlearn.dto.cours.CoursResponseDTO;
import ci.eduplatlearn.dto.cours.CoursUpdateRequestDTO;
import ci.eduplatlearn.entity.Enseignant;
import ci.eduplatlearn.repository.EnseignantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ci.eduplatlearn.entity.Cours;
import ci.eduplatlearn.repository.CoursRepository;
import ci.eduplatlearn.service.CoursService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class CoursServiceImpl implements CoursService {

    private final CoursRepository coursRepository;
    private final EnseignantRepository enseignantRepository;

    public CoursServiceImpl(CoursRepository coursRepository, EnseignantRepository enseignantRepository) {
        this.coursRepository = coursRepository;
        this.enseignantRepository = enseignantRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CoursResponseDTO> getAll(Pageable pageable) {
        return coursRepository.findAll(pageable)
                .map(this::toResponse);
    }

    private Cours toView(Cours cours) {
        return new Cours(cours.getId(), cours.getTitre(), cours.getDescription(), cours.getNiveau(), cours.getPublie());
    }

    @Override
    @Transactional(readOnly = true)
    public CoursResponseDTO getById(Long id) {
        try {
            Cours cours = coursRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Cours not found: " + id));
            return toResponse(cours);
        }catch(Exception e) {
            throw new ResourceNotFoundException("Cours introuvable avec l'id : " + id);
        }
    }

    @Override
    public CoursResponseDTO create(CoursCreateRequestDTO request) {
        // Règles simples de robustesse (sans faire encore la slide Validation)
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }
        if (request.titre() == null || request.titre().isBlank()) {
            throw new IllegalArgumentException("titre is required");
        }
        if (request.niveau() == null || request.niveau().isBlank()) {
            throw new IllegalArgumentException("niveau is required");
        }

        Cours cours = new Cours();
        applyCreate(cours, request);

        // createdAt/updatedAt : idéalement gérés par JPA/Auditing (partie suivante si besoin)
        Cours saved = coursRepository.save(cours);
        return toResponse(saved);
    }

    @Override
    public CoursResponseDTO update(Long id, CoursUpdateRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }

        Cours cours = coursRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cours not found: " + id));

        applyUpdate(cours, request);

        // save() n'est pas strictement obligatoire si l'entité est managée,
        // mais on le garde pour être explicite pédagogiquement
        Cours saved = coursRepository.save(cours);
        return toResponse(saved);
    }


    @Override
    public void delete(Long id) {
        if (!coursRepository.existsById(id)) {
            throw new IllegalArgumentException("Cours not found: " + id);
        }
        coursRepository.deleteById(id);
    }

    private CoursResponseDTO toResponse(Cours cours) {
        return new CoursResponseDTO(
                cours.getId(),
                cours.getTitre(),
                cours.getDescription(),
                cours.getNiveau(),
                cours.getPublie(),
                cours.getCreatedAt(),
                cours.getUpdatedAt()
        );
    }

    private void applyCreate(Cours cours, CoursCreateRequestDTO req) {
        cours.setTitre(req.titre());
        cours.setDescription(req.description());
        cours.setNiveau(req.niveau());

        // Valeur par défaut "publie = false" si non fourni
        cours.setPublie(req.publie() != null ? req.publie() : Boolean.FALSE);
    }


    private void applyUpdate(Cours cours, CoursUpdateRequestDTO req) {
        // Ici on fait un update "complet" (PUT) : on remplace les champs.
        // Si tu veux du PATCH plus tard, on gérera différemment.
        cours.setTitre(req.titre());
        cours.setDescription(req.description());
        cours.setNiveau(req.niveau());
        cours.setPublie(req.publie());
    }

    @Override
    @Transactional
    public CoursResponseDTO addEnseignant(Long coursId, Long enseignantId) {
        Cours cours = coursRepository.findById(coursId)
                .orElseThrow(() -> new ResourceNotFoundException("Cours introuvable avec l'id : " + coursId));

        Enseignant enseignant = enseignantRepository.findById(enseignantId)
                .orElseThrow(() -> new ResourceNotFoundException("Enseignant introuvable avec l'id : " + enseignantId));

        // Ajouter l'enseignant au cours (relation ManyToMany)
        cours.getEnseignants().add(enseignant);
        enseignant.getCours().add(cours);

        Cours saved = coursRepository.save(cours);
        return toResponse(saved);
    }

    @Override
    @Transactional
    public CoursResponseDTO removeEnseignant(Long coursId, Long enseignantId) {
        Cours cours = coursRepository.findById(coursId)
                .orElseThrow(() -> new ResourceNotFoundException("Cours introuvable avec l'id : " + coursId));

        Enseignant enseignant = enseignantRepository.findById(enseignantId)
                .orElseThrow(() -> new ResourceNotFoundException("Enseignant introuvable avec l'id : " + enseignantId));

        // Retirer l'enseignant du cours
        cours.getEnseignants().remove(enseignant);
        enseignant.getCours().remove(cours);

        Cours saved = coursRepository.save(cours);
        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CoursResponseDTO> getByEnseignantId(Long enseignantId, Pageable pageable) {
        return coursRepository.findByEnseignants_Id(enseignantId, pageable)
                .map(this::toResponse);
    }
}

