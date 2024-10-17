package org.example.homemade2.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.homemade2.dto.CreateHome;
import org.example.homemade2.dto.GetResponseHomeDTO;
import org.example.homemade2.dto.ResponseHomeDTO;
import org.example.homemade2.entity.Home;
import org.example.homemade2.exceptions.AlreadyExistException;
import org.example.homemade2.exceptions.NotFoundException;
import org.example.homemade2.mapper.HomeMapper;
import org.example.homemade2.repository.HomeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class HomeService {
    private final HomeRepository homeRepository;
    private final HomeMapper homeMapper;
    private final HandlerMapping resourceHandlerMapping;


    @Transactional
    public ResponseHomeDTO createHome(CreateHome createHome) throws NotFoundException{
        ResponseHomeDTO responseHomeDTO = new ResponseHomeDTO();
        Optional<Home> check =  homeRepository.findHomeByClient_id(createHome.client_id());
        if(check.isEmpty()){
                log.info("Staring adding a new home CLIENT ID : {}",createHome.client_id());
                homeRepository.save(HomeMapper.toEntity(createHome));
                log.info("Created a new home CLIENT ID : {}",createHome.client_id());
                responseHomeDTO.setIsSuccess(true);
                responseHomeDTO.setMessage("Successfully created a new home");
        }else {
            log.info("A new home has already been created with the CLIENT ID : {}",createHome.client_id());
            throw new AlreadyExistException("Home already exists");
        }
        return responseHomeDTO;
    }

    @Transactional
    public ResponseHomeDTO updateHome(CreateHome createHome,Long id) throws NotFoundException{
        ResponseHomeDTO responseHomeDTO = new ResponseHomeDTO();
        Optional<Home> check =  homeRepository.findById(id);
        if(check.isPresent()){
            log.info("Staring updating a home CLIENT ID : {}",createHome.client_id());
                homeRepository.save(HomeMapper.toEntity(createHome));
                log.info("Updated a home CLIENT ID : {}",id);
                responseHomeDTO.setIsSuccess(true);
                responseHomeDTO.setMessage("Successfully updated a home");
        }else {
            log.info(" A Home isn't exist with the CLIENT ID : {}",id);
            throw new NotFoundException("Home does not exist");
        }
        return responseHomeDTO;

    }



    @Transactional
    public ResponseHomeDTO deleteHome(Long id){
        ResponseHomeDTO responseHomeDTO = new ResponseHomeDTO();
        log.info("Staring deleting a home HOME ID : {}",id);
        Optional<Home> check =  homeRepository.findById(id);
        if(check.isPresent()){
            homeRepository.deleteById(id);
            log.info("Deleted a home HOME ID : {}",id);
            responseHomeDTO.setIsSuccess(true);
            responseHomeDTO.setMessage("Successfully deleted a home");
        }else {
            log.info(" A  Home isn't exist with the CLIENT ID : {}",id);
            throw new NotFoundException("Home does not exist");
        }
        return responseHomeDTO;

    }

    public GetResponseHomeDTO getHomeById(Long id){

        GetResponseHomeDTO responseHomeDTO = new GetResponseHomeDTO();
        log.info("Staring retrieving a home HOME ID : {}",id);
        Optional<Home> check =  homeRepository.findById(id);
        if(check.isPresent()){
            Home home = homeRepository.getReferenceById(id);
            log.info("Retrieved a home HOME ID : {}",id);
            responseHomeDTO.setName(home.getName());
            responseHomeDTO.setClient_id(home.getClient_id());
            responseHomeDTO.setHomeImg(home.getHomeImg());
            responseHomeDTO.setIsSuccess(true);
            responseHomeDTO.setMessage("Successfully retrieved a home");
        }else {
            log.error("Failed  retrieving a home : {} ",id);
            throw new NotFoundException("Home does not exist");
        }
        return responseHomeDTO;
    }

}
