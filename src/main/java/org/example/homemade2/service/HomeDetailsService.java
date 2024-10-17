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
import org.example.homemade2.exceptions.AlreadyExistException;
import org.example.homemade2.exceptions.NotFoundException;
import org.example.homemade2.mapper.HomeDetailsMapper;
import org.example.homemade2.mapper.HomeMapper;
import org.example.homemade2.repository.HomeDetailsRepository;
import org.example.homemade2.repository.HomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

               List<HomeDetails> homeDetailsList = homeDetailsRepository.findHomeDetailsByHome(homeCheck.get());
               homeDetails1.setHome(homeCheck.get());
            for (HomeDetails details : homeDetailsList) {
                if (homeDetails1.equals(details)) {
                    throw new AlreadyExistException("Home details already exist");
                }
            }

                homeDetailsRepository.save(homeDetails1);
                log.info("Saved homeDetails successfully: {}", homeDetails.homeId());
                response.setIsSuccess(true);
                response.setMessage("Saved homeDetails successfully");

        }else {
            log.error("Home  not found");
            throw new NotFoundException("Home  not found");
        }
        return  response;

    }

    @Transactional
    public ResponseHomeDetailsDTO updateHomeDetails(CreateHomeDetails createHomeDetails,Long homeDetailsId) {
        Optional<Home> homeCheck = homeRepository.findById(createHomeDetails.homeId());
        ResponseHomeDetailsDTO response = new ResponseHomeDetailsDTO();
        HomeDetails homeDetails1 = HomeDetailsMapper.toEntity(createHomeDetails);
        if (homeCheck.isPresent()) {
                Optional<HomeDetails> homeDetailsCheck = homeDetailsRepository.findById(homeDetailsId);
                if (homeDetailsCheck.isPresent()) {
                    homeDetails1.setHome(homeCheck.get());
                    homeDetails1.setId(homeDetailsId);
                    homeDetailsRepository.save(homeDetails1);
                    response.setIsSuccess(true);
                    response.setMessage("Updated homeDetails successfully");
                    log.info("Updated homeDetails successfully");
                }else {
                    log.error("Home Details not found");
                    throw new NotFoundException("Home Details not found");
                }


        }else {
            log.error("Home  not found when updating a home details : {}",createHomeDetails.homeId());
            throw new NotFoundException("Home not found");
        }
        return  response;
    }



    @Transactional
    public ResponseHomeDetailsDTO deleteHomeDetails(Long id) {
        ResponseHomeDetailsDTO response = new ResponseHomeDetailsDTO();
        Optional<HomeDetails> homeDetails = homeDetailsRepository.findById(id);
        if (homeDetails.isPresent()) {
            homeDetailsRepository.deleteById(id);
            log.info("Deleted homeDetails successfully");
            response.setIsSuccess(true);
            response.setMessage("Deleted homeDetails successfully");
        }else {
            log.error("Home Details  not found");
            throw new NotFoundException("Home Details not found");
        }
        return  response;
    }

    public GetResponseHomeDetailsDTO getHomeDetails(Long id) {
         GetResponseHomeDetailsDTO response = new GetResponseHomeDetailsDTO();
         Optional<HomeDetails> homeDetails = homeDetailsRepository.findById(id);
         if (homeDetails.isPresent()) {
             HomeDetails homeDetails2 = homeDetails.get();
             log.info("Retrieved a home HomeDetails ID : {} ",id);
             response.setHome(homeDetails2.getHome());
             response.setHomeImg(homeDetails2.getHomeImg());
             response.setType(homeDetails2.getType());
             response.setCode(homeDetails2.getCode());
             response.setMessage("Successfully retrieved a homeDetails");
             response.setIsSuccess(true);
         }else {
             log.error("Failed retrieved a homeDetails");
             throw new NotFoundException("Failed retrieved a homeDetails");
         }
         return response;
    }



}
