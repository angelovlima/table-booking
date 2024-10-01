package com.fiap.api.table_booking.api.mapper;

import com.fiap.api.table_booking.api.model.CreateCustomerApiRequest;
import com.fiap.api.table_booking.api.model.CustomerApiResponse;
import com.fiap.api.table_booking.api.model.UpdateCustomerApiRequest;
import com.fiap.api.table_booking.application.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerApiMapper implements ICustomerApiMapper {

    @Override
    public Customer toDomainEntity(CreateCustomerApiRequest createCustomerApiRequest) {
        return new Customer(createCustomerApiRequest.name(), createCustomerApiRequest.contact());
    }

    @Override
    public Customer toDomainEntity(UpdateCustomerApiRequest updateCustomerApiRequest) {
        return new Customer(updateCustomerApiRequest.id(), updateCustomerApiRequest.name(), updateCustomerApiRequest.contact());
    }

    @Override
    public CustomerApiResponse toCustomerApiResponse(Customer customer) {
        return new CustomerApiResponse(customer.getId(), customer.getName(), customer.getContact());
    }
}
