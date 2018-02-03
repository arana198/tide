package com.tide.announcement.service.impl;

import com.tide.announcement.dto.AnnouncementResponse;
import com.tide.announcement.service.AnnouncementService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementConverter announcementConverter;

    @Override
    public Optional<AnnouncementResponse> findByAnnouncementId(final String announcementId) {
        return announcementRepository.findById(announcementId)
                .map(announcementConverter::convert);
    }
}
