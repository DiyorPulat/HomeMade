package org.example.homemade2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateFavouritePayment(
        @JsonProperty("homeDetails_id")
        Long homeDetails_id,
        @JsonProperty("amount")
        Long amount
) {
}
