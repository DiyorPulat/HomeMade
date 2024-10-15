package org.example.homemade2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.example.homemade2.entity.HomeDetails;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetResponseFavPayDTO {

    private HomeDetails homeDetails;

    private Long amount;
}
