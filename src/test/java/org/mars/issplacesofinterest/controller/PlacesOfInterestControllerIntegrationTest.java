package org.mars.issplacesofinterest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
public class PlacesOfInterestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetPlacesOfInterest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/iss_location/placesofinterest")
                        .param("latitude", "46.310000")
                        .param("longitude", "-67.817000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.query.geosearch").exists());
    }

    @Test
    void testGetPlacesOfInterestWithoutParams() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/iss_location//placesofinterest")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.query.geosearch").exists());
    }

    @Test
    void testGetPlacesOfInterestWithInvalidParams() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/iss_location//placesofinterest")
                        .param("latitude", "1s00")
                        .param("longitude", "200")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
