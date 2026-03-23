package com.yjyj.batch.domain.product;

import com.yjyj.batch.batch.domain.product.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

  @Query("select p.productId from Product p")
  List<String> findAllProjectIds();
}
