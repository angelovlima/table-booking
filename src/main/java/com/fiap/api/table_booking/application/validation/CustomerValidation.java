package com.fiap.api.table_booking.application.validation;

import com.fiap.api.table_booking.application.Customer;
import com.fiap.api.table_booking.application.exception.InvalidCustomerDataException;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidation {
    public void validateCustomerDataCreate(Customer customer) {
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new InvalidCustomerDataException("O nome do cliente é obrigatório.");
        }
        if (customer.getContact() == null || customer.getContact().trim().isEmpty()) {
            throw new InvalidCustomerDataException("O contato do cliente é obrigatório.");
        }
    }

    public void validateCustomerDataUpdate(Customer customer) {
        if(customer.getId() == null) {
            throw new InvalidCustomerDataException("O id do cliente é obrigatório.");
        }
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new InvalidCustomerDataException("O nome do cliente é obrigatório.");
        }
        if (customer.getContact() == null || customer.getContact().trim().isEmpty()) {
            throw new InvalidCustomerDataException("O contato do cliente é obrigatório.");
        }
    }
}
