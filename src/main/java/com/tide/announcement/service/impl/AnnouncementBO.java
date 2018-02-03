package com.tide.announcement.service.impl;

import com.tide.common.domain.AbstractTimestampEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "announcements")
class AnnouncementBO extends AbstractTimestampEntity {

}
