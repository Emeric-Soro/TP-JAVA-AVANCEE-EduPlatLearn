package ci.eduplatlearn.api.controller;

import ci.eduplatlearn.dto.texte.TexteCreateRequestDTO;
import ci.eduplatlearn.dto.texte.TexteResponseDTO;
import ci.eduplatlearn.dto.texte.TexteUpdateRequestDTO;
import ci.eduplatlearn.service.TexteService;
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
@RequestMapping(value = "/api/textes")
public class TexteController {
    private final TexteService texteService;

    public TexteController(TexteService texteService) {
        this.texteService = texteService;
    }

    @Operation(
            summary = "Lister les textes",
            description = "Retourne la liste paginée des textes"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des textes"),
            @ApiResponse(responseCode = "400", description = "Paramètres invalides")
    })
    @GetMapping
    public Page<TexteResponseDTO> getAll(@ParameterObject Pageable pageable) {
        return texteService.getAll(pageable);
    }

    @Operation(
            summary = "Obtenir un texte par ID",
            description = "Retourne les détails d'un texte spécifique en fonction de son ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Détails du texte"),
            @ApiResponse(responseCode = "404", description = "Texte non trouvé")
    })
    @GetMapping("/{id}")
    public TexteResponseDTO getById(@PathVariable Long id) {
        return texteService.getById(id);
    }

    @Operation(
            summary = "Obtenir les textes d'une leçon",
            description = "Retourne la liste des textes d'une leçon spécifique"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des textes de la leçon"),
            @ApiResponse(responseCode = "404", description = "Leçon non trouvée")
    })
    @GetMapping("/lecon/{leconId}")
    public Page<TexteResponseDTO> getByLeconId(@PathVariable Long leconId, @ParameterObject Pageable pageable) {
        return texteService.getByLeconId(leconId, pageable);
    }

    @Operation(
            summary = "Créer un texte",
            description = "Permet de créer un nouveau texte en fournissant les informations nécessaires"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Texte créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TexteResponseDTO create(@Valid @RequestBody TexteCreateRequestDTO request) {
        return texteService.create(request);
    }

    @Operation(
            summary = "Mettre à jour un texte",
            description = "Permet de mettre à jour les informations d'un texte existant en fonction de son ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Texte mis à jour avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Texte non trouvé")
    })
    @PutMapping("/{id}")
    public TexteResponseDTO update(@PathVariable Long id, @Valid @RequestBody TexteUpdateRequestDTO request) {
        return texteService.update(id, request);
    }

    @Operation(
            summary = "Supprimer un texte",
            description = "Permet de supprimer un texte existant en fonction de son ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Texte supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Texte non trouvé")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        texteService.delete(id);
    }
}

