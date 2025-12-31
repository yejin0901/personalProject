package com.yjyj.ecommerce.live.representation.in.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VideoRequest {
    private String title;
    private String description;
    private String thumbnailUrl;
    private String channelId;
}
