package org.mars.issplacesofinterest.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mars.issplacesofinterest.BaseTest;
import org.mars.issplacesofinterest.config.AppConfig;
import org.mars.issplacesofinterest.entities.MediaWikiPlaces;
import org.mars.issplacesofinterest.entities.MediaWikiResponse;
import org.mars.issplacesofinterest.entities.PlacesOfInterestResponse;
import org.mars.issplacesofinterest.entities.Query;
import org.mars.issplacesofinterest.exceptionhandler.PlacesOfInterestException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlacesOfInterestServiceTest extends BaseTest {
    @Mock
    private AppConfig appConfig;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PlacesOfInterestService placesOfInterestService;

    @Test
    void testGetPlacesOfInterest_Success() {
        //given
        placesOfInterestService = new PlacesOfInterestService(restTemplate, appConfig);

        // Mock PlacesOfInterestResponse

        MediaWikiResponse mediaWikiResponse = new MediaWikiResponse();
        Query query = new Query();
        MediaWikiPlaces mediaWikiPlaces = new MediaWikiPlaces();
        double latitude = 46.342000;
        double longitude = -67.817000;
        mediaWikiPlaces.setCountry("United States");
        mediaWikiPlaces.setLat(latitude);
        mediaWikiPlaces.setLon(longitude);
        List<MediaWikiPlaces> geosearchList = new ArrayList<>();
        geosearchList.add(mediaWikiPlaces);
        query.setGeosearch(geosearchList);
        mediaWikiResponse.setQuery(query);

        String photonStrResponse = "{\"features\":[{\"geometry\":{\"coordinates\":[-67.8179139,46.341736],\"type\":\"Point\"},\"type\":\"Feature\",\"properties\":{\"osm_id\":358227116,\"country\":\"United States\",\"countrycode\":\"US\",\"postcode\":\"04760\",\"locality\":\"Jewells Corner\",\"county\":\"Aroostook County\",\"type\":\"house\",\"osm_type\":\"N\",\"osm_key\":\"man_made\",\"street\":\"Britton Road\",\"district\":\"Monticello\",\"osm_value\":\"mast\",\"name\":\"WXME-AM (Monticello)\",\"state\":\"Maine\"}}],\"type\":\"FeatureCollection\"}";

        // Mock AppConfig
        when(appConfig.getMediaWikiApiBaseUrl()).thenReturn("https://en.wikipedia.org/w/api.php");
        when(appConfig.getPhotonApiBaseUrl()).thenReturn("https://photon.komoot.io/reverse");

        // Mock restTemplate
        when(restTemplate.getForObject(anyString(), eq(MediaWikiResponse.class))).thenReturn(mediaWikiResponse);
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(photonStrResponse);

        // Call the method
        PlacesOfInterestResponse result = placesOfInterestService.getPlacesOfInterest(latitude, longitude);

        // Verify the result
        assertNotNull(result);
        assertEquals("United States", result.getResults().get(0).getCountry());


    }


    @Test
    void testGetISSLocation_Exception() {
        //given
        placesOfInterestService = new PlacesOfInterestService(restTemplate, appConfig);
        double latitude = 46.342000;
        double longitude = -67.817000;

        // Mock AppConfig
        when(appConfig.getMediaWikiApiBaseUrl()).thenReturn("https://en.wikipedia.org/w/api.php");

        // Mock restTemplate to throw exception
        lenient().when(restTemplate.getForObject(anyString(), eq(MediaWikiResponse.class))).thenThrow(new RuntimeException("Test exception"));

        // Call the method
        PlacesOfInterestException exception = assertThrows(PlacesOfInterestException.class, () -> placesOfInterestService.getPlacesOfInterest(latitude, longitude));

        // Verify the exception message
        assertEquals("Failed to retrieve places of interest: Test exception", exception.getMessage());
    }
}
