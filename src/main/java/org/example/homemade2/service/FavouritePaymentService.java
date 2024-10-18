package org.example.homemade2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.homemade2.dto.CreateFavouritePayment;
import org.example.homemade2.dto.GetResponseFavPayDTO;
import org.example.homemade2.dto.ResponseFavPayDTO;
import org.example.homemade2.entity.FavouritePayment;
import org.example.homemade2.entity.HomeDetails;
import org.example.homemade2.exceptions.AlreadyExistException;
import org.example.homemade2.exceptions.NotFoundException;
import org.example.homemade2.exceptions.ShouldBeUniqueException;
import org.example.homemade2.mapper.FavouritePaymentMapper;
import org.example.homemade2.repository.FavouritePaymentRepository;
import org.example.homemade2.repository.HomeDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
            FavouritePayment favouritePayment = FavouritePaymentMapper.toEntity(createFavouritePayment);
            List<FavouritePayment> favouritePayments = favouritePaymentRepository.findFavouritePaymentByHomeDetails(homeDetails.get());
            if (favouritePayments.isEmpty()) {
                favouritePayment.setHomeDetails(homeDetails.get());
                favouritePaymentRepository.save(favouritePayment);
                responseFavPayDTO.setIsSuccess(true);
                responseFavPayDTO.setMessage("Favourite payment successfully created");
                log.info("Favourite payment successfully created : {}", favouritePayment.getId());


            }
            else {
                log.error("Favourite payment already exist");
                throw new AlreadyExistException("Favourite payment already exist");
            }
        }else {
            log.error("Home details not found");
            throw new NotFoundException("Home details not found");
        }
        return responseFavPayDTO;
    }


    public ResponseFavPayDTO updateFavouritePayment(CreateFavouritePayment createFavouritePayment,Long id){
        Optional<HomeDetails> homeDetails = homeDetailsRepository.findHomeDetailsById(createFavouritePayment.homeDetails_id());
        ResponseFavPayDTO responseFavPayDTO = new ResponseFavPayDTO();
        if (homeDetails.isPresent()) {
                Optional<FavouritePayment> favouritePayment  = favouritePaymentRepository.findById(id);
                if (favouritePayment.isPresent()) {
                    if (favouritePayment.get().getHomeDetails().equals(homeDetails.get())) {
                        FavouritePayment favouritePayment2 = FavouritePaymentMapper.toEntity(createFavouritePayment);
                        favouritePayment2.setId(id);
                        favouritePayment2.setHomeDetails(homeDetails.get());
                        favouritePaymentRepository.save(favouritePayment2);
                        responseFavPayDTO.setIsSuccess(true);
                        responseFavPayDTO.setMessage("Favourite payment successfully updated");
                        log.info("Favourite payment successfully updated");
                    }else {
                        throw new ShouldBeUniqueException("You cann't update another favourite payment");
                    }

                }else {
                    throw new NotFoundException("Favourite payment not found");
                }
        }else {
            log.error("Home details not found ");
            throw new NotFoundException("Home details not found");
        }
        return responseFavPayDTO;
    }

    public ResponseFavPayDTO deleteFavouritePaymentById(Long id){
        ResponseFavPayDTO responseFavPayDTO = new ResponseFavPayDTO();
        Optional<FavouritePayment> favouritePayment = favouritePaymentRepository.findById(id);
        if (favouritePayment.isPresent()) {
            FavouritePayment favouritePayment2 = favouritePayment.get();
            favouritePayment2.setHomeDetails(null);
            favouritePaymentRepository.save(favouritePayment2);
            favouritePaymentRepository.deleteById(id);
            responseFavPayDTO.setIsSuccess(true);
            responseFavPayDTO.setMessage("Favourite payment successfully deleted");
            log.info("Favourite payment successfully deleted");
        }else {
            log.error("Favourite payment not found");
            throw new NotFoundException("Favourite payment not found");
        }

        return responseFavPayDTO;
    }

    public GetResponseFavPayDTO getFavouritePayment(Long id){
        GetResponseFavPayDTO responseFavPayDTO = new GetResponseFavPayDTO();
        Optional<FavouritePayment> favouritePayment = favouritePaymentRepository.findById(id);
        if (favouritePayment.isPresent()) {
            FavouritePayment favouritePayment2 = favouritePayment.get();
            responseFavPayDTO.setHomeDetails(favouritePayment2.getHomeDetails());
            responseFavPayDTO.setAmount(favouritePayment2.getAmount());
            log.info("Favourite payment successfully retrieved");
        }else {
            log.error("Favourite payment not found ");
            throw new NotFoundException("Favourite payment not found");
        }
        return responseFavPayDTO;
    }












}
