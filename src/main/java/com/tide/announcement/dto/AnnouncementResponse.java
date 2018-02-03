package com.tide.announcement.dto;

import com.tide.common.dto.BaseResponseDomain;
import lombok.Data;

@Data
public class AnnouncementResponse extends BaseResponseDomain {
    private final String announcmentId;
}
