package com.tide.announcement.service.impl;

import com.tide.announcement.dto.AnnouncementResponse;
import com.tide.common.converter.BaseConverter;
import org.springframework.stereotype.Service;

@Service
class AnnouncementConverter implements BaseConverter<AnnouncementBO, AnnouncementResponse> {
    @Override
    public AnnouncementResponse convert(final AnnouncementBO source) {
        return new AnnouncementResponse(source.getId());
    }
}
