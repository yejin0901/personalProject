package com.yjyj.ecommerce.live.application.port.out;


import com.yjyj.ecommerce.live.domain.video.Video;

public interface SaveVideoPort {
    void saveVideo(Video video);
    void incrementViewCount(String videoId);
    void syncViewCount(String videoId);
}
