package com.tide.announcement

import com.tide.announcement.dto.AnnouncementLikeResponse
import com.tide.announcement.service.AnnouncementLikeService
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.hamcrest.Matchers.is
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class AnnouncementLikeControllerTest extends Specification{

    private final AnnouncementLikeService announcementLikeService = Mock(AnnouncementLikeService)
    def mockMvc

    private AnnouncementLikeController underTest

    def setup() {
        underTest = new AnnouncementLikeController(announcementLikeService)
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build()
    }

    def 'get announcement should return 200'() {
        given:
            String announcementId = "1"
            int likes = 100
            int dislikes = 2
            AnnouncementLikeResponse announcementVoteResponse = new AnnouncementLikeResponse(likes, dislikes)
        when:
            def response = mockMvc.perform(get("/announcements/$announcementId/likes"))
        then:
            1 * announcementLikeService.getVoteCountForAnnouncement(announcementId) >> Optional.of(announcementVoteResponse)

            response
                    .andExpect(status().isOk())
                    .andExpect(jsonPath('$.likes', is(likes)))
                    .andExpect(jsonPath('$.dislikes', is(dislikes)))
    }

    def 'likeAnnouncement should return 201'() {
        given:
            String announcementId = "1"
        when:
            def response = mockMvc.perform(post("/announcements/$announcementId/likes"))
        then:
            1 * announcementLikeService.likeAnnouncement(announcementId)

            response
                    .andExpect(status().isCreated())
    }

    def 'dislikeAnnouncement should return 200'() {
        given:
            String announcementId = "1"
        when:
            def response = mockMvc.perform(delete("/announcements/$announcementId/likes"))
        then:
            1 * announcementLikeService.dislikeAnnouncement(announcementId)

            response
                    .andExpect(status().isOk())
    }
}