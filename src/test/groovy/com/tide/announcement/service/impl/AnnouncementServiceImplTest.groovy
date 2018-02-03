package com.tide.announcement.service.impl

import com.tide.announcement.dto.AnnouncementResponse
import com.tide.announcement.service.AnnouncementService
import spock.lang.Specification

class AnnouncementServiceImplTest extends Specification {

    private AnnouncementRepository announcementRepository = Mock(AnnouncementRepository)
    private AnnouncementConverter announcementConverter = Mock(AnnouncementConverter)

    private AnnouncementBO announcementBO
    private AnnouncementResponse announcementResponse

    private AnnouncementService underTest

    def setup() {
        announcementBO = Mock(AnnouncementBO)
        announcementResponse = Mock(AnnouncementResponse)

        underTest = new AnnouncementServiceImpl(announcementRepository, announcementConverter)
    }

    def "should return announcement response"() {
        given:
            String announcementId = "1"
        when:
            Optional<AnnouncementResponse> result = underTest.findByAnnouncementId(announcementId)
        then:
            1 * announcementRepository.findById(announcementId) >> Optional.of(announcementBO)
            1 * announcementConverter.convert(announcementBO) >> announcementResponse
            result.isPresent()
            AnnouncementResponse announcementResponseResult = result.get()
            announcementResponseResult == announcementResponse
    }

    def "should return empty optional when announcement does not exists"() {
        given:
            String announcementId = "1"
        when:
            Optional<AnnouncementResponse> result = underTest.findByAnnouncementId(announcementId)
        then:
            1 * announcementRepository.findById(announcementId) >> Optional.empty()
            0 * announcementConverter.convert(announcementBO) >> announcementResponse
            !result.isPresent()
    }
}
