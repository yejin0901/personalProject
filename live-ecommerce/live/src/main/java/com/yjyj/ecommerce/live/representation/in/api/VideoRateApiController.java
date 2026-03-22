package com.yjyj.ecommerce.live.representation.in.api;


import com.yjyj.ecommerce.live.application.port.in.VideoLikeUseCase;
import com.yjyj.ecommerce.live.domain.user.User;
import com.yjyj.ecommerce.live.domain.video.VideoRate;
import com.yjyj.ecommerce.live.representation.in.api.dto.VideoRateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/videos/rate")
@Tag(name = "라이브 영상 API")
public class VideoRateApiController {
    private final VideoLikeUseCase videoLikeUseCase;

    public VideoRateApiController(VideoLikeUseCase videoLikeUseCase) {
        this.videoLikeUseCase = videoLikeUseCase;
    }

    @PostMapping
    @Operation(summary = "라이브 영상 평가")
    void rateVideo(
        @ModelAttribute User user,
        @RequestParam String videoId,
        @RequestParam VideoRate rating
    ) {
        switch (rating) {
            case like:
                videoLikeUseCase.likeVideo(videoId, user.getId());
                break;
            case none:
                videoLikeUseCase.unlikeVideo(videoId, user.getId());
                break;
        }
    }

    @GetMapping
    @Operation(summary = "라이브 영상 평가 조회")
    VideoRateResponse getRate(
        @ModelAttribute User user,
        @RequestParam String videoId
    ) {
        var rate = videoLikeUseCase.isLikedVideo(videoId, user.getId()) ? VideoRate.like : VideoRate.none;
        return new VideoRateResponse(videoId, rate);
    }
}
