package com.yjyj.ecommerce.live.representation.in.api.dto;

import com.yjyj.ecommerce.live.domain.video.VideoRate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VideoRateResponse {
    private String videoId;
    private VideoRate rate;
}
