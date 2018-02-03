package com.tide.announcement.exception;

import com.tide.common.exception.ResourceNotFoundException;

public class AnnouncementNotFoundException extends ResourceNotFoundException {
    public AnnouncementNotFoundException(final String announcementId) {
        super("Announcement [ " + announcementId + " ] not found");
    }
}
