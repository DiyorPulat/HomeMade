package org.example.homemade2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateHomeDetails(
        @JsonProperty("home_id")
        Long homeId,
        @JsonProperty("homeImg")
        String homeImg,
        @JsonProperty("type")
        String type,
        @JsonProperty("code")
        String code

) {
}
