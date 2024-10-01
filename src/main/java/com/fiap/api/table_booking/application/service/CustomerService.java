package com.fiap.api.table_booking.application.service;

import com.fiap.api.table_booking.application.Customer;
import com.fiap.api.table_booking.application.exception.DuplicateContactException;
import com.fiap.api.table_booking.application.exception.ResourceNotFoundException;
import com.fiap.api.table_booking.application.mapper.ICustomerJpaMapper;
import com.fiap.api.table_booking.application.validation.CustomerValidation;
import com.fiap.api.table_booking.infrastructure.CustomerJpaEntity;
import com.fiap.api.table_booking.infrastructure.repository.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    @Qualifier("jpaCustomer")
    private final ICustomerRepository customerRepository;

    private final ICustomerJpaMapper customerJpaMapper;

    private final CustomerValidation customerValidation;

    @Override
    public Customer createCustomer(Customer customer) {
        customerValidation.validateCustomerDataCreate(customer);
        try {
            CustomerJpaEntity customerJpaEntity = customerJpaMapper.toJpaEntity(customer);
            CustomerJpaEntity savedCustomer = customerRepository.save(customerJpaEntity);
            return customerJpaMapper.toDomainEntity(savedCustomer);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateContactException("O contato " + customer.getContact() + " já existe.");
        }
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        customerValidation.validateCustomerDataUpdate(customer);

        CustomerJpaEntity savedCustomer = customerRepository.findById(customer.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        savedCustomer.setName(customer.getName());
        savedCustomer.setContact(customer.getContact());

        try {
            CustomerJpaEntity editedCustomer = customerRepository.save(savedCustomer);
            return customerJpaMapper.toDomainEntity(editedCustomer);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateContactException("O contato " + customer.getContact() + " já existe.");
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerJpaMapper::toDomainEntity)
                .toList();
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
