package com.yjyj.ecommerce.live.representation.in.api;


import com.yjyj.ecommerce.live.application.port.in.ChannelUseCase;
import com.yjyj.ecommerce.live.domain.channel.Channel;
import com.yjyj.ecommerce.live.representation.in.api.dto.ChannelRequest;
import com.yjyj.ecommerce.live.representation.in.api.dto.CommandResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/channels")
@Tag(name = "라이브 채널 상태 API")
public class ChannelApiController {
    private final ChannelUseCase channelUseCase;

    public ChannelApiController(ChannelUseCase channelUseCase) {
        this.channelUseCase = channelUseCase;
    }

    @PostMapping
    @Operation(summary = "라이브 채널 생성")
    public CommandResponse createChannel(@RequestBody ChannelRequest channelRequest) {
        var channel = channelUseCase.createChannel(channelRequest);

        return new CommandResponse(channel.getId());
    }

    @PutMapping("{channelId}")
    @Operation(summary = "라이브 채널 업데이트")
    public void updateChannel(
        @PathVariable String channelId,
        @RequestBody ChannelRequest channelRequest
    ) {
        channelUseCase.updateChannel(channelId, channelRequest);
    }

    @GetMapping("{channelId}")
    @Operation(summary = "라이브 채널 조회")
    public Channel getChannel(@PathVariable String channelId) {
        return channelUseCase.getChannel(channelId);
    }
}
