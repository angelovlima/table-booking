package com.fiap.api.table_booking.application.mapper;

import com.fiap.api.table_booking.application.Customer;
import com.fiap.api.table_booking.infrastructure.CustomerJpaEntity;

public interface ICustomerJpaMapper {

    CustomerJpaEntity toJpaEntity(Customer customer);

    Customer toDomainEntity(CustomerJpaEntity customerJpaEntity);
}
