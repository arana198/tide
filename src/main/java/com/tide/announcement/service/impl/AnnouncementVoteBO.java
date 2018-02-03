package com.tide.announcement.service.impl;

import com.tide.common.domain.AbstractEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "announcement_votes")
class AnnouncementVoteBO extends AbstractEntity {

    @Column(name = "announcement_id", nullable = false)
    private String announcementId;

    @Column(name = "like_count", nullable = false)
    private int likeCount;

    @Column(name = "dislike_count", nullable = false)
    private int dislikeCount;
}
