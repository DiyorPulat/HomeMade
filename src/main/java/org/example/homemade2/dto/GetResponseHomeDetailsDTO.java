package org.example.homemade2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.example.homemade2.entity.Home;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetResponseHomeDetailsDTO {
    private Home home;
    private String homeImg;
    private String type;
    private String code;
    private Boolean isSuccess;
    private String message;
}
