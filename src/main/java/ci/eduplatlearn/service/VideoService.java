package ci.eduplatlearn.service;

import ci.eduplatlearn.dto.video.VideoCreateRequestDTO;
import ci.eduplatlearn.dto.video.VideoResponseDTO;
import ci.eduplatlearn.dto.video.VideoUpdateRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VideoService {
    Page<VideoResponseDTO> getAll(Pageable pageable);

    VideoResponseDTO getById(Long id);

    VideoResponseDTO create(VideoCreateRequestDTO request);

    VideoResponseDTO update(Long id, VideoUpdateRequestDTO request);

    void delete(Long id);

    Page<VideoResponseDTO> getByLeconId(Long leconId, Pageable pageable);
}

