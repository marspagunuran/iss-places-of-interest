package org.mars.issplacesofinterest.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mars.issplacesofinterest.service.ISSLocationService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


public class AppConfigTest {
    @Mock
    private RestTemplateBuilder restTemplateBuilder;

    private AppConfig appConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        appConfig = new AppConfig();
    }

    @Test
    void restTemplate() {
        RestTemplate restTemplate = appConfig.restTemplate();
        assertEquals(RestTemplate.class, restTemplate.getClass());
    }

    @Test
    void issLocationService() {
        when(restTemplateBuilder.build()).thenReturn(new RestTemplate());
        ISSLocationService issLocationService = appConfig.issLocationService();
        assertEquals(ISSLocationService.class, issLocationService.getClass());
    }

    @Test
    void gettersAndSetters() {
        String openNotifyApiBaseUrl = "http://open.notify.org";
        String mediaWikiApiBaseUrl = "http://mediawiki.org";
        String photonApiBaseUrl = "http://photon.org";

        appConfig.setOpenNotifyApiBaseUrl(openNotifyApiBaseUrl);
        appConfig.setMediaWikiApiBaseUrl(mediaWikiApiBaseUrl);
        appConfig.setPhotonApiBaseUrl(photonApiBaseUrl);

        assertEquals(openNotifyApiBaseUrl, appConfig.getOpenNotifyApiBaseUrl());
        assertEquals(mediaWikiApiBaseUrl, appConfig.getMediaWikiApiBaseUrl());
        assertEquals(photonApiBaseUrl, appConfig.getPhotonApiBaseUrl());
    }

    @Test
    void testHashCode() {
        AppConfig appConfig1 = new AppConfig();
        AppConfig appConfig2 = new AppConfig();
        assertEquals(appConfig1.hashCode(), appConfig2.hashCode());
    }

    @Test
    void testEquals() {
        AppConfig appConfig1 = new AppConfig();
        AppConfig appConfig2 = new AppConfig();
        assertTrue(appConfig1.equals(appConfig2));
    }

    @Test
    void testToString() {
        AppConfig appConfig = new AppConfig();
        String expectedToString = "AppConfig(openNotifyApiBaseUrl=null, mediaWikiApiBaseUrl=null, photonApiBaseUrl=null)";
        assertEquals(expectedToString, appConfig.toString());
    }

    @Test
    void testCanEqual() {
        AppConfig appConfig1 = new AppConfig();
        AppConfig appConfig2 = new AppConfig();
        assertTrue(appConfig1.canEqual(appConfig2));
    }
}
