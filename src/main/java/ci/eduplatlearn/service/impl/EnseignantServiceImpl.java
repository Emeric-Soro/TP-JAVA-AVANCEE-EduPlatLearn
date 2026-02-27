package ci.eduplatlearn.service.impl;

import ci.eduplatlearn.api.exception.ResourceNotFoundException;
import ci.eduplatlearn.dto.Enseignant.EnseignantCreateRequestDTO;
import ci.eduplatlearn.dto.Enseignant.EnseignantResponseDTO;
import ci.eduplatlearn.dto.Enseignant.EnseignantUpdateRequestDTO;
import ci.eduplatlearn.entity.Enseignant;
import ci.eduplatlearn.repository.EnseignantRepository;
import ci.eduplatlearn.service.EnseignantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnseignantServiceImpl implements EnseignantService {

    private final EnseignantRepository enseignantRepository;

    public EnseignantServiceImpl(EnseignantRepository enseignantRepository) {
        this.enseignantRepository = enseignantRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EnseignantResponseDTO> getAll(Pageable pageable) {
        return enseignantRepository.findAll(pageable)
                .map(this::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public EnseignantResponseDTO getById(Long id) {
        Enseignant enseignant = enseignantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enseignant introuvable avec l'id : " + id));
        return toResponse(enseignant);
    }

    @Override
    public EnseignantResponseDTO create(EnseignantCreateRequestDTO request) {
        // Règles simples de robustesse (sans faire encore la slide Validation)
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }
        if (request.nom() == null || request.nom().isBlank()) {
            throw new IllegalArgumentException("nom is required");
        }
        if (request.prenom() == null || request.prenom().isBlank()) {
            throw new IllegalArgumentException("prenom is required");
        }
        if (request.email() == null || request.email().isBlank()) {
            throw new IllegalArgumentException("email is required");
        }

        Enseignant enseignant = new Enseignant();
        applyCreate(enseignant, request);

        Enseignant saved = enseignantRepository.save(enseignant);
        return toResponse(saved);
    }

    @Override
    public EnseignantResponseDTO update(Long id, EnseignantUpdateRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }

        Enseignant enseignant = enseignantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enseignant introuvable avec l'id : " + id));

        applyUpdate(enseignant, request);
        Enseignant saved = enseignantRepository.save(enseignant);
        return toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        if (!enseignantRepository.existsById(id)) {
            throw new ResourceNotFoundException("Enseignant introuvable avec l'id : " + id);
        }
        enseignantRepository.deleteById(id);
    }

    @Override
    public Page<EnseignantResponseDTO> getByCoursId(Long coursId, Pageable pageable) {
            return enseignantRepository.findByCours_Id(coursId, pageable)
                     .map(this::toResponse);
    }


    private EnseignantResponseDTO toResponse(Enseignant enseignant) {
        return new EnseignantResponseDTO(
                enseignant.getId(),
                enseignant.getNom(),
                enseignant.getPrenom(),
                enseignant.getEmail(),
                enseignant.getBio(),
                enseignant.getCreatedAt(),
                enseignant.getUpdatedAt()
        );
    }

    private void applyCreate(Enseignant enseignant, EnseignantCreateRequestDTO request) {
        enseignant.setNom(request.nom());
        enseignant.setPrenom(request.prenom());
        enseignant.setEmail(request.email());
        enseignant.setBio(request.bio());
    }

    private void applyUpdate(Enseignant enseignant, EnseignantUpdateRequestDTO request) {
        enseignant.setNom(request.nom());
        enseignant.setPrenom(request.prenom());
        enseignant.setEmail(request.email());
        enseignant.setBio(request.bio());
    }

}
