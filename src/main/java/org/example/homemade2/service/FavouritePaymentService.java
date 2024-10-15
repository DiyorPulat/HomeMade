package org.example.homemade2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.homemade2.dto.CreateFavouritePayment;
import org.example.homemade2.dto.GetResponseFavPayDTO;
import org.example.homemade2.dto.ResponseFavPayDTO;
import org.example.homemade2.entity.FavouritePayment;
import org.example.homemade2.entity.HomeDetails;
import org.example.homemade2.mapper.FavouritePaymentMapper;
import org.example.homemade2.repository.FavouritePaymentRepository;
import org.example.homemade2.repository.HomeDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavouritePaymentService {
    private final FavouritePaymentRepository favouritePaymentRepository;
    private final HomeDetailsRepository homeDetailsRepository;
    public ResponseFavPayDTO createFavouritePayment(CreateFavouritePayment createFavouritePayment) {
        Optional<HomeDetails> homeDetails = homeDetailsRepository.findHomeDetailsById(createFavouritePayment.homeDetails_id());
        ResponseFavPayDTO responseFavPayDTO = new ResponseFavPayDTO();
        if (homeDetails.isPresent()) {
            try {
                FavouritePayment favouritePayment = FavouritePaymentMapper.toEntity(createFavouritePayment);
                favouritePayment.setHomeDetails(homeDetails.get());
                favouritePaymentRepository.save(favouritePayment);
                responseFavPayDTO.setIsSuccess(true);
                responseFavPayDTO.setMessage("Favourite payment successfully created");
                log.info("Favourite payment successfully created : {}", favouritePayment.getId());
            }catch (Exception e) {
                responseFavPayDTO.setIsSuccess(false);
                responseFavPayDTO.setMessage("Favourite payment failed");
                log.error("Favourite payment failed : {}", e.getMessage());
            }


        }else {
            responseFavPayDTO.setIsSuccess(false);
            responseFavPayDTO.setMessage("Home details not found");
            log.error("Home details not found : {}", responseFavPayDTO.getIsSuccess());
        }
        return responseFavPayDTO;
    }


    public ResponseFavPayDTO updateFavouritePayment(CreateFavouritePayment createFavouritePayment){
        Optional<HomeDetails> homeDetails = homeDetailsRepository.findHomeDetailsById(createFavouritePayment.homeDetails_id());
        ResponseFavPayDTO responseFavPayDTO = new ResponseFavPayDTO();
        if (homeDetails.isPresent()) {
            try {
                FavouritePayment favouritePayment = FavouritePaymentMapper.toEntity(createFavouritePayment);
                favouritePayment.setHomeDetails(homeDetails.get());
                favouritePaymentRepository.save(favouritePayment);
                responseFavPayDTO.setIsSuccess(true);
                responseFavPayDTO.setMessage("Favourite payment successfully updated");
                log.info("Favourite payment successfully updated");


            }catch (Exception e) {
                responseFavPayDTO.setIsSuccess(false);
                responseFavPayDTO.setMessage("Favourite payment failed");
                log.error("Favourite payment fail : {}", e.getMessage());
            }
        }else {
            responseFavPayDTO.setIsSuccess(false);
            responseFavPayDTO.setMessage("Home details not found");
            log.error("Home details not found: {}", responseFavPayDTO.getIsSuccess());
        }
        return responseFavPayDTO;
    }

    public ResponseFavPayDTO deleteFavouritePaymentById(Long id){
        ResponseFavPayDTO responseFavPayDTO = new ResponseFavPayDTO();
        try {
            favouritePaymentRepository.deleteById(id);
            responseFavPayDTO.setIsSuccess(true);
            responseFavPayDTO.setMessage("Favourite payment successfully deleted");
            log.info("Favourite payment successfully deleted");

        }catch (Exception e){
            responseFavPayDTO.setIsSuccess(false);
            responseFavPayDTO.setMessage("Favourite payment failed");
            log.error("Favourite payment failed: {}", e.getMessage());
        }
        return responseFavPayDTO;
    }

    public GetResponseFavPayDTO findFavouritePaymentById(Long id){
        GetResponseFavPayDTO responseFavPayDTO = new GetResponseFavPayDTO();
        try {
            FavouritePayment favouritePayment = favouritePaymentRepository.findById(id).get();
            responseFavPayDTO.setHomeDetails(favouritePayment.getHomeDetails());
            responseFavPayDTO.setAmount(favouritePayment.getAmount());
            log.info("Favourite payment successfully retrieved");
        }catch (Exception e){
            log.error("Favourite payment retrieving failed : {}", e.getMessage());
        }
        return responseFavPayDTO;
    }












}
