package com.fiap.api.table_booking.application.service;

import com.fiap.api.table_booking.application.Customer;

import java.util.List;

public interface ICustomerService {
    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    List<Customer> getAllCustomers();

    void deleteCustomer(Long id);
}
