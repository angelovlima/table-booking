package com.fiap.api.table_booking.api;

import com.fiap.api.table_booking.api.model.CreateRestaurantCommentApiRequest;
import com.fiap.api.table_booking.api.model.RestaurantCommentApiResponse;
import com.fiap.api.table_booking.api.model.UpdateRestaurantCommentApiRequest;
import com.fiap.api.table_booking.application.exception.ControllerExceptionHandler;
import com.fiap.api.table_booking.application.exception.ResourceNotFoundException;
import com.fiap.api.table_booking.application.service.IRestaurantCommentService;
import com.fiap.api.table_booking.infrastructure.RestaurantCommentJpaEntity;
import com.fiap.api.table_booking.api.mapper.IRestaurantCommentApiMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RestaurantCommentControllerTest {

    @Mock
    private IRestaurantCommentService commentService;

    @Mock
    private IRestaurantCommentApiMapper commentApiMapper;

    @InjectMocks
    private RestaurantCommentController commentController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(commentController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @Nested
    class CreateComment {

        @Test
        void testCreateCommentSuccess() throws Exception {
            var commentEntity = new RestaurantCommentJpaEntity(null, "Ótimo restaurante", null, null);
            var commentResponse = new RestaurantCommentApiResponse(1L, "Restaurante Italiano", "Ângelo Lima", "Ótimo restaurante");

            when(commentApiMapper.toDomainEntity(any(CreateRestaurantCommentApiRequest.class))).thenReturn(commentEntity);
            when(commentService.createComment(commentEntity)).thenReturn(commentEntity);
            when(commentApiMapper.toApiResponse(commentEntity)).thenReturn(commentResponse);

            mockMvc.perform(post("/restaurant-comment")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{ \"restaurantId\": 1, \"customerId\": 1, \"comment\": \"Ótimo restaurante\" }"))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.comment").value("Ótimo restaurante"))
                    .andExpect(jsonPath("$.restaurantName").value("Restaurante Italiano"))
                    .andExpect(jsonPath("$.customerName").value("Ângelo Lima"));

            verify(commentService, times(1)).createComment(any(RestaurantCommentJpaEntity.class));
        }
    }

    @Nested
    class UpdateComment {

        @Test
        void testUpdateCommentSuccess() throws Exception {
            var commentEntity = new RestaurantCommentJpaEntity(1L, "Serviço excelente", null, null);
            var commentResponse = new RestaurantCommentApiResponse(1L, "Restaurante Italiano", "Ângelo Lima", "Serviço excelente");

            when(commentApiMapper.toDomainEntity(any(UpdateRestaurantCommentApiRequest.class))).thenReturn(commentEntity);
            when(commentService.updateComment(commentEntity)).thenReturn(commentEntity);
            when(commentApiMapper.toApiResponse(commentEntity)).thenReturn(commentResponse);

            mockMvc.perform(put("/restaurant-comment")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{ \"id\": 1, \"restaurantId\": 1, \"customerId\": 1, \"comment\": \"Serviço excelente\" }"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.comment").value("Serviço excelente"))
                    .andExpect(jsonPath("$.restaurantName").value("Restaurante Italiano"))
                    .andExpect(jsonPath("$.customerName").value("Ângelo Lima"));

            verify(commentService, times(1)).updateComment(any(RestaurantCommentJpaEntity.class));
        }

        @Test
        void testUpdateCommentNotFound() throws Exception {
            var commentEntity = new RestaurantCommentJpaEntity(999L, "Comentário não encontrado", null, null);

            when(commentApiMapper.toDomainEntity(any(UpdateRestaurantCommentApiRequest.class))).thenReturn(commentEntity);
            doThrow(new ResourceNotFoundException("Comentário não encontrado")).when(commentService).updateComment(any(RestaurantCommentJpaEntity.class));

            mockMvc.perform(put("/restaurant-comment")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{ \"id\": 999, \"restaurantId\": 1, \"customerId\": 1, \"comment\": \"Comentário não encontrado\" }"))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.message").value("Comentário não encontrado"));

            verify(commentService, times(1)).updateComment(any(RestaurantCommentJpaEntity.class));
        }
    }

    @Nested
    class FindAllComments {

        @Test
        void testFindAllCommentsSuccess() throws Exception {
            var commentEntity = new RestaurantCommentJpaEntity(1L, "Ótimo restaurante", null, null);
            var commentResponse = new RestaurantCommentApiResponse(1L, "Restaurante Italiano", "Ângelo Lima", "Ótimo restaurante");

            when(commentService.getAllComments()).thenReturn(Collections.singletonList(commentEntity));
            when(commentApiMapper.toApiResponse(commentEntity)).thenReturn(commentResponse);

            mockMvc.perform(get("/restaurant-comment")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].comment").value("Ótimo restaurante"))
                    .andExpect(jsonPath("$[0].restaurantName").value("Restaurante Italiano"))
                    .andExpect(jsonPath("$[0].customerName").value("Ângelo Lima"));

            verify(commentService, times(1)).getAllComments();
        }
    }

    @Nested
    class DeleteComment {

        @Test
        void testDeleteCommentSuccess() throws Exception {
            doNothing().when(commentService).deleteComment(1L);

            mockMvc.perform(delete("/restaurant-comment/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());

            verify(commentService, times(1)).deleteComment(1L);
        }
    }
}
