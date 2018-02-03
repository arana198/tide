package com.tide.announcement.service.impl;

import com.tide.announcement.dto.AnnouncementLikeResponse;
import com.tide.announcement.exception.AnnouncementNotFoundException;
import com.tide.announcement.service.AnnouncementLikeService;
import com.tide.announcement.service.AnnouncementService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Slf4j
@Service
class AnnouncementLikeServiceImpl implements AnnouncementLikeService {

    private final AnnouncementVoteRepository announcementVoteRepository;
    private final AnnouncementService announcementService;
    private final AnnouncementVoteConverter announcementVoteConverter;

    @Override
    public Optional<AnnouncementLikeResponse> getVoteCountForAnnouncement(final String announcementId) throws AnnouncementNotFoundException {
        log.debug("Getting vote count for announcement: {}", announcementId);
        return announcementVoteRepository.findByAnnouncementId(announcementId)
                .map(announcementVoteConverter::convert);
    }

    @Override
    public void likeAnnouncement(final String announcementId) throws AnnouncementNotFoundException {
        log.debug("Liking an announcement: {}", announcementId);
        announcementService.findByAnnouncementId(announcementId)
                .orElseThrow(() -> {
                    log.info("Announcement [ {} ] not found", announcementId);
                    return new AnnouncementNotFoundException(announcementId);
                });

        AnnouncementVoteBO announcementVoteBO = this.getAnnouncementId(announcementId);
        int likesCount = announcementVoteBO.getLikeCount() + 1;
        announcementVoteBO.setLikeCount(likesCount);
        announcementVoteRepository.save(announcementVoteBO);
        log.debug("Successfully saved like announcement [ {} ]", announcementId);
    }

    @Override
    public void dislikeAnnouncement(final String announcementId) throws AnnouncementNotFoundException {
        log.debug("Disliking an announcement: {}", announcementId);
        announcementService.findByAnnouncementId(announcementId)
                .orElseThrow(() -> {
                    log.info("Announcement [ {} ] not found", announcementId);
                    return new AnnouncementNotFoundException(announcementId);
                });

        AnnouncementVoteBO announcementVoteBO = this.getAnnouncementId(announcementId);
        int dislikeCount = announcementVoteBO.getDislikeCount() + 1;
        announcementVoteBO.setDislikeCount(dislikeCount);
        announcementVoteRepository.save(announcementVoteBO);
        log.debug("Successfully saved dislike announcement [ {} ]", announcementId);
    }

    private AnnouncementVoteBO getAnnouncementId(final String announcementId) {
        return announcementVoteRepository.findByAnnouncementId(announcementId)
                .orElse(this.createAnnouncementBO(announcementId));
    }

    private AnnouncementVoteBO createAnnouncementBO(final String announcementId) {
        return new AnnouncementVoteBO()
                .setAnnouncementId(announcementId)
                .setLikeCount(0)
                .setDislikeCount(0);
    }
}
