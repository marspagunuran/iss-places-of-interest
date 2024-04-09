package org.mars.issplacesofinterest.service;

import org.mars.issplacesofinterest.config.AppConfig;
import org.mars.issplacesofinterest.dto.ISSLocationDTO;
import org.mars.issplacesofinterest.dto.ISSLocationResponse;
import org.mars.issplacesofinterest.dto.ISSPosition;
import org.mars.issplacesofinterest.exceptionhandler.ISSLocationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ISSLocationService {

     private final AppConfig appConfig;

    private final RestTemplate restTemplate;

    public ISSLocationService(RestTemplate restTemplate, AppConfig appConfig) {
        this.restTemplate = restTemplate;
        this.appConfig = appConfig;
    }


    public ISSLocationDTO getISSLocation() {
        ISSLocationResponse response;
        try {
            response = restTemplate.getForObject(appConfig.getOpenNotifyApiBaseUrl(), ISSLocationResponse.class);
        } catch (Exception e) {
            throw new ISSLocationException("Failed to retrieve ISS location: " + e.getMessage());
        }

        if (response != null && response.getIss_position() != null) {
            ISSPosition issPosition = response.getIss_position();
            return new ISSLocationDTO(issPosition.getLatitude(), issPosition.getLongitude());
        } else {
            throw new ISSLocationException("Failed to retrieve ISS location");
        }
    }
}
