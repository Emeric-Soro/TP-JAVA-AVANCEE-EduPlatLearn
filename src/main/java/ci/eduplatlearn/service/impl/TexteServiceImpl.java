package ci.eduplatlearn.service.impl;

import ci.eduplatlearn.api.exception.ResourceNotFoundException;
import ci.eduplatlearn.dto.texte.TexteCreateRequestDTO;
import ci.eduplatlearn.dto.texte.TexteResponseDTO;
import ci.eduplatlearn.dto.texte.TexteUpdateRequestDTO;
import ci.eduplatlearn.entity.Lecon;
import ci.eduplatlearn.entity.Texte;
import ci.eduplatlearn.repository.LeconRepository;
import ci.eduplatlearn.repository.TexteRepository;
import ci.eduplatlearn.service.TexteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TexteServiceImpl implements TexteService {

    private final TexteRepository texteRepository;
    private final LeconRepository leconRepository;

    public TexteServiceImpl(TexteRepository texteRepository, LeconRepository leconRepository) {
        this.texteRepository = texteRepository;
        this.leconRepository = leconRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TexteResponseDTO> getAll(Pageable pageable) {
        return texteRepository.findAll(pageable)
                .map(this::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public TexteResponseDTO getById(Long id) {
        Texte texte = texteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Texte introuvable avec l'id : " + id));
        return toResponse(texte);
    }

    @Override
    @Transactional
    public TexteResponseDTO create(TexteCreateRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }
        if (request.titre() == null || request.titre().isBlank()) {
            throw new IllegalArgumentException("titre is required");
        }
        if (request.contenu() == null || request.contenu().isBlank()) {
            throw new IllegalArgumentException("contenu is required");
        }
        if (request.leconId() == null) {
            throw new IllegalArgumentException("leconId is required");
        }

        Lecon lecon = leconRepository.findById(request.leconId())
                .orElseThrow(() -> new ResourceNotFoundException("Leçon introuvable avec l'id : " + request.leconId()));

        Texte texte = new Texte(request.titre(), lecon, request.contenu());
        Texte saved = texteRepository.save(texte);
        return toResponse(saved);
    }

    @Override
    @Transactional
    public TexteResponseDTO update(Long id, TexteUpdateRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }

        Texte texte = texteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Texte introuvable avec l'id : " + id));

        texte.setTitre(request.titre());
        texte.setContenu(request.contenu());

        Texte saved = texteRepository.save(texte);
        return toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!texteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Texte introuvable avec l'id : " + id);
        }
        texteRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TexteResponseDTO> getByLeconId(Long leconId, Pageable pageable) {
        return texteRepository.findByLecon_Id(leconId, pageable)
                .map(this::toResponse);
    }

    private TexteResponseDTO toResponse(Texte texte) {
        return new TexteResponseDTO(
                texte.getId(),
                texte.getTitre(),
                texte.getContenu(),
                texte.getLecon().getId(),
                texte.getLecon().getTitre(),
                texte.getCreatedAt(),
                texte.getUpdatedAt()
        );
    }
}

