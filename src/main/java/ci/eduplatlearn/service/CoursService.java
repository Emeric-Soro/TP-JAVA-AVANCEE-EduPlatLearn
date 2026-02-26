package ci.eduplatlearn.service;


import ci.eduplatlearn.dto.cours.CoursCreateRequestDTO;
import ci.eduplatlearn.dto.cours.CoursResponseDTO;
import ci.eduplatlearn.dto.cours.CoursUpdateRequestDTO;
import ci.eduplatlearn.entity.Cours;

import java.util.List;

public interface CoursService  {
    List<CoursResponseDTO> getAll();
    CoursResponseDTO getById(Long id);
    CoursResponseDTO create(CoursCreateRequestDTO request);
    CoursResponseDTO update(Long id, CoursUpdateRequestDTO request);
    void delete(Long id);
}
