package com.fiap.api.table_booking.api;

import com.fiap.api.table_booking.api.mapper.ICustomerApiMapper;
import com.fiap.api.table_booking.api.model.CreateCustomerApiRequest;
import com.fiap.api.table_booking.api.model.CustomerApiResponse;
import com.fiap.api.table_booking.api.model.UpdateCustomerApiRequest;
import com.fiap.api.table_booking.application.Customer;
import com.fiap.api.table_booking.application.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController implements CustomerApi {

    private final ICustomerService customerService;
    private final ICustomerApiMapper customerApiMapper;

    @Override
    public CustomerApiResponse create(CreateCustomerApiRequest request) {
        Customer createdCustomer = customerService.createCustomer(customerApiMapper.toDomainEntity(request));
        return customerApiMapper.toCustomerApiResponse(createdCustomer);
    }

    @Override
    public CustomerApiResponse update(UpdateCustomerApiRequest request) {
        Customer updatedCustomer = customerService.updateCustomer(customerApiMapper.toDomainEntity(request));
        return customerApiMapper.toCustomerApiResponse(updatedCustomer);
    }

    @Override
    public List<CustomerApiResponse> findAllCustomers() {
        List<Customer> allCustomers = customerService.getAllCustomers();
        return allCustomers.stream()
                .map(customerApiMapper::toCustomerApiResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        customerService.deleteCustomer(id);
    }
}
