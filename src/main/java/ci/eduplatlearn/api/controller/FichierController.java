package ci.eduplatlearn.api.controller;

import ci.eduplatlearn.dto.fichier.FichierCreateRequestDTO;
import ci.eduplatlearn.dto.fichier.FichierResponseDTO;
import ci.eduplatlearn.dto.fichier.FichierUpdateRequestDTO;
import ci.eduplatlearn.service.FichierService;
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
@RequestMapping(value = "/api/fichiers")
public class FichierController {
    private final FichierService fichierService;

    public FichierController(FichierService fichierService) {
        this.fichierService = fichierService;
    }

    @Operation(
            summary = "Lister les fichiers",
            description = "Retourne la liste paginée des fichiers"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des fichiers"),
            @ApiResponse(responseCode = "400", description = "ParamÃ¨tres invalides")
    })
    @GetMapping
    public Page<FichierResponseDTO> getAll(@ParameterObject Pageable pageable) {
        return fichierService.getAll(pageable);
    }

    @Operation(
            summary = "Obtenir un fichier par ID",
            description = "Retourne les détails d'un fichier spécifique en fonction de son ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Détails du fichier"),
            @ApiResponse(responseCode = "404", description = "Fichier non trouvé")
    })
    @GetMapping("/{id}")
    public FichierResponseDTO getById(@PathVariable Long id) {
        return fichierService.getById(id);
    }

    @Operation(
            summary = "Obtenir les fichiers d'une leÃ§on",
            description = "Retourne la liste des fichiers d'une leÃ§on spécifique"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des fichiers de la leÃ§on"),
            @ApiResponse(responseCode = "404", description = "LeÃ§on non trouvée")
    })
    @GetMapping("/lecon/{leconId}")
    public Page<FichierResponseDTO> getByLeconId(@PathVariable Long leconId, @ParameterObject Pageable pageable) {
        return fichierService.getByLeconId(leconId, pageable);
    }

    @Operation(
            summary = "Créer un fichier",
            description = "Permet de créer un nouveau fichier en fournissant les informations nécessaires"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Fichier créé avec succÃ¨s"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FichierResponseDTO create(@Valid @RequestBody FichierCreateRequestDTO request) {
        return fichierService.create(request);
    }

    @Operation(
            summary = "Mettre Ã  jour un fichier",
            description = "Permet de mettre Ã  jour les informations d'un fichier existant en fonction de son ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Fichier mis Ã  jour avec succÃ¨s"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Fichier non trouvé")
    })
    @PutMapping("/{id}")
    public FichierResponseDTO update(@PathVariable Long id, @Valid @RequestBody FichierUpdateRequestDTO request) {
        return fichierService.update(id, request);
    }

    @Operation(
            summary = "Supprimer un fichier",
            description = "Permet de supprimer un fichier existant en fonction de son ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Fichier supprimé avec succÃ¨s"),
            @ApiResponse(responseCode = "404", description = "Fichier non trouvé")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        fichierService.delete(id);
    }
}

