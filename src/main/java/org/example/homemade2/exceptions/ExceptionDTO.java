package org.example.homemade2.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public record ExceptionDTO(
        @JsonProperty("code")
        int code,
        @JsonProperty("timestamp")
        Date date,
        @JsonProperty("error_message")
        String message
) implements Serializable {
}
