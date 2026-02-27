package ci.eduplatlearn.service.impl;

import ci.eduplatlearn.api.exception.ResourceNotFoundException;
import ci.eduplatlearn.dto.module.ModuleCreateRequestDTO;
import ci.eduplatlearn.dto.module.ModuleResponseDTO;
import ci.eduplatlearn.dto.module.ModuleUpdateRequestDTO;
import ci.eduplatlearn.entity.Cours;
import ci.eduplatlearn.entity.Module;
import ci.eduplatlearn.repository.CoursRepository;
import ci.eduplatlearn.repository.ModuleRepository;
import ci.eduplatlearn.service.ModuleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final CoursRepository coursRepository;

    public ModuleServiceImpl(ModuleRepository moduleRepository, CoursRepository coursRepository) {
        this.moduleRepository = moduleRepository;
        this.coursRepository = coursRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ModuleResponseDTO> getAll(Pageable pageable) {
        return moduleRepository.findAll(pageable)
                .map(this::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public ModuleResponseDTO getById(Long id) {
        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Module introuvable avec l'id : " + id));
        return toResponse(module);
    }

    @Override
    @Transactional
    public ModuleResponseDTO create(ModuleCreateRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }
        if (request.titre() == null || request.titre().isBlank()) {
            throw new IllegalArgumentException("titre is required");
        }
        if (request.ordre() == null) {
            throw new IllegalArgumentException("ordre is required");
        }
        if (request.coursId() == null) {
            throw new IllegalArgumentException("coursId is required");
        }

        Cours cours = coursRepository.findById(request.coursId())
                .orElseThrow(() -> new ResourceNotFoundException("Cours introuvable avec l'id : " + request.coursId()));

        Module module = new Module();
        applyCreate(module, request, cours);

        Module saved = moduleRepository.save(module);
        return toResponse(saved);
    }

    @Override
    @Transactional
    public ModuleResponseDTO update(Long id, ModuleUpdateRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }

        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Module introuvable avec l'id : " + id));

        applyUpdate(module, request);
        Module saved = moduleRepository.save(module);
        return toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!moduleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Module introuvable avec l'id : " + id);
        }
        moduleRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ModuleResponseDTO> getByCoursId(Long coursId, Pageable pageable) {
        return moduleRepository.findByCours_Id(coursId, pageable)
                .map(this::toResponse);
    }

    private ModuleResponseDTO toResponse(Module module) {
        return new ModuleResponseDTO(
                module.getId(),
                module.getTitre(),
                module.getDescription(),
                module.getOrdre(),
                module.getCours().getId(),
                module.getCours().getTitre(),
                module.getCreatedAt(),
                module.getUpdatedAt()
        );
    }

    private void applyCreate(Module module, ModuleCreateRequestDTO request, Cours cours) {
        module.setTitre(request.titre());
        module.setDescription(request.description());
        module.setOrdre(request.ordre());
        module.setCours(cours);
    }

    private void applyUpdate(Module module, ModuleUpdateRequestDTO request) {
        module.setTitre(request.titre());
        module.setDescription(request.description());
        module.setOrdre(request.ordre());
    }
}

