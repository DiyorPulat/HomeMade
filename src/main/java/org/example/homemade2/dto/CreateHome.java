package org.example.homemade2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateHome(
        @Size(min=1, max=100)
        @NotBlank
        @JsonProperty("home_main")
        String name,
        @NotBlank
        @JsonProperty("homeImg")
        String homeImg,
        @NotBlank
        @JsonProperty("client_id")
        Long client_id
        ) {
}
