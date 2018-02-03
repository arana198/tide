package com.tide.announcement.service.impl;

import com.tide.common.repository.BaseRepository;

import java.util.Optional;

public interface AnnouncementVoteRepository extends BaseRepository<AnnouncementVoteBO, String> {
    Optional<AnnouncementVoteBO> findByAnnouncementId(String announcementId);
}
