package ci.eduplatlearn.api.controller;

import ci.eduplatlearn.dto.Enseignant.EnseignantCreateRequestDTO;
import ci.eduplatlearn.dto.Enseignant.EnseignantResponseDTO;
import ci.eduplatlearn.dto.Enseignant.EnseignantUpdateRequestDTO;
import ci.eduplatlearn.dto.cours.CoursResponseDTO;
import ci.eduplatlearn.service.CoursService;
import ci.eduplatlearn.service.EnseignantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/enseignants")
public class EnseignantController {
    private final EnseignantService enseignantService;
    private final CoursService coursService;

    public EnseignantController(EnseignantService enseignantService, CoursService coursService) {
        this.enseignantService = enseignantService;
        this.coursService = coursService;
    }

    @Operation(
            summary = "Lister les enseignants",
            description = "Retourne la liste des enseignants EduPlatLearn"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des enseignants"),
            @ApiResponse(responseCode = "400", description = "Paramètres invalides")
    })
    @GetMapping
    public Page<EnseignantResponseDTO> getAll(@ParameterObject Pageable pageable) {
        return enseignantService.getAll(pageable);
    }

    @Operation(
            summary = "Obtenir un enseignant par ID",
            description = "Retourne les détails d'un enseignant spécifique en fonction de son ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Détails de l'enseignant"),
            @ApiResponse(responseCode = "404", description = "Enseignant non trouvé")
    })
    @GetMapping("/{id}")
    public EnseignantResponseDTO getById(@PathVariable Long id) {
        return enseignantService.getById(id);
    }

    @Operation(
            summary = "Obtenir les enseignants d'un cours",
            description = "Retourne la liste des enseignants associés à un cours spécifique"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des enseignants du cours"),
            @ApiResponse(responseCode = "404", description = "Cours non trouvé")
    })
    @GetMapping("/cours/{coursId}")
    public Page<EnseignantResponseDTO> getByCoursId(@PathVariable Long coursId, @ParameterObject Pageable pageable) {
        return enseignantService.getByCoursId(coursId, pageable);
    }

    @Operation(
            summary = "Créer un enseignant",
            description = "Permet de créer un nouvel enseignant en fournissant les informations nécessaires"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Enseignant créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnseignantResponseDTO create(@Valid @RequestBody EnseignantCreateRequestDTO request) {
        return enseignantService.create(request);
    }

    @Operation(
            summary = "Mettre à jour un enseignant",
            description = "Permet de mettre à jour les informations d'un enseignant existant en fonction de son ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Enseignant mis à jour avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Enseignant non trouvé")
    })
    @PutMapping("/{id}")
    public EnseignantResponseDTO update(@PathVariable Long id, @Valid @RequestBody EnseignantUpdateRequestDTO request) {
        return enseignantService.update(id, request);
    }

    @Operation(
            summary = "Supprimer un enseignant",
            description = "Permet de supprimer un enseignant existant en fonction de son ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Enseignant supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Enseignant non trouvé")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        enseignantService.delete(id);
    }

    @Operation(
            summary = "Lister les cours d'un enseignant",
            description = "Retourne la liste paginée des cours associés à un enseignant spécifique"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des cours de l'enseignant"),
            @ApiResponse(responseCode = "404", description = "Enseignant non trouvé")
    })
    @GetMapping("/{enseignantId}/cours")
    public Page<CoursResponseDTO> getCoursByEnseignant(@PathVariable Long enseignantId, @ParameterObject Pageable pageable) {
        return coursService.getByEnseignantId(enseignantId, pageable);
    }
}

