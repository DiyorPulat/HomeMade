package org.example.homemade2.dto;

import lombok.*;
import org.springframework.stereotype.Repository;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseHomeDetailsDTO {
    private Boolean isSuccess;
    private String message;
}
