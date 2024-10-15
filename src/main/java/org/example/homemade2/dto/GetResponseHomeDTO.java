package org.example.homemade2.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetResponseHomeDTO {
    private String name;
    private String homeImg;
    private Long client_id;
    private Boolean isSuccess;
    private String message;
}
