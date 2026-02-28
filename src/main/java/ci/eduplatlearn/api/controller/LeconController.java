package ci.eduplatlearn.api.controller;

import ci.eduplatlearn.dto.lecon.LeconCreateRequestDTO;
import ci.eduplatlearn.dto.lecon.LeconResponseDTO;
import ci.eduplatlearn.dto.lecon.LeconUpdateRequestDTO;
import ci.eduplatlearn.service.LeconService;
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
@RequestMapping(value = "/api/lecons")
public class LeconController {
    private final LeconService leconService;

    public LeconController(LeconService leconService) {
        this.leconService = leconService;
    }

    @Operation(
            summary = "Lister les leçons",
            description = "Retourne la liste paginée des leçons"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des leçons"),
            @ApiResponse(responseCode = "400", description = "Paramètres invalides")
    })
    @GetMapping
    public Page<LeconResponseDTO> getAll(@ParameterObject Pageable pageable) {
        return leconService.getAll(pageable);
    }

    @Operation(
            summary = "Obtenir une leçon par ID",
            description = "Retourne les détails d'une leçon spécifique en fonction de son ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Détails de la leçon"),
            @ApiResponse(responseCode = "404", description = "Leçon non trouvée")
    })
    @GetMapping("/{id}")
    public LeconResponseDTO getById(@PathVariable Long id) {
        return leconService.getById(id);
    }

    @Operation(
            summary = "Obtenir les leçons d'un module",
            description = "Retourne la liste des leçons d'un module spécifique"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des leçons du module"),
            @ApiResponse(responseCode = "404", description = "Module non trouvé")
    })
    @GetMapping("/module/{moduleId}")
    public Page<LeconResponseDTO> getByModuleId(@PathVariable Long moduleId, @ParameterObject Pageable pageable) {
        return leconService.getByModuleId(moduleId, pageable);
    }

    @Operation(
            summary = "Créer une leçon",
            description = "Permet de créer une nouvelle leçon en fournissant les informations nécessaires"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Leçon créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LeconResponseDTO create(@Valid @RequestBody LeconCreateRequestDTO request) {
        return leconService.create(request);
    }

    @Operation(
            summary = "Mettre à jour une leçon",
            description = "Permet de mettre à jour les informations d'une leçon existante en fonction de son ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Leçon mise à jour avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Leçon non trouvée")
    })
    @PutMapping("/{id}")
    public LeconResponseDTO update(@PathVariable Long id, @Valid @RequestBody LeconUpdateRequestDTO request) {
        return leconService.update(id, request);
    }

    @Operation(
            summary = "Supprimer une leçon",
            description = "Permet de supprimer une leçon existante en fonction de son ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Leçon supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Leçon non trouvée")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        leconService.delete(id);
    }
}

