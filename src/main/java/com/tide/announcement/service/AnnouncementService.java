package com.tide.announcement.service;

import com.tide.announcement.dto.AnnouncementResponse;

import java.util.Optional;

public interface AnnouncementService {
    Optional<AnnouncementResponse> findByAnnouncementId(String announcementId);
}
