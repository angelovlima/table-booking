package com.fiap.api.table_booking.application.mapper;

import com.fiap.api.table_booking.application.Customer;
import com.fiap.api.table_booking.infrastructure.CustomerJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerJpaMapper implements ICustomerJpaMapper {

    @Override
    public CustomerJpaEntity toJpaEntity(Customer customer) {
        return new CustomerJpaEntity(customer.getId(), customer.getName(), customer.getContact());
    }

    @Override
    public Customer toDomainEntity(CustomerJpaEntity customerJpaEntity) {
        return new Customer(customerJpaEntity.getId(), customerJpaEntity.getName(), customerJpaEntity.getContact());
    }
}
