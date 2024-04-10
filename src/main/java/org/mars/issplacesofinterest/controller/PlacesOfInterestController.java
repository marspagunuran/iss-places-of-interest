package org.mars.issplacesofinterest.controller;

import lombok.extern.slf4j.Slf4j;
import org.mars.issplacesofinterest.entities.ISSLocationDTO;
import org.mars.issplacesofinterest.entities.PlacesOfInterestResponse;
import org.mars.issplacesofinterest.service.ISSLocationService;
import org.mars.issplacesofinterest.service.PlacesOfInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/iss_location")
@Validated
public class PlacesOfInterestController {

    @Autowired
    private ISSLocationService issLocationService;

    @Autowired
    private PlacesOfInterestService placesOfInterestService;

    private ISSLocationDTO issLocationDTO;


    @GetMapping
    public ISSLocationDTO getISSLocation() {
        issLocationDTO =  issLocationService.getISSLocation();
        return issLocationDTO;
    }

    @GetMapping(value = "/placesofinterest")
    public PlacesOfInterestResponse getPlacesOfInterest(
            @RequestParam(required = false) @NumberFormat @DecimalMin(value = "-90", message = "Latitude must be between -90 and 90")
            @DecimalMax(value = "90", message = "Latitude must be between -90 and 90") Double latitude,
            @RequestParam(required = false) @NumberFormat @DecimalMin(value = "-180", message = "Longitude must be between -180 and 180")
            @DecimalMax(value = "180", message = "Longitude must be between -180 and 180") Double longitude
    ) {
        issLocationDTO =  issLocationService.getISSLocation();
        double requestLatitude =  latitude != null ? latitude : issLocationDTO.getLatitude();
        double requestLongitude = longitude != null ? longitude : issLocationDTO.getLongitude();
        return placesOfInterestService.getPlacesOfInterest(requestLatitude, requestLongitude);
    }

}

