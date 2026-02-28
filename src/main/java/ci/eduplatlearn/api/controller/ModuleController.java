package ci.eduplatlearn.api.controller;

import ci.eduplatlearn.dto.lecon.LeconResponseDTO;
import ci.eduplatlearn.dto.module.ModuleCreateRequestDTO;
import ci.eduplatlearn.dto.module.ModuleResponseDTO;
import ci.eduplatlearn.dto.module.ModuleUpdateRequestDTO;
import ci.eduplatlearn.service.LeconService;
import ci.eduplatlearn.service.ModuleService;
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
@RequestMapping(value = "/api/modules")
public class ModuleController {
    private final ModuleService moduleService;
    private final LeconService leconService;

    public ModuleController(ModuleService moduleService, LeconService leconService) {
        this.moduleService = moduleService;
        this.leconService = leconService;
    }

    @Operation(
            summary = "Lister les modules",
            description = "Retourne la liste paginée des modules"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des modules"),
            @ApiResponse(responseCode = "400", description = "Paramètres invalides")
    })
    @GetMapping
    public Page<ModuleResponseDTO> getAll(@ParameterObject Pageable pageable) {
        return moduleService.getAll(pageable);
    }

    @Operation(
            summary = "Obtenir un module par ID",
            description = "Retourne les détails d'un module spécifique en fonction de son ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Détails du module"),
            @ApiResponse(responseCode = "404", description = "Module non trouvé")
    })
    @GetMapping("/{id}")
    public ModuleResponseDTO getById(@PathVariable Long id) {
        return moduleService.getById(id);
    }

    @Operation(
            summary = "Obtenir les modules d'un cours",
            description = "Retourne la liste des modules d'un cours spécifique"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des modules du cours"),
            @ApiResponse(responseCode = "404", description = "Cours non trouvé")
    })
    @GetMapping("/cours/{coursId}")
    public Page<ModuleResponseDTO> getByCoursId(@PathVariable Long coursId, @ParameterObject Pageable pageable) {
        return moduleService.getByCoursId(coursId, pageable);
    }

    @Operation(
            summary = "Créer un module",
            description = "Permet de créer un nouveau module en fournissant les informations nécessaires"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Module créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ModuleResponseDTO create(@Valid @RequestBody ModuleCreateRequestDTO request) {
        return moduleService.create(request);
    }

    @Operation(
            summary = "Mettre à jour un module",
            description = "Permet de mettre à jour les informations d'un module existant en fonction de son ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Module mis à jour avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Module non trouvé")
    })
    @PutMapping("/{id}")
    public ModuleResponseDTO update(@PathVariable Long id, @Valid @RequestBody ModuleUpdateRequestDTO request) {
        return moduleService.update(id, request);
    }

    @Operation(
            summary = "Supprimer un module",
            description = "Permet de supprimer un module existant en fonction de son ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Module supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Module non trouvé")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        moduleService.delete(id);
    }

    @Operation(
            summary = "Lister les leçons d'un module",
            description = "Retourne la liste paginée des leçons d'un module spécifique"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des leçons du module"),
            @ApiResponse(responseCode = "404", description = "Module non trouvé")
    })
    @GetMapping("/{moduleId}/lecons")
    public Page<LeconResponseDTO> getLeconsByModule(@PathVariable Long moduleId, @ParameterObject Pageable pageable) {
        return leconService.getByModuleId(moduleId, pageable);
    }
}

