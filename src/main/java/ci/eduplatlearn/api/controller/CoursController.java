package ci.eduplatlearn.api.controller;

import ci.eduplatlearn.dto.Enseignant.EnseignantResponseDTO;
import ci.eduplatlearn.dto.cours.CoursCreateRequestDTO;
import ci.eduplatlearn.dto.cours.CoursResponseDTO;
import ci.eduplatlearn.dto.cours.CoursUpdateRequestDTO;
import ci.eduplatlearn.service.CoursService;
import ci.eduplatlearn.service.EnseignantService;
import org.springframework.data.domain.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/cours")
public class CoursController {
    private final CoursService coursService;
    private final EnseignantService enseignantService;

    public CoursController(CoursService coursService, EnseignantService enseignantService) {
        this.coursService = coursService;
        this.enseignantService = enseignantService;
    }

    @Operation(
            summary = "Lister les cours",
            description = "Retourne la liste paginée des cours EduPlatLearn"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des cours"),
            @ApiResponse(responseCode = "400", description = "Paramètres invalides")
    })
    @GetMapping
    public Page<CoursResponseDTO> getAll(@ParameterObject Pageable pageable) {
        return coursService.getAll(pageable);
    }

    @Operation(
            summary = "Obtenir un cours par ID",
            description = "Retourne les détails d'un cours spécifique en fonction de son ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Détails du cours"),
            @ApiResponse(responseCode = "404", description = "Cours non trouvé")
    })
    @GetMapping("/{id}")
    public CoursResponseDTO getById(@PathVariable Long id) {
        return coursService.getById(id);
    }

    @Operation(
            summary = "Créer un cours",
            description = "Permet de créer un nouveau cours en fournissant les informations nécessaires"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cours créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CoursResponseDTO create(@Valid @RequestBody CoursCreateRequestDTO request) {
        return coursService.create(request);
    }

    @Operation(
            summary = "Mettre à jour un cours",
            description = "Permet de mettre à jour les informations d'un cours existant en fonction de son ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cours mis à jour avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Cours non trouvé")
    })
    @PutMapping("/{id}")
    public CoursResponseDTO update(@PathVariable Long id, @Valid @RequestBody CoursUpdateRequestDTO request) {
        return coursService.update(id, request);
    }

    @Operation(
            summary = "Supprimer un cours",
            description = "Permet de supprimer un cours existant en fonction de son ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cours supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Cours non trouvé")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        coursService.delete(id);
    }

    @Operation(
            summary = "Lister les enseignants d'un cours",
            description = "Retourne la liste paginée des enseignants associés à un cours spécifique"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des enseignants du cours"),
            @ApiResponse(responseCode = "404", description = "Cours non trouvé")
    })
    @GetMapping("/{coursId}/enseignants")
    public Page<EnseignantResponseDTO> getEnseignantsByCours(@PathVariable Long coursId, @ParameterObject Pageable pageable) {
        return enseignantService.getByCoursId(coursId, pageable);
    }

}
