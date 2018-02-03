package com.tide.announcement.service.impl

import com.tide.announcement.dto.AnnouncementLikeResponse
import spock.lang.Specification

class AnnouncementVoteConverterTest extends Specification {

    private static final int LIKE_COUNT = 100
    private static final int DISLIKE_COUNT = 2

    private AnnouncementVoteBO announcementVoteBO
    private AnnouncementLikeResponse announcementVoteResponse

    private AnnouncementVoteConverter underTest

    def setup() {
        announcementVoteBO = Mock(AnnouncementVoteBO)
        announcementVoteResponse = Mock(AnnouncementLikeResponse)

        underTest = new AnnouncementVoteConverter()
    }

    def "should convert AnnouncementVoteBO to AnnouncementVoteResponse"() {
        given:
            announcementVoteBO.getLikeCount() >> LIKE_COUNT
            announcementVoteBO.getDislikeCount() >> DISLIKE_COUNT
        when:
            AnnouncementLikeResponse result = underTest.convert(announcementVoteBO)
        then:
            result != null
            result.getLikes() == LIKE_COUNT
            result.getDislikes() == DISLIKE_COUNT
    }
}
