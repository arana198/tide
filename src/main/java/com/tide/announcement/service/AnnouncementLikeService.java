package com.tide.announcement.service;

import com.tide.announcement.dto.AnnouncementLikeResponse;
import com.tide.announcement.exception.AnnouncementNotFoundException;

import java.util.Optional;

public interface AnnouncementLikeService {

    Optional<AnnouncementLikeResponse> getVoteCountForAnnouncement(String announcementId) throws AnnouncementNotFoundException;

    void likeAnnouncement(String announcementId) throws AnnouncementNotFoundException;

    void dislikeAnnouncement(String announcementId) throws AnnouncementNotFoundException;
}
