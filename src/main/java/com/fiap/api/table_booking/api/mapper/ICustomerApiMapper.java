package com.fiap.api.table_booking.api.mapper;

import com.fiap.api.table_booking.api.model.CreateCustomerApiRequest;
import com.fiap.api.table_booking.api.model.CustomerApiResponse;
import com.fiap.api.table_booking.api.model.UpdateCustomerApiRequest;
import com.fiap.api.table_booking.application.Customer;

public interface ICustomerApiMapper {

    Customer toDomainEntity(CreateCustomerApiRequest createCustomerApiRequest);
    Customer toDomainEntity(UpdateCustomerApiRequest updateCustomerApiRequest);
    CustomerApiResponse toCustomerApiResponse(Customer customer);
}
