package com.yjyj.ecommerce.live.application.port.in;

import java.util.List;

public interface CacheManageUseCase {
    List<String> getAllCacheNames();

    Boolean deleteCache(String cacheKey);
}
