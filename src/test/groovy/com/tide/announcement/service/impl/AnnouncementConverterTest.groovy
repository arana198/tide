package com.tide.announcement.service.impl

import com.tide.announcement.dto.AnnouncementResponse
import spock.lang.Specification

class AnnouncementConverterTest extends Specification {

    private static final String ANNOUNCEMENT_ID = "1"

    private AnnouncementBO announcementBO
    private AnnouncementResponse announcementResponse

    private AnnouncementConverter underTest

    def setup() {
        announcementBO = Mock(AnnouncementBO)
        announcementResponse = Mock(AnnouncementResponse)

        underTest = new AnnouncementConverter()
    }

    def "should convert AnnouncementBO to AnnouncementResponse"() {
        given:
            announcementBO.getId() >> ANNOUNCEMENT_ID
        when:
            AnnouncementResponse result = underTest.convert(announcementBO)
        then:
            result != null
            result.getAnnouncmentId() == ANNOUNCEMENT_ID
    }
}
