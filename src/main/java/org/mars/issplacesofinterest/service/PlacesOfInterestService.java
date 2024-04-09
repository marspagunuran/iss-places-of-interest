package org.mars.issplacesofinterest.service;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.mars.issplacesofinterest.config.AppConfig;
import org.mars.issplacesofinterest.dto.PlaceOfInterest;
import org.mars.issplacesofinterest.dto.PlacesOfInterestResponse;
import org.mars.issplacesofinterest.exceptionhandler.PlacesOfInterestException;
import org.mars.issplacesofinterest.utils.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PlacesOfInterestService {

    private final AppConfig appConfig;

    private static final String PHOTON_API_QUERY_PARAMS = "%s?lon=%f&lat=%f";

    private final RestTemplate restTemplate;

    public PlacesOfInterestService(RestTemplate restTemplate, AppConfig appConfig) {
        this.restTemplate = restTemplate;
        this.appConfig = appConfig;
    }

    public PlacesOfInterestResponse getPlacesOfInterest(double latitude, double longitude) {
        String apiUrl = String.format("%s" + QueryBuilder.media_wiki_api_buildQuery(latitude, longitude), appConfig.getMediaWikiApiBaseUrl());
        log.debug("media wiki  apiUrl = " + apiUrl);

        try {
            //return restTemplate.getForObject(url, PlacesOfInterestResponse.class);
            PlacesOfInterestResponse response =  restTemplate.getForObject(apiUrl, PlacesOfInterestResponse.class);
            List<PlaceOfInterest> places = response != null ? response.getQuery().getGeosearch() : null;

            // Fetch additional details for each place
            for (PlaceOfInterest place : places) {
                String country = getCountryName(place.getLat(), place.getLon());
                log.debug("Country = " + country);
                place.setCountry(country);

            }

            Objects.requireNonNull(response).getQuery().setGeosearch(places);
            return  response;

        } catch (Exception e) {
            throw new PlacesOfInterestException("Failed to retrieve places of interest: " + e.getMessage());
        }
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
