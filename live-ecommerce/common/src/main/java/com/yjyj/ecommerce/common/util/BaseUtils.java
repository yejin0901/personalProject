package com.yjyj.ecommerce.common.util;

import com.google.gson.Gson;

public interface BaseUtils {
    default String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this, this.getClass());
    }
}