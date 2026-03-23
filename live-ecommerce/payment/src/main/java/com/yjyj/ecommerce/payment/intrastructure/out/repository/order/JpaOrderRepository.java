package com.yjyj.ecommerce.payment.intrastructure.out.repository.order;


import com.yjyj.ecommerce.payment.domain.order.Order;
import com.yjyj.ecommerce.payment.intrastructure.out.repository.JpaBaseRepository;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaOrderRepository extends JpaBaseRepository<Order, UUID> {
}
