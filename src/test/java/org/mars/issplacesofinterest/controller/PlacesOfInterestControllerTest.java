package org.mars.issplacesofinterest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mars.issplacesofinterest.dto.ISSLocationDTO;
import org.mars.issplacesofinterest.dto.PlaceOfInterest;
import org.mars.issplacesofinterest.dto.PlacesOfInterestResponse;
import org.mars.issplacesofinterest.dto.Query;
import org.mars.issplacesofinterest.service.ISSLocationService;
import org.mars.issplacesofinterest.service.PlacesOfInterestService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlacesOfInterestControllerTest {
    @Mock
    private ISSLocationService issLocationService;

    @Mock
    private PlacesOfInterestService placesOfInterestService;

    @InjectMocks
    private PlacesOfInterestController placesOfInterestController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetISSLocation() {
        ISSLocationDTO mockLocationDTO = new ISSLocationDTO(89.000, 179.000);
        when(issLocationService.getISSLocation()).thenReturn(mockLocationDTO);

        ISSLocationDTO result = placesOfInterestController.getISSLocation();

        assertEquals(mockLocationDTO, result);
        verify(issLocationService, times(1)).getISSLocation();
    }

    @Test
    public void testGetPlacesOfInterest() {
        //given
        double latitude = 46.342000;
        double longitude = 67.817000;

        PlacesOfInterestResponse placesOfInterestResponse = new PlacesOfInterestResponse();
        Query query = new Query();
        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setCountry("Canada");
        placeOfInterest.setLat(latitude);
        placeOfInterest.setLon(longitude);
        List<PlaceOfInterest> geosearchList = new ArrayList<>();
        geosearchList.add(placeOfInterest);
        query.setGeosearch(geosearchList);
        placesOfInterestResponse.setQuery(query);

        ISSLocationDTO mockLocationDTO = new ISSLocationDTO(latitude, longitude);


        when(issLocationService.getISSLocation()).thenReturn(mockLocationDTO);
        when(placesOfInterestService.getPlacesOfInterest(mockLocationDTO.getLatitude(), mockLocationDTO.getLongitude())).thenReturn(placesOfInterestResponse);

        PlacesOfInterestResponse result = placesOfInterestController.getPlacesOfInterest(latitude, longitude);

        assertNotNull(result);
        verify(issLocationService, times(1)).getISSLocation();
        verify(placesOfInterestService, times(1)).getPlacesOfInterest(mockLocationDTO.getLatitude(), mockLocationDTO.getLongitude());
    }
}
