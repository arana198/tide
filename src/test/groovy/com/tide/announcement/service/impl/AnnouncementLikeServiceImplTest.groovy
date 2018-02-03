package com.tide.announcement.service.impl

import com.tide.announcement.dto.AnnouncementResponse
import com.tide.announcement.dto.AnnouncementLikeResponse
import com.tide.announcement.exception.AnnouncementNotFoundException
import com.tide.announcement.service.AnnouncementLikeService
import com.tide.announcement.service.AnnouncementService
import spock.lang.Specification

class AnnouncementLikeServiceImplTest extends Specification {

    private AnnouncementVoteRepository announcementVoteRepository = Mock(AnnouncementVoteRepository)
    private AnnouncementService announcementService = Mock(AnnouncementService)
    private AnnouncementVoteConverter announcementVoteConverter = Mock(AnnouncementVoteConverter)

    private AnnouncementVoteBO announcementVoteBO
    private AnnouncementResponse announcementResponse
    private AnnouncementLikeResponse likeCountResponse

    private AnnouncementLikeService underTest

    def setup() {
        announcementVoteBO = Mock(AnnouncementVoteBO)
        announcementResponse = Mock(AnnouncementResponse)
        likeCountResponse = Mock(AnnouncementLikeResponse)

        underTest = new AnnouncementLikeServiceImpl(announcementVoteRepository,
                announcementService,
                announcementVoteConverter)
    }

    def "should return announcement vote response"() {
        given:
            String announcementId = "1"
        when:
            Optional<AnnouncementLikeResponse> result = underTest.getVoteCountForAnnouncement(announcementId)
        then:
            1 * announcementVoteRepository.findByAnnouncementId(announcementId) >> Optional.of(announcementVoteBO)
            1 * announcementVoteConverter.convert(announcementVoteBO) >> likeCountResponse
            result.isPresent()
            AnnouncementLikeResponse likeCountResponseResult = result.get()
            likeCountResponseResult == likeCountResponse
    }

    def "should return empty optional when announcement vote does not exists"() {
        given:
            String announcementId = "1"
        when:
            Optional<AnnouncementLikeResponse> result = underTest.getVoteCountForAnnouncement(announcementId)
        then:
            1 * announcementVoteRepository.findByAnnouncementId(announcementId) >> Optional.empty()
            0 * announcementVoteConverter.convert(announcementVoteBO) >> likeCountResponse
            !result.isPresent()
    }

    def "should like announcement when announcement vote already exits"() {
        given:
            String announcementId = "1"
        when:
            underTest.likeAnnouncement(announcementId)
        then:
            1 * announcementService.findByAnnouncementId(announcementId) >> Optional.of(announcementResponse)
            1 * announcementVoteRepository.findByAnnouncementId(announcementId) >> Optional.of(announcementVoteBO)
            1 * announcementVoteRepository.save(announcementVoteBO) >> likeCountResponse
    }

    def "should like announcement when announcement vote does not exits"() {
        given:
            String announcementId = "1"
        when:
            underTest.likeAnnouncement(announcementId)
        then:
            1 * announcementService.findByAnnouncementId(announcementId) >> Optional.of(announcementResponse)
            1 * announcementVoteRepository.findByAnnouncementId(announcementId) >> Optional.empty()
            1 * announcementVoteRepository.save(_ as AnnouncementVoteBO) >> likeCountResponse
    }

    def "like announcement should throw AnnouncementNotFoundException when announcement does not exits"() {
        given:
            String announcementId = "1"
        when:
            underTest.likeAnnouncement(announcementId)
        then:
            1 * announcementService.findByAnnouncementId(announcementId) >> Optional.empty()
            0 * announcementVoteRepository.findByAnnouncementId(announcementId) >> Optional.empty()
            0 * announcementVoteRepository.save(_ as AnnouncementVoteBO) >> likeCountResponse
            def ex = thrown(AnnouncementNotFoundException)
            ex.message == "Announcement [ $announcementId ] not found"
    }

    def "should dislike announcement when announcement vote already exits"() {
        given:
            String announcementId = "1"
        when:
            underTest.dislikeAnnouncement(announcementId)
        then:
            1 * announcementService.findByAnnouncementId(announcementId) >> Optional.of(announcementResponse)
            1 * announcementVoteRepository.findByAnnouncementId(announcementId) >> Optional.of(announcementVoteBO)
            1 * announcementVoteRepository.save(announcementVoteBO) >> likeCountResponse
    }

    def "should dislike announcement when announcement vote does not exits"() {
        given:
            String announcementId = "1"
        when:
            underTest.dislikeAnnouncement(announcementId)
        then:
            1 * announcementService.findByAnnouncementId(announcementId) >> Optional.of(announcementResponse)
            1 * announcementVoteRepository.findByAnnouncementId(announcementId) >> Optional.empty()
            1 * announcementVoteRepository.save(_ as AnnouncementVoteBO) >> likeCountResponse
    }

    def "dislike announcement should throw AnnouncementNotFoundException when announcement does not exits"() {
        given:
            String announcementId = "1"
        when:
            underTest.dislikeAnnouncement(announcementId)
        then:
            1 * announcementService.findByAnnouncementId(announcementId) >> Optional.empty()
            0 * announcementVoteRepository.findByAnnouncementId(announcementId) >> Optional.empty()
            0 * announcementVoteRepository.save(_ as AnnouncementVoteBO) >> likeCountResponse
            def ex = thrown(AnnouncementNotFoundException)
            ex.message == "Announcement [ $announcementId ] not found"
    }
}
