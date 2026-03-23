package com.yjyj.ecommerce.payment.intrastructure.out.repository;


import java.util.Optional;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface JpaBaseRepository<T, ID> extends Repository<T, ID> {
    Optional<T> findById(ID id);
    <S extends T> T save(T entity);
    boolean deleteById(ID id);
}
