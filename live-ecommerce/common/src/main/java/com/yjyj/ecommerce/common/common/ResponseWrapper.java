package com.yjyj.ecommerce.common.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
@Slf4j
public class ResponseWrapper implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        log.info("execute AOP - supports");
        log.info("execute AOP - returnType :: {}", returnType);
        log.info("execute AOP - converterType :: {}", converterType);
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        log.info("execute AOP - beforeBodyWrite");

      String path = request.getURI().getPath();

      // ✅ Swagger / OpenAPI / Actuator 등은 래핑 금지
      if (path.startsWith("/v3/api-docs")
          || path.startsWith("/swagger-ui")
          || path.startsWith("/actuator")) {
        return body;
      }

      // ✅ byte[] / 파일 응답도 래핑 금지 (캐스팅 에러 방지)
      if (body instanceof byte[]) {
        return body;
      }

      // 이미 ApiResponse면 중복 래핑 방지
      if (body instanceof ApiResponse) {
        return body;
      }
        return new ApiResponse<>("SUCCESS", body);
    }
}
