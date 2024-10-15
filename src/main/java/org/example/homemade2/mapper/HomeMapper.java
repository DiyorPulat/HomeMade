package org.example.homemade2.mapper;

import org.example.homemade2.dto.CreateHome;
import org.example.homemade2.entity.Home;
import org.springframework.stereotype.Component;

@Component
public class HomeMapper {
    public static Home toEntity(CreateHome createHome) {
        Home entity = new Home();
        entity.setName(createHome.name());
        entity.setHomeImg(createHome.homeImg());
        entity.setClient_id(createHome.client_id());
        return entity;
    }



}
