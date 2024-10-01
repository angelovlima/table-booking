package com.fiap.api.table_booking.api;

import com.fiap.api.table_booking.api.mapper.ICustomerApiMapper;
import com.fiap.api.table_booking.api.model.CreateCustomerApiRequest;
import com.fiap.api.table_booking.api.model.CustomerApiResponse;
import com.fiap.api.table_booking.api.model.UpdateCustomerApiRequest;
import com.fiap.api.table_booking.application.Customer;
import com.fiap.api.table_booking.application.exception.ControllerExceptionHandler;
import com.fiap.api.table_booking.application.exception.ResourceNotFoundException;
import com.fiap.api.table_booking.application.service.ICustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CustomerControllerTest {

    @Mock
    private ICustomerService customerService;

    @Mock
    private ICustomerApiMapper customerApiMapper;

    @InjectMocks
    private CustomerController customerController;

    AutoCloseable mock;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void testCreateCustomer() throws Exception {
        Customer customer = new Customer();
        CustomerApiResponse response = new CustomerApiResponse(1L, "Ângelo Lima", "(12) 98765-4321");

        when(customerApiMapper.toDomainEntity(any(CreateCustomerApiRequest.class))).thenReturn(customer);
        when(customerService.createCustomer(customer)).thenReturn(customer);
        when(customerApiMapper.toCustomerApiResponse(customer)).thenReturn(response);

        mockMvc.perform(post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Ângelo Lima\", \"contact\": \"(12) 98765-4321\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Ângelo Lima"))
                .andExpect(jsonPath("$.contact").value("(12) 98765-4321"));

        verify(customerService, times(1)).createCustomer(customer);
    }

    @Test
    void testUpdateCustomer() throws Exception {
        Customer customer = new Customer();
        CustomerApiResponse response = new CustomerApiResponse(1L, "Ângelo Lima", "(12) 98765-4321");

        when(customerApiMapper.toDomainEntity(any(UpdateCustomerApiRequest.class))).thenReturn(customer);
        when(customerService.updateCustomer(customer)).thenReturn(customer);
        when(customerApiMapper.toCustomerApiResponse(customer)).thenReturn(response);

        mockMvc.perform(put("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"name\": \"Ângelo Lima\", \"contact\": \"(12) 98765-4321\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Ângelo Lima"))
                .andExpect(jsonPath("$.contact").value("(12) 98765-4321"));

        verify(customerService, times(1)).updateCustomer(customer);
    }

    @Test
    void testUpdateCustomerNotFound() throws Exception {
        UpdateCustomerApiRequest updateRequest = new UpdateCustomerApiRequest(999L, "Ângelo Lima", "(12) 98765-4321");

        Customer customer = new Customer();
        when(customerApiMapper.toDomainEntity(updateRequest)).thenReturn(customer);
        doThrow(new ResourceNotFoundException("Cliente não encontrado")).when(customerService).updateCustomer(any(Customer.class));

        mockMvc.perform(put("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 999, \"name\": \"Ângelo Lima\", \"contact\": \"(12) 98765-4321\" }"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Cliente não encontrado"))
                .andExpect(jsonPath("$.details[0]").exists())
                .andExpect(jsonPath("$.timestamp").exists());

        verify(customerService, times(1)).updateCustomer(any(Customer.class));
    }

    @Test
    void testFindAllCustomers() throws Exception {
        Customer customer = new Customer();
        CustomerApiResponse response = new CustomerApiResponse(1L, "Ângelo Lima", "(12) 98765-4321");

        when(customerService.getAllCustomers()).thenReturn(Collections.singletonList(customer));
        when(customerApiMapper.toCustomerApiResponse(customer)).thenReturn(response);

        mockMvc.perform(get("/customer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Ângelo Lima"))
                .andExpect(jsonPath("$[0].contact").value("(12) 98765-4321"));

        verify(customerService, times(1)).getAllCustomers();
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(customerService).deleteCustomer(anyLong());

        mockMvc.perform(delete("/customer/1"))
                .andExpect(status().isNoContent());

        verify(customerService, times(1)).deleteCustomer(anyLong());
    }
}
