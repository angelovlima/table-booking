package com.fiap.api.table_booking.application.service;

import com.fiap.api.table_booking.infrastructure.RestaurantCommentJpaEntity;

import java.util.List;

public interface IRestaurantCommentService {
    RestaurantCommentJpaEntity createComment(RestaurantCommentJpaEntity comment);
    RestaurantCommentJpaEntity updateComment(RestaurantCommentJpaEntity comment);
    List<RestaurantCommentJpaEntity> getAllComments();
    void deleteComment(Long id);
}
