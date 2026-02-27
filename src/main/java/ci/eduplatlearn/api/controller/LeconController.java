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
            summary = "Lister les leÃ§ons",
            description = "Retourne la liste paginÃ©e des leÃ§ons"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des leÃ§ons"),
            @ApiResponse(responseCode = "400", description = "ParamÃ¨tres invalides")
    })
    @GetMapping
    public Page<LeconResponseDTO> getAll(@ParameterObject Pageable pageable) {
        return leconService.getAll(pageable);
    }

    @Operation(
            summary = "Obtenir une leÃ§on par ID",
            description = "Retourne les dÃ©tails d'une leÃ§on spÃ©cifique en fonction de son ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "DÃ©tails de la leÃ§on"),
            @ApiResponse(responseCode = "404", description = "LeÃ§on non trouvÃ©e")
    })
    @GetMapping("/{id}")
    public LeconResponseDTO getById(@PathVariable Long id) {
        return leconService.getById(id);
    }

    @Operation(
            summary = "Obtenir les leÃ§ons d'un module",
            description = "Retourne la liste des leÃ§ons d'un module spÃ©cifique"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des leÃ§ons du module"),
            @ApiResponse(responseCode = "404", description = "Module non trouvÃ©")
    })
    @GetMapping("/module/{moduleId}")
    public Page<LeconResponseDTO> getByModuleId(@PathVariable Long moduleId, @ParameterObject Pageable pageable) {
        return leconService.getByModuleId(moduleId, pageable);
    }

    @Operation(
            summary = "CrÃ©er une leÃ§on",
            description = "Permet de crÃ©er une nouvelle leÃ§on en fournissant les informations nÃ©cessaires"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "LeÃ§on crÃ©Ã©e avec succÃ¨s"),
            @ApiResponse(responseCode = "400", description = "DonnÃ©es invalides")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LeconResponseDTO create(@Valid @RequestBody LeconCreateRequestDTO request) {
        return leconService.create(request);
    }

    @Operation(
            summary = "Mettre Ã  jour une leÃ§on",
            description = "Permet de mettre Ã  jour les informations d'une leÃ§on existante en fonction de son ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "LeÃ§on mise Ã  jour avec succÃ¨s"),
            @ApiResponse(responseCode = "400", description = "DonnÃ©es invalides"),
            @ApiResponse(responseCode = "404", description = "LeÃ§on non trouvÃ©e")
    })
    @PutMapping("/{id}")
    public LeconResponseDTO update(@PathVariable Long id, @Valid @RequestBody LeconUpdateRequestDTO request) {
        return leconService.update(id, request);
    }

    @Operation(
            summary = "Supprimer une leÃ§on",
            description = "Permet de supprimer une leÃ§on existante en fonction de son ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "LeÃ§on supprimÃ©e avec succÃ¨s"),
            @ApiResponse(responseCode = "404", description = "LeÃ§on non trouvÃ©e")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        leconService.delete(id);
    }
}

