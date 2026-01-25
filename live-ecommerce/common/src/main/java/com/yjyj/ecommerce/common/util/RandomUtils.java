package com.yjyj.ecommerce.common.util;

import java.time.Instant;
import java.util.UUID;

public class RandomUtils {

  public static String generateRandomId() {
    return Instant.now().toEpochMilli() + "_" + UUID.randomUUID();
  }
  
}
