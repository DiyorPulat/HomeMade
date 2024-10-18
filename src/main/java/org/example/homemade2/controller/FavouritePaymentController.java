package org.example.homemade2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.homemade2.constants.Endpoints;
import org.example.homemade2.dto.CreateFavouritePayment;
import org.example.homemade2.dto.GetResponseFavPayDTO;
import org.example.homemade2.dto.ResponseFavPayDTO;
import org.example.homemade2.dto.ResponseHomeDTO;
import org.example.homemade2.entity.FavouritePayment;
import org.example.homemade2.service.FavouritePaymentService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class FavouritePaymentController {
    private final FavouritePaymentService favouritePaymentService;
    @Operation(summary = "Creating FAVOURITE PAYMENT")
    @ApiResponse(responseCode = "200",description = "Success",content =  @Content(schema = @Schema(implementation = ResponseHomeDTO.class)))
    @PostMapping(Endpoints.FAVOURITE_PAYMENT_CREATE)
    public ResponseEntity<ResponseFavPayDTO> createFavouritePayment(@RequestBody CreateFavouritePayment createFavouritePayment) {
        return new ResponseEntity<>(favouritePaymentService.createFavouritePayment(createFavouritePayment), HttpStatusCode.valueOf(200));
    }

    @Operation(summary = "Updating FAVOURITE PAYMENT")
    @ApiResponse(responseCode = "200",description = "Success",content =  @Content(schema = @Schema(implementation = ResponseHomeDTO.class)))
    @PutMapping(Endpoints.FAVOURITE_PAYMENT_UPDATE)
    public ResponseEntity<ResponseFavPayDTO> updateFavouritePayment(@PathVariable("id") Long id,@RequestBody CreateFavouritePayment createFavouritePayment) {
        return new ResponseEntity<>(favouritePaymentService.updateFavouritePayment(createFavouritePayment,id), HttpStatusCode.valueOf(200));
    }

    @Operation(summary = "Deleting FAVOURITE PAYMENT")
    @ApiResponse(responseCode = "200",description = "Success",content =  @Content(schema = @Schema(implementation = ResponseHomeDTO.class)))
    @DeleteMapping(Endpoints.FAVOURITE_PAYMENT_DELETE)
    public ResponseEntity<ResponseFavPayDTO> deleteFavouritePayment(@PathVariable("id") Long id) {
        return new ResponseEntity<>(favouritePaymentService.deleteFavouritePaymentById(id), HttpStatusCode.valueOf(200));
    }


    @Operation(summary = "Getting FAVOURITE PAYMENT")
    @ApiResponse(responseCode = "200",description = "Success",content =  @Content(schema = @Schema(implementation = ResponseHomeDTO.class)))
    @GetMapping(Endpoints.FAVOURITE_PAYMENT_GET)
    public ResponseEntity<GetResponseFavPayDTO> getFavouritePayment(@PathVariable("id") Long id) {
        return new ResponseEntity<>(favouritePaymentService.getFavouritePayment(id), HttpStatusCode.valueOf(200));
    }





}
