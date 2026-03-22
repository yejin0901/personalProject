package com.yjyj.ecommerce.live.representation.in.api;


import com.yjyj.ecommerce.live.application.port.in.SubscribeUseCase;
import com.yjyj.ecommerce.live.application.port.in.UserUserCase;
import com.yjyj.ecommerce.live.domain.channel.Channel;
import com.yjyj.ecommerce.live.domain.user.User;
import com.yjyj.ecommerce.live.representation.in.api.dto.CommandResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subscribe")
@Tag(name = "라이브 채널 구독 API")
public class ChannelSubscribeApiController {
    private final SubscribeUseCase subscribeUseCase;
    private final UserUserCase userUserCase;

    public ChannelSubscribeApiController(SubscribeUseCase subscribeUseCase, UserUserCase userUserCase) {
        this.subscribeUseCase = subscribeUseCase;
        this.userUserCase = userUserCase;
    }

    @Operation(summary = "라이브 채널 구독")
    @PostMapping
    CommandResponse subscribe(
        @ModelAttribute User user,
        @RequestParam String channelId
    ) {
        var subscribeId = subscribeUseCase.subscribeChannel(channelId, user.getId());
        return new CommandResponse(subscribeId);
    }

    @Operation(summary = "라이브 채널 구독 취소")
    @DeleteMapping
    void unsubscribe(
        @ModelAttribute User user,
        @RequestParam String subscribeId
    ) {
        subscribeUseCase.unsubscribeChannel(subscribeId, user.getId());
    }

    @Operation(summary = "사용자 별 라이브 채널 구독 조회")
    @GetMapping("/mine")
    List<Channel> listSubscribeChannelByUser(@ModelAttribute User user) {
        //var user = userUserCase.getUser(userId);
        return subscribeUseCase.listSubscribeChannel(user.getId());
    }
}


