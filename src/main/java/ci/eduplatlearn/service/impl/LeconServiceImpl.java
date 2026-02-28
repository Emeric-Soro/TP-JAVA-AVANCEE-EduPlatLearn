package ci.eduplatlearn.service.impl;

import ci.eduplatlearn.api.exception.ResourceNotFoundException;
import ci.eduplatlearn.dto.lecon.LeconCreateRequestDTO;
import ci.eduplatlearn.dto.lecon.LeconResponseDTO;
import ci.eduplatlearn.dto.lecon.LeconUpdateRequestDTO;
import ci.eduplatlearn.entity.Lecon;
import ci.eduplatlearn.entity.Module;
import ci.eduplatlearn.repository.LeconRepository;
import ci.eduplatlearn.repository.ModuleRepository;
import ci.eduplatlearn.service.LeconService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LeconServiceImpl implements LeconService {

    private final LeconRepository leconRepository;
    private final ModuleRepository moduleRepository;

    public LeconServiceImpl(LeconRepository leconRepository, ModuleRepository moduleRepository) {
        this.leconRepository = leconRepository;
        this.moduleRepository = moduleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LeconResponseDTO> getAll(Pageable pageable) {
        return leconRepository.findAll(pageable)
                .map(this::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public LeconResponseDTO getById(Long id) {
        Lecon lecon = leconRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leçon introuvable avec l'id : " + id));
        return toResponse(lecon);
    }

    @Override
    @Transactional
    public LeconResponseDTO create(LeconCreateRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }
        if (request.titre() == null || request.titre().isBlank()) {
            throw new IllegalArgumentException("titre is required");
        }
        if (request.ordre() == null) {
            throw new IllegalArgumentException("ordre is required");
        }
        if (request.dureeMinutes() == null) {
            throw new IllegalArgumentException("dureeMinutes is required");
        }
        if (request.moduleId() == null) {
            throw new IllegalArgumentException("moduleId is required");
        }

        Module module = moduleRepository.findById(request.moduleId())
                .orElseThrow(() -> new ResourceNotFoundException("Module introuvable avec l'id : " + request.moduleId()));

        Lecon lecon = new Lecon();
        applyCreate(lecon, request, module);

        Lecon saved = leconRepository.save(lecon);
        return toResponse(saved);
    }


    @Override
    @Transactional
    public LeconResponseDTO update(Long id, LeconUpdateRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }

        Lecon lecon = leconRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leçon introuvable avec l'id : " + id));

        applyUpdate(lecon, request);
        Lecon saved = leconRepository.save(lecon);
        return toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!leconRepository.existsById(id)) {
            throw new ResourceNotFoundException("Leçon introuvable avec l'id : " + id);
        }
        leconRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LeconResponseDTO> getByModuleId(Long moduleId, Pageable pageable) {
        return leconRepository.findByModule_Id(moduleId, pageable)
                .map(this::toResponse);
    }

    private LeconResponseDTO toResponse(Lecon lecon) {
        return new LeconResponseDTO(
                lecon.getId(),
                lecon.getTitre(),
                lecon.getResume(),
                lecon.getOrdre(),
                lecon.getDureeMinutes(),
                lecon.getModule().getId(),
                lecon.getModule().getTitre(),
                lecon.getCreatedAt(),
                lecon.getUpdatedAt()
        );
    }


    private void applyCreate(Lecon lecon, LeconCreateRequestDTO request, Module module) {
        lecon.setTitre(request.titre());
        lecon.setResume(request.resume());
        lecon.setOrdre(request.ordre());
        lecon.setDureeMinutes(request.dureeMinutes());
        lecon.setModule(module);
    }


    private void applyUpdate(Lecon lecon, LeconUpdateRequestDTO request) {
        lecon.setTitre(request.titre());
        lecon.setResume(request.resume());
        lecon.setOrdre(request.ordre());
        lecon.setDureeMinutes(request.dureeMinutes());
    }
}


