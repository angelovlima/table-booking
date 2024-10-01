package com.fiap.api.table_booking.application.service;

import com.fiap.api.table_booking.application.exception.ResourceNotFoundException;
import com.fiap.api.table_booking.infrastructure.RestaurantCommentJpaEntity;
import com.fiap.api.table_booking.infrastructure.repository.IRestaurantCommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantCommentServiceTest {

    @Mock
    private IRestaurantCommentRepository commentRepository;

    @InjectMocks
    private RestaurantCommentService commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class CreateComment {

        @Test
        void testCreateCommentSuccess() {
            var commentEntity = new RestaurantCommentJpaEntity(null, "Ótimo restaurante", null, null);

            when(commentRepository.save(any(RestaurantCommentJpaEntity.class))).thenReturn(commentEntity);

            RestaurantCommentJpaEntity savedComment = commentService.createComment(commentEntity);

            assertNotNull(savedComment);
            assertEquals("Ótimo restaurante", savedComment.getComment());
            verify(commentRepository, times(1)).save(commentEntity);
        }
    }

    @Nested
    class UpdateComment {

        @Test
        void testUpdateCommentSuccess() {
            var commentEntity = new RestaurantCommentJpaEntity(1L, "Comentário atualizado", null, null);

            when(commentRepository.findById(1L)).thenReturn(Optional.of(commentEntity));
            when(commentRepository.save(any(RestaurantCommentJpaEntity.class))).thenReturn(commentEntity);

            RestaurantCommentJpaEntity updatedComment = commentService.updateComment(commentEntity);

            assertNotNull(updatedComment);
            assertEquals("Comentário atualizado", updatedComment.getComment());
            verify(commentRepository, times(1)).findById(1L);
            verify(commentRepository, times(1)).save(commentEntity);
        }

        @Test
        void testUpdateCommentNotFound() {
            var commentEntity = new RestaurantCommentJpaEntity(999L, "Comentário inexistente", null, null);

            when(commentRepository.findById(999L)).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> commentService.updateComment(commentEntity));
            verify(commentRepository, times(1)).findById(999L);
            verify(commentRepository, never()).save(any(RestaurantCommentJpaEntity.class));
        }
    }

    @Nested
    class GetAllComments {

        @Test
        void testGetAllCommentsSuccess() {
            var commentEntity = new RestaurantCommentJpaEntity(1L, "Ótimo restaurante", null, null);

            when(commentRepository.findAll()).thenReturn(Collections.singletonList(commentEntity));

            List<RestaurantCommentJpaEntity> comments = commentService.getAllComments();

            assertFalse(comments.isEmpty());
            assertEquals(1, comments.size());
            assertEquals("Ótimo restaurante", comments.get(0).getComment());
            verify(commentRepository, times(1)).findAll();
        }
    }

    @Nested
    class DeleteComment {

        @Test
        void testDeleteCommentSuccess() {
            var commentEntity = new RestaurantCommentJpaEntity(1L, "Comentário para deletar", null, null);

            when(commentRepository.findById(1L)).thenReturn(Optional.of(commentEntity));
            doNothing().when(commentRepository).deleteById(1L);

            commentService.deleteComment(1L);

            verify(commentRepository, times(1)).findById(1L);
            verify(commentRepository, times(1)).deleteById(1L);
        }

    }
}
