package com.fiap.api.table_booking.api;

import com.fiap.api.table_booking.api.mapper.IRestaurantReservationApiMapper;
import com.fiap.api.table_booking.api.model.CreateRestaurantReservationApiRequest;
import com.fiap.api.table_booking.api.model.RestaurantReservationApiResponse;
import com.fiap.api.table_booking.api.model.UpdateRestaurantReservationApiRequest;
import com.fiap.api.table_booking.application.service.IRestaurantReservationService;
import com.fiap.api.table_booking.infrastructure.RestaurantReservationJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantReservationController implements RestaurantReservationApi {

    private final IRestaurantReservationService reservationService;
    private final IRestaurantReservationApiMapper reservationApiMapper;

    @Override
    public RestaurantReservationApiResponse create(CreateRestaurantReservationApiRequest request) {
        RestaurantReservationJpaEntity createdReservation = reservationService.createReservation(reservationApiMapper.toDomainEntity(request));
        return reservationApiMapper.toApiResponse(createdReservation);
    }

    @Override
    public RestaurantReservationApiResponse update(UpdateRestaurantReservationApiRequest request) {
        RestaurantReservationJpaEntity updatedReservation = reservationService.updateReservation(reservationApiMapper.toDomainEntity(request));
        return reservationApiMapper.toApiResponse(updatedReservation);
    }

    @Override
    public List<RestaurantReservationApiResponse> findAllReservations() {
        List<RestaurantReservationJpaEntity> reservations = reservationService.getAllReservations();
        return reservations.stream()
                .map(reservationApiMapper::toApiResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        reservationService.deleteReservation(id);
    }
}
