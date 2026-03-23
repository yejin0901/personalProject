package com.yjyj.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(scanBasePackages = {
//    "com.yjyj.ecommerce.config",
//    "com.yjyj.ecommerce.notification",
//    "com.yjyj.ecommerce.payment",
//    "com.yjyj.ecommerce.common",
//    "com.yjyj.ecommerce.live",
//})
@SpringBootApplication(scanBasePackages = "com.yjyj.ecommerce")
@EnableJpaRepositories(basePackages = {
    "com.yjyj.ecommerce.payment", // payment repo들
    "com.yjyj.ecommerce.live.infrastructure.adapter.out.jpa" // live repo들 (channel 포함)
})
@EntityScan(basePackages = {
    "com.yjyj.ecommerce.payment", // payment 엔티티가 있는 실제 패키지 상위(안전하게 크게)
    "com.yjyj.ecommerce.live.infrastructure.adapter.out.jpa" // live 엔티티들
})

public class EcommerceApplication {

  public static void main(String[] args) {
    SpringApplication.run(EcommerceApplication.class, args);
  }

}
