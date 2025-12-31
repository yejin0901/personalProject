package com.yjyj.ecommerce.live.representation.in.api;

import com.yjyj.ecommerce.live.application.port.in.CacheManageUseCase;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/caches")
public class CacheController {
    private final CacheManageUseCase cacheUseCase;

    public CacheController(CacheManageUseCase cacheUseCase) {
        this.cacheUseCase = cacheUseCase;
    }

    @GetMapping("cachenames")
    List<String> cacheNames() {
        return cacheUseCase.getAllCacheNames();
    }

    @DeleteMapping(params = "cacheKey")
    void deleteCache(@RequestParam String cacheKey) {
        cacheUseCase.deleteCache(cacheKey);
    }
}
