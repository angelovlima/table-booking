package com.fiap.api.table_booking.infrastructure.repository;

import com.fiap.api.table_booking.infrastructure.CustomerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("jpaCustomer")
public interface JpaCustomerRepository extends ICustomerRepository, JpaRepository<CustomerJpaEntity, Long> {
}
