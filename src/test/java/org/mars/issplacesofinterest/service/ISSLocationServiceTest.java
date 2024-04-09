package org.mars.issplacesofinterest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mars.issplacesofinterest.BaseTest;
import org.mars.issplacesofinterest.config.AppConfig;
import org.mars.issplacesofinterest.dto.ISSLocationDTO;
import org.mars.issplacesofinterest.dto.ISSLocationResponse;
import org.mars.issplacesofinterest.dto.ISSPosition;
import org.mars.issplacesofinterest.exceptionhandler.ISSLocationException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ISSLocationServiceTest extends BaseTest {
    @Mock
    private AppConfig appConfig;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ISSLocationService issLocationService;


    @Test
    void testGetISSLocation_Success() {
        // Mock ISSLocationResponse
        ISSLocationResponse mockResponse = new ISSLocationResponse();
        ISSPosition issPosition = new ISSPosition();
        issPosition.setLatitude(37.0);
        issPosition.setLongitude(-122.0);
        mockResponse.setIss_position(issPosition);

        issLocationService = new ISSLocationService(restTemplate, appConfig);


        // Mock AppConfig
        when(appConfig.getOpenNotifyApiBaseUrl()).thenReturn("http://api.open-notify.org/iss-now.json");

        // Mock restTemplate
        when(restTemplate.getForObject(anyString(), eq(ISSLocationResponse.class))).thenReturn(mockResponse);

        // Call the method
        ISSLocationDTO result = issLocationService.getISSLocation();

        // Verify the result
        assertNotNull(result);
        assertEquals(37.0, result.getLatitude());
        assertEquals(-122.0, result.getLongitude());
    }

    @Test
    void testGetISSLocation_Exception() {
        issLocationService = new ISSLocationService(restTemplate, appConfig);
        // Mock AppConfig
        when(appConfig.getOpenNotifyApiBaseUrl()).thenReturn("http://api.open-notify.org/iss-now.json");

        // Mock restTemplate to throw exception
        lenient().when(restTemplate.getForObject(anyString(), eq(ISSLocationResponse.class))).thenThrow(new RuntimeException("Test exception"));

        // Call the method
        ISSLocationException exception = assertThrows(ISSLocationException.class, () -> issLocationService.getISSLocation());

        // Verify the exception message
        assertEquals("Failed to retrieve ISS location: Test exception", exception.getMessage());
    }
}
