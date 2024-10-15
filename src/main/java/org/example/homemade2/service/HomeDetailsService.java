package org.example.homemade2.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.homemade2.dto.CreateHome;
import org.example.homemade2.dto.CreateHomeDetails;
import org.example.homemade2.dto.GetResponseHomeDetailsDTO;
import org.example.homemade2.dto.ResponseHomeDetailsDTO;
import org.example.homemade2.entity.Home;
import org.example.homemade2.entity.HomeDetails;
import org.example.homemade2.mapper.HomeDetailsMapper;
import org.example.homemade2.mapper.HomeMapper;
import org.example.homemade2.repository.HomeDetailsRepository;
import org.example.homemade2.repository.HomeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class HomeDetailsService {
    private final HomeDetailsRepository homeDetailsRepository;
    private final HomeRepository homeRepository;

    @Transactional
    public ResponseHomeDetailsDTO createHomeDetails(CreateHomeDetails homeDetails) {

        Optional<Home> homeCheck = homeRepository.findById(homeDetails.homeId());
        ResponseHomeDetailsDTO response = new ResponseHomeDetailsDTO();
        HomeDetails homeDetails1 = HomeDetailsMapper.toEntity(homeDetails);

        if (homeCheck.isPresent()) {
            try {
                homeDetails1.setHome(homeCheck.get());
                homeDetailsRepository.save(homeDetails1);
                log.info("Saved homeDetails successfully: {}", homeDetails.homeId());
                response.setIsSuccess(true);
                response.setMessage("Saved homeDetails successfully");
            }catch (Exception e) {
                response.setIsSuccess(false);
                response.setMessage(e.getMessage());
                log.error("Failed  saving a new home details : {}",e.getMessage());
            }
        }else {
            response.setIsSuccess(false);
            response.setMessage("Home not found");
            log.error("Home  not found");
        }
        return  response;

    }

    @Transactional
    public ResponseHomeDetailsDTO updateHomeDetails(CreateHomeDetails createHomeDetails) {
        Optional<Home> homeCheck = homeRepository.findById(createHomeDetails.homeId());
        ResponseHomeDetailsDTO response = new ResponseHomeDetailsDTO();
        HomeDetails homeDetails1 = HomeDetailsMapper.toEntity(createHomeDetails);
        if (homeCheck.isPresent()) {
            try {
                homeDetails1.setHome(homeCheck.get());
                homeDetailsRepository.save(homeDetails1);
                response.setIsSuccess(true);
                response.setMessage("Updated homeDetails successfully");
                log.info("Updated homeDetails successfully");
            }catch (Exception e) {
                response.setIsSuccess(false);
                response.setMessage(e.getMessage());
                log.error("Failed  updating a  home details : {}",e.getMessage());
            }
        }else {
            response.setIsSuccess(false);
            response.setMessage("Home not found");
            log.error("Home  not found when updating a home details : {}",createHomeDetails.homeId());
        }
        return  response;
    }



    @Transactional
    public ResponseHomeDetailsDTO deleteHomeDetails(Long id) {
        ResponseHomeDetailsDTO response = new ResponseHomeDetailsDTO();
        try {
            homeDetailsRepository.deleteById(id);
            log.info("Deleted homeDetails successfully");
            response.setIsSuccess(true);
            response.setMessage("Deleted homeDetails successfully");
        }catch (Exception e) {
            log.error("Failed  deleting a home details : {}",e.getMessage());
            response.setIsSuccess(false);
            response.setMessage(e.getMessage());
        }
        return  response;
    }

    public GetResponseHomeDetailsDTO getHomeDetails(Long id) {
         GetResponseHomeDetailsDTO response = new GetResponseHomeDetailsDTO();
         try {
             HomeDetails homeDetails = homeDetailsRepository.getReferenceById(id);
             log.info("Retrieved a home HomeDetails ID : {} ",id);
             response.setHome(homeDetails.getHome());
             response.setHomeImg(homeDetails.getHomeImg());
             response.setType(homeDetails.getType());
             response.setCode(homeDetails.getCode());
             response.setMessage("Successfully retrieved a homeDetails");
             response.setIsSuccess(true);

         }catch (Exception e){
             response.setIsSuccess(false);
             response.setMessage("Failed retrieved a homeDetails");
             log.error("Failed retrieved a homeDetails: {}",e.getMessage());
         }
         return response;
    }



}
