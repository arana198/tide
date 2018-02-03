package com.tide.announcement.dto;

import com.tide.common.dto.BaseResponseDomain;
import lombok.Data;

@Data
public class AnnouncementLikeResponse extends BaseResponseDomain {
    private final int likes;
    private final int dislikes;
}
