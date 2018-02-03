package com.tide.announcement.service.impl;

import com.tide.announcement.dto.AnnouncementLikeResponse;
import com.tide.common.converter.BaseConverter;
import org.springframework.stereotype.Service;

@Service
class AnnouncementVoteConverter implements BaseConverter<AnnouncementVoteBO, AnnouncementLikeResponse> {
    @Override
    public AnnouncementLikeResponse convert(final AnnouncementVoteBO source) {
        return new AnnouncementLikeResponse(source.getLikeCount(), source.getDislikeCount());
    }
}
