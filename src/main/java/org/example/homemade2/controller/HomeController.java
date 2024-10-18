package org.example.homemade2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.homemade2.constants.Endpoints;
import org.example.homemade2.dto.CreateHome;
import org.example.homemade2.dto.GetResponseHomeDTO;
import org.example.homemade2.dto.ResponseHomeDTO;
import org.example.homemade2.service.HomeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class HomeController {
    private final HomeService homeService;
    @Operation(summary = "Adding a new home")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = ResponseHomeDTO.class)))
    @PostMapping(Endpoints.HOME_CREATE)
    public ResponseEntity<ResponseHomeDTO> createHome(@RequestBody CreateHome createHome) {
        return new ResponseEntity<>(homeService.createHome(createHome), HttpStatus.CREATED);
    }
    @Operation(summary = "Updating home")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = ResponseHomeDTO.class)))
    @PutMapping(Endpoints.HOME_UPDATE)
    public ResponseEntity<ResponseHomeDTO> updateHome(@PathVariable("id") Long id, @RequestBody CreateHome createHome) {
        return new ResponseEntity<>(homeService.updateHome(createHome, id), HttpStatus.OK);
    }
    @Operation(summary = "Deleting home")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = ResponseHomeDTO.class)))
    @DeleteMapping(Endpoints.HOME_DELETE)
    public ResponseEntity<ResponseHomeDTO> deleteHome(@PathVariable("id") Long id) {
        return new ResponseEntity<>(homeService.deleteHome(id), HttpStatus.OK);
    }

    @Operation(summary = "Getting home")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = GetResponseHomeDTO.class)))
    @GetMapping(Endpoints.HOME_GET)
    public ResponseEntity<GetResponseHomeDTO> getHome(@PathVariable("id") Long id) {
        return new ResponseEntity<>(homeService.getHomeById(id), HttpStatus.OK);
    }







}
