package com.fiap.api.table_booking.application.service;

import com.fiap.api.table_booking.application.exception.InvalidReservationDataException;
import com.fiap.api.table_booking.application.exception.ResourceNotFoundException;
import com.fiap.api.table_booking.infrastructure.RestaurantReservationJpaEntity;
import com.fiap.api.table_booking.infrastructure.repository.IRestaurantReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantReservationService implements IRestaurantReservationService {

    @Qualifier("jpaRestaurantReservation")
    private final IRestaurantReservationRepository reservationRepository;
    private final IRestaurantService restaurantService;

    @Override
    public RestaurantReservationJpaEntity createReservation(RestaurantReservationJpaEntity reservation) {
        validateRestaurantPeriod(reservation.getRestaurant().getId(), reservation.getPeriod());

        validateRestaurantCapacity(reservation.getRestaurant().getId(), reservation.getReservationDate());

        return reservationRepository.save(reservation);
    }

    @Override
    public RestaurantReservationJpaEntity updateReservation(RestaurantReservationJpaEntity reservation) {
        RestaurantReservationJpaEntity existingReservation = reservationRepository.findById(reservation.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Reserva não encontrada"));

        validateRestaurantPeriod(reservation.getRestaurant().getId(), reservation.getPeriod());

        validateRestaurantCapacity(reservation.getRestaurant().getId(), reservation.getReservationDate());

        existingReservation.setPeriod(reservation.getPeriod());
        existingReservation.setReservationDate(reservation.getReservationDate());
        existingReservation.setCustomer(reservation.getCustomer());
        existingReservation.setRestaurant(reservation.getRestaurant());

        return reservationRepository.save(existingReservation);
    }

    @Override
    public List<RestaurantReservationJpaEntity> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    private void validateRestaurantPeriod(Long restaurantId, String period) {
        var restaurant = restaurantService.getRestaurantById(restaurantId);

        if (!restaurant.getPeriod().equalsIgnoreCase(period)) {
            throw new InvalidReservationDataException("O restaurante não funciona no período desejado.");
        }
    }

    private void validateRestaurantCapacity(Long restaurantId, LocalDate reservationDate) {
        var restaurant = restaurantService.getRestaurantById(restaurantId);
        var reservationsForDay = reservationRepository.findAllByRestaurantIdAndReservationDate(restaurantId, reservationDate);

        if (reservationsForDay.size() >= restaurant.getCapacity()) {
            throw new InvalidReservationDataException("O restaurante não tem capacidade disponível para a data selecionada.");
        }
    }

    public RestaurantReservationJpaEntity findReservationById(Long id) {
        Optional<RestaurantReservationJpaEntity> reservation = reservationRepository.findById(id);
        if (reservation.isEmpty()) {
            throw new RuntimeException("Reserva não encontrada");
        }
        return reservation.get();
    }
}
