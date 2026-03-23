package com.yjyj.ecommerce.report.representation.product;

import com.yjyj.ecommerce.report.application.service.product.ProductStockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/products")
@RestController
@RequiredArgsConstructor
@Tag(name = "제품 API")
public class ProductController {

  private final ProductStockService productStockService;

  @GetMapping("/{productId}")
  @Operation(summary = "제품 정보 조회")
  public ProductResponse getProduct(@PathVariable String productId) {
    return ProductResponse.from(productStockService.findProduct(productId));
  }

  @GetMapping("")
  @Operation(summary = "복수 제품 정보 조회")
  public Page<ProductResponse> getProducts(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "productId,asc") String[] sort) {
    Sort.Direction direction =
        sort[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
    Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
    return productStockService.getAllProducts(pageable).map(ProductResponse::from);
  }

}
