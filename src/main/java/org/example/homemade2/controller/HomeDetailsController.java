package org.example.homemade2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.homemade2.constants.Endpoints;
import org.example.homemade2.dto.CreateHomeDetails;
import org.example.homemade2.dto.GetResponseHomeDetailsDTO;
import org.example.homemade2.dto.ResponseHomeDetailsDTO;
import org.example.homemade2.entity.HomeDetails;
import org.example.homemade2.service.HomeDetailsService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class HomeDetailsController {
    private final HomeDetailsService homeDetailsService;
    @Operation(summary = "Creating Home Details")
    @ApiResponse(responseCode ="200",description = "Success",content = @Content(schema = @Schema(implementation = ResponseHomeDetailsDTO.class)))
    @PostMapping(Endpoints.HOME_DETAILS_CREATE)
    public ResponseEntity<ResponseHomeDetailsDTO> createHomeDetails(@RequestBody CreateHomeDetails createHomeDetails) {
        return new ResponseEntity<>(homeDetailsService.createHomeDetails(createHomeDetails), HttpStatusCode.valueOf(200));
    }


    @Operation(summary = "Updating Home Details")
    @ApiResponse(responseCode ="200",description = "Success",content = @Content(schema = @Schema(implementation = ResponseHomeDetailsDTO.class)))
    @PutMapping(Endpoints.HOME_DETAILS_UPDATE)
    public ResponseEntity<ResponseHomeDetailsDTO> updateHomeDetails(@PathVariable("id") Long id ,@RequestBody CreateHomeDetails createHomeDetails) {
        return new ResponseEntity<>(homeDetailsService.updateHomeDetails(createHomeDetails,id), HttpStatusCode.valueOf(200));
    }


    @Operation(summary = "Deleting Home Details")
    @ApiResponse(responseCode ="200",description = "Success",content = @Content(schema = @Schema(implementation = ResponseHomeDetailsDTO.class)))
    @DeleteMapping(Endpoints.HOME_DETAILS_DELETE)
    public ResponseEntity<ResponseHomeDetailsDTO> updateHomeDetails(@PathVariable("id") Long id) {
        return new ResponseEntity<>(homeDetailsService.deleteHomeDetails(id), HttpStatusCode.valueOf(200));
    }

    @Operation(summary = "Getting Home Details")
    @ApiResponse(responseCode ="200",description = "Success",content = @Content(schema = @Schema(implementation = ResponseHomeDetailsDTO.class)))
    @GetMapping(Endpoints.HOME_DETAILS_GET)
    public ResponseEntity<GetResponseHomeDetailsDTO> getHomeDetails(@PathVariable("id") Long id) {
        return new ResponseEntity<>(homeDetailsService.getHomeDetails(id), HttpStatusCode.valueOf(200));
    }








}
