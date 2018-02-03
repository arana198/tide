package com.tide.announcement.exception;

import com.tide.common.exception.ResourceNotFoundException;

public class AnnouncementVoteNotFoundException extends ResourceNotFoundException {
    public AnnouncementVoteNotFoundException(final String announcementId) {
        super("Announcement vote for announcement [ " + announcementId + " ] not found");
    }
}
