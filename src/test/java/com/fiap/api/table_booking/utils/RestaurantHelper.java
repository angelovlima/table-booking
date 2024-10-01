package com.fiap.api.table_booking.utils;

import com.fiap.api.table_booking.api.model.CreateRestaurantApiRequest;
import com.fiap.api.table_booking.api.model.UpdateRestaurantApiRequest;
import com.fiap.api.table_booking.infrastructure.RestaurantJpaEntity;

public abstract class RestaurantHelper {

    public static RestaurantJpaEntity generateRestaurantJpaEntity() {
        return new RestaurantJpaEntity(
                1L,
                "Almoço",
                "Restaurante X",
                "Rua Y",
                "Italiana",
                50
        );
    }

    public static CreateRestaurantApiRequest generateCreateRestaurantApiRequest() {
        return new CreateRestaurantApiRequest(
                "Restaurante X",
                "Rua Y",
                "Italiana",
                50,
                "Almoço"
        );
    }

    public static UpdateRestaurantApiRequest generateUpdateRestaurantApiRequest() {
        return new UpdateRestaurantApiRequest(
                1L,
                "Restaurante Atualizado",
                "Rua Z",
                "Francesa",
                60,
                "Jantar"
        );
    }
}
