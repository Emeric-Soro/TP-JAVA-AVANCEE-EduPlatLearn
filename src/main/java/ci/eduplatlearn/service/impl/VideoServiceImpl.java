package ci.eduplatlearn.service.impl;

import ci.eduplatlearn.api.exception.ResourceNotFoundException;
import ci.eduplatlearn.dto.video.VideoCreateRequestDTO;
import ci.eduplatlearn.dto.video.VideoResponseDTO;
import ci.eduplatlearn.dto.video.VideoUpdateRequestDTO;
import ci.eduplatlearn.entity.Lecon;
import ci.eduplatlearn.entity.Video;
import ci.eduplatlearn.repository.LeconRepository;
import ci.eduplatlearn.repository.VideoRepository;
import ci.eduplatlearn.service.VideoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final LeconRepository leconRepository;

    public VideoServiceImpl(VideoRepository videoRepository, LeconRepository leconRepository) {
        this.videoRepository = videoRepository;
        this.leconRepository = leconRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VideoResponseDTO> getAll(Pageable pageable) {
        return videoRepository.findAll(pageable)
                .map(this::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public VideoResponseDTO getById(Long id) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vidéo introuvable avec l'id : " + id));
        return toResponse(video);
    }

    @Override
    @Transactional
    public VideoResponseDTO create(VideoCreateRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }
        if (request.titre() == null || request.titre().isBlank()) {
            throw new IllegalArgumentException("titre is required");
        }
        if (request.url() == null || request.url().isBlank()) {
            throw new IllegalArgumentException("url is required");
        }
        if (request.dureeSecondes() == null) {
            throw new IllegalArgumentException("dureeSecondes is required");
        }
        if (request.leconId() == null) {
            throw new IllegalArgumentException("leconId is required");
        }

        Lecon lecon = leconRepository.findById(request.leconId())
                .orElseThrow(() -> new ResourceNotFoundException("Leçon introuvable avec l'id : " + request.leconId()));

        Video video = new Video(request.titre(), lecon, request.url(), request.dureeSecondes());
        Video saved = videoRepository.save(video);
        return toResponse(saved);
    }

    @Override
    @Transactional
    public VideoResponseDTO update(Long id, VideoUpdateRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }

        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vidéo introuvable avec l'id : " + id));

        video.setTitre(request.titre());
        video.setUrl(request.url());
        video.setDureeSecondes(request.dureeSecondes());

        Video saved = videoRepository.save(video);
        return toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!videoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vidéo introuvable avec l'id : " + id);
        }
        videoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VideoResponseDTO> getByLeconId(Long leconId, Pageable pageable) {
        return videoRepository.findByLecon_Id(leconId, pageable)
                .map(this::toResponse);
    }

    private VideoResponseDTO toResponse(Video video) {
        return new VideoResponseDTO(
                video.getId(),
                video.getTitre(),
                video.getUrl(),
                video.getDureeSecondes(),
                video.getLecon().getId(),
                video.getLecon().getTitre(),
                video.getCreatedAt(),
                video.getUpdatedAt()
        );
    }
}

