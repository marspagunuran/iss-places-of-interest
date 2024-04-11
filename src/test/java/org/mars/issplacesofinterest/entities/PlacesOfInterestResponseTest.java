package org.mars.issplacesofinterest.entities;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlacesOfInterestResponseTest {
    @Test
    void testEquals() {
        List<PlacesOfInterest> results1 = new ArrayList<>();
        results1.add(new PlacesOfInterest());
        PlacesOfInterestResponse response1 = new PlacesOfInterestResponse();

        List<PlacesOfInterest> results2 = new ArrayList<>();
        results2.add(new PlacesOfInterest());
        PlacesOfInterestResponse response2 = new PlacesOfInterestResponse();

        assertEquals(response1, response2);
    }

    @Test
    void testHashCode() {
        List<PlacesOfInterest> results1 = new ArrayList<>();
        results1.add(new PlacesOfInterest());
        PlacesOfInterestResponse response1 = new PlacesOfInterestResponse();

        List<PlacesOfInterest> results2 = new ArrayList<>();
        results2.add(new PlacesOfInterest());
        PlacesOfInterestResponse response2 = new PlacesOfInterestResponse();

        assertEquals(response1.hashCode(), response2.hashCode());
    }

    @Test
    void testToString() {
        List<PlacesOfInterest> results = new ArrayList<>();
        results.add(new PlacesOfInterest());
        PlacesOfInterestResponse response = new PlacesOfInterestResponse();

        assertEquals("PlacesOfInterestResponse(results=null)", response.toString());
    }

    @Test
    void testCanEqual() {
        List<PlacesOfInterest> results1 = new ArrayList<>();
        results1.add(new PlacesOfInterest());
        PlacesOfInterestResponse response1 = new PlacesOfInterestResponse();

        List<PlacesOfInterest> results2 = new ArrayList<>();
        results2.add(new PlacesOfInterest());
        PlacesOfInterestResponse response2 = new PlacesOfInterestResponse();



        assertTrue(response1.canEqual(response2));

    }
}
