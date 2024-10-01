package com.fiap.api.table_booking.infrastructure.repository;

import com.fiap.api.table_booking.infrastructure.CustomerJpaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICustomerRepository {
    CustomerJpaEntity save(CustomerJpaEntity customerJpaEntity);
    List<CustomerJpaEntity> findAll();
    void deleteById(Long id);
    Optional<CustomerJpaEntity> findById(Long id);
}
