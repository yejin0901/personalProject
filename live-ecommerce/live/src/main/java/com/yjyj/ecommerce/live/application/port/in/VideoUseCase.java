package com.yjyj.ecommerce.live.application.port.in;

import com.yjyj.ecommerce.live.domain.video.Video;
import com.yjyj.ecommerce.live.representation.in.api.dto.VideoRequest;
import java.util.List;

public interface VideoUseCase {
    Video getVideo(String videoId);

    List<Video> listVideos(String channelId);

    Video createVideo(VideoRequest videoRequest);

    void increaseViewCount(String videoId);
}
