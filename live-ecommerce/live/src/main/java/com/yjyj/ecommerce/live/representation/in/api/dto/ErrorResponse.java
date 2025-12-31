package com.yjyj.ecommerce.live.representation.in.api.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {
    private String type;
    private String detail;
    private LocalDateTime timestamp;
}
