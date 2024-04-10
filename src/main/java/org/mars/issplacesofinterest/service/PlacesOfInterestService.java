package org.mars.issplacesofinterest.service;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.mars.issplacesofinterest.config.AppConfig;
import org.mars.issplacesofinterest.entities.MediaWikiPlaces;
import org.mars.issplacesofinterest.entities.MediaWikiResponse;
import org.mars.issplacesofinterest.entities.PlacesOfInterest;
import org.mars.issplacesofinterest.entities.PlacesOfInterestResponse;
import org.mars.issplacesofinterest.exceptionhandler.PlacesOfInterestException;
import org.mars.issplacesofinterest.utils.QueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class PlacesOfInterestService {

    private final AppConfig appConfig;

    private final RestTemplate restTemplate;

    public PlacesOfInterestService(RestTemplate restTemplate, AppConfig appConfig) {
        this.restTemplate = restTemplate;
        this.appConfig = appConfig;
    }

    public PlacesOfInterestResponse getPlacesOfInterest(double latitude, double longitude) {
        String apiUrl = String.format("%s" + QueryBuilder.media_wiki_api_buildQuery(latitude, longitude), appConfig.getMediaWikiApiBaseUrl());
        log.debug("media wiki  apiUrl = " + apiUrl);

        try {
            MediaWikiResponse mediaWikiResult =  restTemplate.getForObject(apiUrl, MediaWikiResponse.class);
            List<MediaWikiPlaces> places = (mediaWikiResult != null && !mediaWikiResult.getQuery().getGeosearch().isEmpty())
                                                                ? mediaWikiResult.getQuery().getGeosearch() : null;

            // Fetch additional details for each place
            for (MediaWikiPlaces place : Objects.requireNonNull(places)) {
                String country = getCountryName(place.getLat(), place.getLon());
                log.debug("Country = " + country);
                place.setCountry(country);

            }

            return getPlacesOfInterestResponse(Objects.requireNonNull(mediaWikiResult));

        } catch (NullPointerException e) {
            throw new PlacesOfInterestException("Empty for places of interest: " + e.getMessage());
        }
        catch (Exception e) {
            throw new PlacesOfInterestException("Failed to retrieve places of interest: " + e.getMessage());
        }
    }

    private static PlacesOfInterestResponse getPlacesOfInterestResponse(MediaWikiResponse response) {
        PlacesOfInterestResponse placesOfInterestResponse = new PlacesOfInterestResponse();
        ArrayList<PlacesOfInterest> placesOfInterests = new ArrayList<>();
        List<MediaWikiPlaces> mediaWikiPlaces = response.getQuery().getGeosearch();
        if(!mediaWikiPlaces.isEmpty()) {
            for (MediaWikiPlaces mediaWikiPlace : mediaWikiPlaces) {
                PlacesOfInterest placesOfInterest = new PlacesOfInterest();
                placesOfInterest.setTitle(mediaWikiPlace.getTitle());
                placesOfInterest.setLongitude(mediaWikiPlace.getLon());
                placesOfInterest.setLatitude(mediaWikiPlace.getLat());
                placesOfInterest.setCountry(mediaWikiPlace.getCountry());
                placesOfInterests.add(placesOfInterest);

            }
            placesOfInterestResponse.setResults(placesOfInterests);
        }
        return placesOfInterestResponse;
    }

    public String getCountryName(double latitude, double longitude) {
        try {
            // Construct the API URL
            String apiUrl = String.format("%s" + QueryBuilder.photon_api_buildQuery(latitude, longitude), appConfig.getPhotonApiBaseUrl());
            log.debug("Photon apiUrl = " + apiUrl);

            // Make the HTTP GET request using RestTemplate
            String response = restTemplate.getForObject(apiUrl, String.class);

            JSONParser parser = new JSONParser();

            // Parse the JSON response
            JSONObject json = (JSONObject) parser.parse(response);
            JSONArray features = (JSONArray) json.get("features");
            if (!features.isEmpty()) {
                JSONObject firstFeature = (JSONObject) features.get(0);
                JSONObject properties = (JSONObject) firstFeature.get("properties");
                return (String) properties.get("country");
            } else {
                return null;
            }
        } catch (Exception e) {
            // Handle exceptions
            throw new PlacesOfInterestException("Failed in getCountryName: " + e.getMessage());
        }
    }
}
