package org.example.homemade2.mapper;

import lombok.RequiredArgsConstructor;
import org.example.homemade2.dto.CreateHomeDetails;
import org.example.homemade2.entity.HomeDetails;
import org.example.homemade2.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



public class HomeDetailsMapper {
    public static HomeDetails toEntity(CreateHomeDetails createHomeDetails) {
        HomeDetails entity = new HomeDetails();
        entity.setHomeImg(createHomeDetails.homeImg());
        entity.setCode(createHomeDetails.code());
        entity.setType(createHomeDetails.type());
        return entity;
    }
}
