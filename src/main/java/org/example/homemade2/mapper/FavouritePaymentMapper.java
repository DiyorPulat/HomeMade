package org.example.homemade2.mapper;

import org.example.homemade2.dto.CreateFavouritePayment;
import org.example.homemade2.entity.FavouritePayment;

public class FavouritePaymentMapper {

    public static FavouritePayment toEntity(CreateFavouritePayment createFavouritePayment) {
        FavouritePayment entity = new FavouritePayment();
        entity.setAmount(createFavouritePayment.amount());
        return entity;
    }
}
