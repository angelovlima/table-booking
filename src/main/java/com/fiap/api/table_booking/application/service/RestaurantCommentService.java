package com.fiap.api.table_booking.application.service;

import com.fiap.api.table_booking.application.exception.ResourceNotFoundException;
import com.fiap.api.table_booking.infrastructure.RestaurantCommentJpaEntity;
import com.fiap.api.table_booking.infrastructure.repository.IRestaurantCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantCommentService implements IRestaurantCommentService {

    @Qualifier("jpaRestaurantComment")
    private final IRestaurantCommentRepository commentRepository;

    @Override
    public RestaurantCommentJpaEntity createComment(RestaurantCommentJpaEntity comment) {
        return commentRepository.save(comment);
    }

    @Override
    public RestaurantCommentJpaEntity updateComment(RestaurantCommentJpaEntity comment) {
        RestaurantCommentJpaEntity existingComment = commentRepository.findById(comment.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Comentário não encontrado"));

        existingComment.setComment(comment.getComment());
        existingComment.setRestaurant(comment.getRestaurant());
        existingComment.setCustomer(comment.getCustomer());

        return commentRepository.save(existingComment);
    }

    @Override
    public List<RestaurantCommentJpaEntity> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public void deleteComment(Long id) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comentário não encontrado"));
        commentRepository.deleteById(comment.getId());
    }
}