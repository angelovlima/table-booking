package com.fiap.api.table_booking.application.service;

import com.fiap.api.table_booking.application.Customer;
import com.fiap.api.table_booking.application.exception.DuplicateContactException;
import com.fiap.api.table_booking.application.exception.ResourceNotFoundException;
import com.fiap.api.table_booking.application.mapper.ICustomerJpaMapper;
import com.fiap.api.table_booking.application.validation.CustomerValidation;
import com.fiap.api.table_booking.infrastructure.CustomerJpaEntity;
import com.fiap.api.table_booking.infrastructure.repository.ICustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private ICustomerRepository customerRepository;

    @Mock
    private ICustomerJpaMapper customerJpaMapper;

    @Mock
    private CustomerValidation customerValidation;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;
    private CustomerJpaEntity customerJpaEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customer = new Customer(1L, "Ângelo Lima", "(12) 98765-4321");
        customerJpaEntity = new CustomerJpaEntity(1L, "(12) 98765-4321", "Ângelo Lima");
    }

    @Test
    void testCreateCustomerSuccess() {
        doNothing().when(customerValidation).validateCustomerDataCreate(customer);

        when(customerJpaMapper.toJpaEntity(any(Customer.class))).thenReturn(customerJpaEntity);
        when(customerRepository.save(any(CustomerJpaEntity.class))).thenReturn(customerJpaEntity);
        when(customerJpaMapper.toDomainEntity(any(CustomerJpaEntity.class))).thenReturn(customer);

        Customer result = customerService.createCustomer(customer);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(customer.getId());
        assertThat(result.getName()).isEqualTo(customer.getName());
        assertThat(result.getContact()).isEqualTo(customer.getContact());

        verify(customerRepository, times(1)).save(any(CustomerJpaEntity.class));
        verify(customerValidation, times(1)).validateCustomerDataCreate(customer);
        verify(customerJpaMapper, times(1)).toDomainEntity(customerJpaEntity);
    }

    @Test
    void testCreateCustomerDuplicateContact() {
        doNothing().when(customerValidation).validateCustomerDataCreate(customer);
        when(customerJpaMapper.toJpaEntity(any(Customer.class))).thenReturn(customerJpaEntity);
        when(customerRepository.save(any(CustomerJpaEntity.class)))
                .thenThrow(new DataIntegrityViolationException("Duplicate contact"));

        assertThrows(DuplicateContactException.class, () -> customerService.createCustomer(customer));

        verify(customerRepository, times(1)).save(any(CustomerJpaEntity.class));
        verify(customerValidation, times(1)).validateCustomerDataCreate(customer);
    }

    @Test
    void testUpdateCustomerNotFound() {
        doNothing().when(customerValidation).validateCustomerDataUpdate(customer);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> customerService.updateCustomer(customer));

        verify(customerRepository, times(1)).findById(customer.getId());
    }

    @Test
    void testUpdateCustomerSuccess() {
        doNothing().when(customerValidation).validateCustomerDataUpdate(customer);

        when(customerJpaMapper.toJpaEntity(any(Customer.class))).thenReturn(customerJpaEntity);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customerJpaEntity));

        when(customerRepository.save(any(CustomerJpaEntity.class))).thenReturn(customerJpaEntity);

        when(customerJpaMapper.toDomainEntity(any(CustomerJpaEntity.class))).thenReturn(customer);

        Customer result = customerService.updateCustomer(customer);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(customer.getId());
        assertThat(result.getName()).isEqualTo(customer.getName());
        assertThat(result.getContact()).isEqualTo(customer.getContact());

        verify(customerRepository, times(1)).findById(customer.getId());
        verify(customerRepository, times(1)).save(any(CustomerJpaEntity.class));
        verify(customerJpaMapper, times(1)).toDomainEntity(customerJpaEntity);
    }

    @Test
    void testGetAllCustomersSuccess() {
        CustomerJpaEntity customerJpaEntity1 = new CustomerJpaEntity(1L, "(12) 98765-4321", "User 1");
        CustomerJpaEntity customerJpaEntity2 = new CustomerJpaEntity(2L, "(13) 98765-1234", "User 2");

        Customer customer1 = new Customer(1L, "User 1", "(12) 98765-4321");
        Customer customer2 = new Customer(2L, "User 2", "(13) 98765-1234");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customerJpaEntity1, customerJpaEntity2));

        when(customerJpaMapper.toDomainEntity(customerJpaEntity1)).thenReturn(customer1);
        when(customerJpaMapper.toDomainEntity(customerJpaEntity2)).thenReturn(customer2);

        List<Customer> result = customerService.getAllCustomers();

        assertThat(result).isNotNull().hasSize(2).containsExactly(customer1, customer2);

        verify(customerRepository, times(1)).findAll();

        verify(customerJpaMapper, times(1)).toDomainEntity(customerJpaEntity1);
        verify(customerJpaMapper, times(1)).toDomainEntity(customerJpaEntity2);
    }

    @Test
    void testDeleteCustomerSuccess() {
        customerService.deleteCustomer(1L);

        verify(customerRepository, times(1)).deleteById(1L);
    }
}
