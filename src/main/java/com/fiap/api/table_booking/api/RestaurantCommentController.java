package com.fiap.api.table_booking.api;

import com.fiap.api.table_booking.api.mapper.IRestaurantCommentApiMapper;
import com.fiap.api.table_booking.api.model.CreateRestaurantCommentApiRequest;
import com.fiap.api.table_booking.api.model.RestaurantCommentApiResponse;
import com.fiap.api.table_booking.api.model.UpdateRestaurantCommentApiRequest;
import com.fiap.api.table_booking.application.service.IRestaurantCommentService;
import com.fiap.api.table_booking.infrastructure.RestaurantCommentJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantCommentController implements RestaurantCommentApi {

    private final IRestaurantCommentService commentService;
    private final IRestaurantCommentApiMapper commentApiMapper;

    @Override
    public RestaurantCommentApiResponse create(CreateRestaurantCommentApiRequest request) {
        RestaurantCommentJpaEntity createdComment = commentService.createComment(commentApiMapper.toDomainEntity(request));
        return commentApiMapper.toApiResponse(createdComment);
    }

    @Override
    public RestaurantCommentApiResponse update(UpdateRestaurantCommentApiRequest request) {
        RestaurantCommentJpaEntity updatedComment = commentService.updateComment(commentApiMapper.toDomainEntity(request));
        return commentApiMapper.toApiResponse(updatedComment);
    }

    @Override
    public List<RestaurantCommentApiResponse> findAllComments() {
        List<RestaurantCommentJpaEntity> comments = commentService.getAllComments();
        return comments.stream()
                .map(commentApiMapper::toApiResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        commentService.deleteComment(id);
    }
}
