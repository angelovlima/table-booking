package com.fiap.api.table_booking.infrastructure.repository;

import com.fiap.api.table_booking.infrastructure.CustomerJpaEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class JpaCustomerRepositoryTest {

    @Mock
    private ICustomerRepository customerRepository;
    AutoCloseable openMock;

    @BeforeEach
    void setup() {
        openMock = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMock.close();
    }

    @Test
    void shouldAllowRegisterCustomer() {
        //Arrange
        CustomerJpaEntity customer = new CustomerJpaEntity(1L, "User", "(00) 00000-0000");
        when(customerRepository.save(any(CustomerJpaEntity.class))).thenReturn(customer);
        // Act
        CustomerJpaEntity customerSaved = customerRepository.save(customer);
        //Assert
        assertThat(customerSaved).isNotNull().isEqualTo(customer);
        verify(customerRepository, times(1)).save(any(CustomerJpaEntity.class));
    }
}
