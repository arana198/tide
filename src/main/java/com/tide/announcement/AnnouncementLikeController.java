package com.tide.announcement;

import com.tide.announcement.dto.AnnouncementLikeResponse;
import com.tide.announcement.exception.AnnouncementVoteNotFoundException;
import com.tide.announcement.service.AnnouncementLikeService;
import com.tide.common.exception.ResourceNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@Slf4j
@Controller
@RequestMapping("/announcements/{announcementId}/likes")
public class AnnouncementLikeController {

    private final AnnouncementLikeService announcementLikeService;


    @ApiOperation(value = "View vote count for an announcement", response = AnnouncementLikeResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved announcement vote response"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping
    public ResponseEntity<AnnouncementLikeResponse> getAnnouncementLike(@PathVariable final String announcementId)
            throws ResourceNotFoundException {

        log.info("Getting like count for announcement: {}", announcementId);
        AnnouncementLikeResponse response = announcementLikeService.getVoteCountForAnnouncement(announcementId)
                .orElseThrow(() -> new AnnouncementVoteNotFoundException(announcementId));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Like an announcement")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully liked an announcement"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping
    public ResponseEntity<Void> likeAnnouncement(@PathVariable final String announcementId)
            throws ResourceNotFoundException {

        log.info("Liking an announcement: {}", announcementId);
        announcementLikeService.likeAnnouncement(announcementId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Dislike an announcement")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully disliked an announcement"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @DeleteMapping
    public ResponseEntity<Void> dislikeAnnouncement(@PathVariable final String announcementId)
            throws ResourceNotFoundException {

        log.info("Getting like count for announcement: {}", announcementId);
        announcementLikeService.dislikeAnnouncement(announcementId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
