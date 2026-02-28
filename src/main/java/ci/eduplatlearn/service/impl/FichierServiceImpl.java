package ci.eduplatlearn.service.impl;

import ci.eduplatlearn.api.exception.ResourceNotFoundException;
import ci.eduplatlearn.dto.fichier.FichierCreateRequestDTO;
import ci.eduplatlearn.dto.fichier.FichierResponseDTO;
import ci.eduplatlearn.dto.fichier.FichierUpdateRequestDTO;
import ci.eduplatlearn.entity.Fichier;
import ci.eduplatlearn.entity.Lecon;
import ci.eduplatlearn.repository.FichierRepository;
import ci.eduplatlearn.repository.LeconRepository;
import ci.eduplatlearn.service.FichierService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FichierServiceImpl implements FichierService {

    private final FichierRepository fichierRepository;
    private final LeconRepository leconRepository;

    public FichierServiceImpl(FichierRepository fichierRepository, LeconRepository leconRepository) {
        this.fichierRepository = fichierRepository;
        this.leconRepository = leconRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FichierResponseDTO> getAll(Pageable pageable) {
        return fichierRepository.findAll(pageable)
                .map(this::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public FichierResponseDTO getById(Long id) {
        Fichier fichier = fichierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fichier introuvable avec l'id : " + id));
        return toResponse(fichier);
    }

    @Override
    @Transactional
    public FichierResponseDTO create(FichierCreateRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }
        if (request.titre() == null || request.titre().isBlank()) {
            throw new IllegalArgumentException("titre is required");
        }
        if (request.nomFichier() == null || request.nomFichier().isBlank()) {
            throw new IllegalArgumentException("nomFichier is required");
        }
        if (request.cheminStockage() == null || request.cheminStockage().isBlank()) {
            throw new IllegalArgumentException("cheminStockage is required");
        }
        if (request.leconId() == null) {
            throw new IllegalArgumentException("leconId is required");
        }

        Lecon lecon = leconRepository.findById(request.leconId())
                .orElseThrow(() -> new ResourceNotFoundException("Leçon introuvable avec l'id : " + request.leconId()));

        Fichier fichier = new Fichier(request.titre(), lecon, request.nomFichier(), request.cheminStockage());
        Fichier saved = fichierRepository.save(fichier);
        return toResponse(saved);
    }

    @Override
    @Transactional
    public FichierResponseDTO update(Long id, FichierUpdateRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }

        Fichier fichier = fichierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fichier introuvable avec l'id : " + id));

        fichier.setTitre(request.titre());
        fichier.setNomFichier(request.nomFichier());
        fichier.setCheminStockage(request.cheminStockage());

        Fichier saved = fichierRepository.save(fichier);
        return toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!fichierRepository.existsById(id)) {
            throw new ResourceNotFoundException("Fichier introuvable avec l'id : " + id);
        }
        fichierRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FichierResponseDTO> getByLeconId(Long leconId, Pageable pageable) {
        return fichierRepository.findByLecon_Id(leconId, pageable)
                .map(this::toResponse);
    }

    private FichierResponseDTO toResponse(Fichier fichier) {
        return new FichierResponseDTO(
                fichier.getId(),
                fichier.getTitre(),
                fichier.getNomFichier(),
                fichier.getCheminStockage(),
                fichier.getLecon().getId(),
                fichier.getLecon().getTitre(),
                fichier.getCreatedAt(),
                fichier.getUpdatedAt()
        );
    }
}

