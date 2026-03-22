package com.yjyj.ecommerce.live.representation.in.api;


import com.yjyj.ecommerce.live.application.port.in.VideoUseCase;
import com.yjyj.ecommerce.live.domain.video.Video;
import com.yjyj.ecommerce.live.representation.in.api.dto.CommandResponse;
import com.yjyj.ecommerce.live.representation.in.api.dto.VideoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/videos")
@Tag(name = "라이브 영상 API")
public class VideoApiController {
    private final VideoUseCase videoUseCase;

    public VideoApiController(VideoUseCase videoUseCase) {
        this.videoUseCase = videoUseCase;
    }

    @GetMapping("{videoId}")
    @Operation(summary = "라이브 영상 조회")
    public Video getVideo(@PathVariable String videoId) {
        return videoUseCase.getVideo(videoId);
    }

    @GetMapping(params = "channelId")
    @Operation(summary = "라이브 영상 리스트 조회")
    public List<Video> listVideo(@RequestParam String channelId) {
        return videoUseCase.listVideos(channelId);
    }

    @PostMapping
    @Operation(summary = "라이브 영상 생성")
    public CommandResponse createVideo(@RequestBody VideoRequest videoRequest) {
        var video = videoUseCase.createVideo(videoRequest);
        return new CommandResponse(video.getId());
    }

    @PostMapping("{videoId}/view")
    @Operation(summary = "라이브 영상 조회수 증가")
    public void increaseVideoViewCount(@PathVariable String videoId) {
        videoUseCase.increaseViewCount(videoId);
    }
}
