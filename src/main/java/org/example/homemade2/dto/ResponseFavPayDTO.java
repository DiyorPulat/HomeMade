package org.example.homemade2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseFavPayDTO {
    private Boolean isSuccess;
    private String message;
}
