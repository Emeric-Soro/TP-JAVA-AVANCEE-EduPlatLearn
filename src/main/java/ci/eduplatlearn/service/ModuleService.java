package ci.eduplatlearn.service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import ci.eduplatlearn.dto.module.ModuleUpdateRequestDTO;
import ci.eduplatlearn.dto.module.ModuleResponseDTO;
import ci.eduplatlearn.dto.module.ModuleCreateRequestDTO;


public interface ModuleService {
    Page<ModuleResponseDTO> getByCoursId(Long coursId, Pageable pageable);

    void delete(Long id);

    ModuleResponseDTO update(Long id, ModuleUpdateRequestDTO request);

    ModuleResponseDTO create(ModuleCreateRequestDTO request);

    ModuleResponseDTO getById(Long id);

    Page<ModuleResponseDTO> getAll(Pageable pageable);
}


