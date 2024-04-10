package org.mars.issplacesofinterest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mars.issplacesofinterest.entities.*;
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

        MediaWikiResponse mediaWikiResponse = new MediaWikiResponse();
        Query query = new Query();
        MediaWikiPlaces mediaWikiPlaces = new MediaWikiPlaces();
        mediaWikiPlaces.setCountry("Canada");
        mediaWikiPlaces.setLat(latitude);
        mediaWikiPlaces.setLon(longitude);
        List<MediaWikiPlaces> geosearchList = new ArrayList<>();
        geosearchList.add(mediaWikiPlaces);
        query.setGeosearch(geosearchList);
        mediaWikiResponse.setQuery(query);
        PlacesOfInterestResponse placesOfInterestResponse = new PlacesOfInterestResponse();
        List<PlacesOfInterest> placesOfInterests = new ArrayList<>();
        List<MediaWikiPlaces> mediaWikiList = mediaWikiResponse.getQuery().getGeosearch();
        if(!mediaWikiList.isEmpty()) {
            for (MediaWikiPlaces mediaWikiPlace : mediaWikiList) {
                PlacesOfInterest placesOfInterest =new PlacesOfInterest();
                placesOfInterest.setTitle(mediaWikiPlace.getTitle());
                placesOfInterest.setLongitude(mediaWikiPlace.getLon());
                placesOfInterest.setLatitude(mediaWikiPlace.getLat());
                placesOfInterest.setCountry(mediaWikiPlace.getCountry());
                placesOfInterests.add(placesOfInterest);

            }
        }
        placesOfInterestResponse.setResults(placesOfInterests);


        ISSLocationDTO mockLocationDTO = new ISSLocationDTO(latitude, longitude);


        when(issLocationService.getISSLocation()).thenReturn(mockLocationDTO);
        when(placesOfInterestService.getPlacesOfInterest(mockLocationDTO.getLatitude(), mockLocationDTO.getLongitude())).thenReturn(placesOfInterestResponse);

        PlacesOfInterestResponse result = placesOfInterestController.getPlacesOfInterest(latitude, longitude);

        assertNotNull(result);
        verify(issLocationService, times(1)).getISSLocation();
        verify(placesOfInterestService, times(1)).getPlacesOfInterest(mockLocationDTO.getLatitude(), mockLocationDTO.getLongitude());
    }
}
