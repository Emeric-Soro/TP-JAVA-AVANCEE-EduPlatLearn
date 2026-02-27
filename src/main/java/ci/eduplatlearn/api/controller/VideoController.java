package ci.eduplatlearn.api.controller;

import ci.eduplatlearn.dto.video.VideoCreateRequestDTO;
import ci.eduplatlearn.dto.video.VideoResponseDTO;
import ci.eduplatlearn.dto.video.VideoUpdateRequestDTO;
import ci.eduplatlearn.service.VideoService;
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
@RequestMapping(value = "/api/videos")
public class VideoController {
    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @Operation(
            summary = "Lister les vidéos",
            description = "Retourne la liste paginée des vidéos"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des vidéos"),
            @ApiResponse(responseCode = "400", description = "Paramètres invalides")
    })
    @GetMapping
    public Page<VideoResponseDTO> getAll(@ParameterObject Pageable pageable) {
        return videoService.getAll(pageable);
    }

    // ...existing code...

    @GetMapping("/lecon/{leconId}")
    public Page<VideoResponseDTO> getByLeconId(@PathVariable Long leconId, @ParameterObject Pageable pageable) {
        return videoService.getByLeconId(leconId, pageable);
    }

    @Operation(
            summary = "Créer une vidéo",
            description = "Permet de créer une nouvelle vidéo en fournissant les informations nécessaires"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Vidéo créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VideoResponseDTO create(@Valid @RequestBody VideoCreateRequestDTO request) {
        return videoService.create(request);
    }

    @Operation(
            summary = "Mettre à jour une vidéo",
            description = "Permet de mettre à jour les informations d'une vidéo existante en fonction de son ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vidéo mise à jour avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Vidéo non trouvée")
    })
    @PutMapping("/{id}")
    public VideoResponseDTO update(@PathVariable Long id, @Valid @RequestBody VideoUpdateRequestDTO request) {
        return videoService.update(id, request);
    }

    @Operation(
            summary = "Supprimer une vidéo",
            description = "Permet de supprimer une vidéo existante en fonction de son ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Vidéo supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Vidéo non trouvée")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        videoService.delete(id);
    }
}

