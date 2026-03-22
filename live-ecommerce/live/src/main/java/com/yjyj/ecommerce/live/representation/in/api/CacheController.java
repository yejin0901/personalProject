package com.yjyj.ecommerce.live.representation.in.api;

import com.yjyj.ecommerce.live.application.port.in.CacheManageUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/caches")
@Tag(name = "캐시 관리 API")
public class CacheController {
    private final CacheManageUseCase cacheUseCase;

    public CacheController(CacheManageUseCase cacheUseCase) {
        this.cacheUseCase = cacheUseCase;
    }

    @GetMapping("cachenames")
    @Operation(summary = "캐시 이름 조회")
    List<String> cacheNames() {
        return cacheUseCase.getAllCacheNames();
    }

    @DeleteMapping(params = "cacheKey")
    @Operation(summary = "캐시 삭제")
    void deleteCache(@RequestParam String cacheKey) {
        cacheUseCase.deleteCache(cacheKey);
    }
}
