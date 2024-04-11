package org.mars.issplacesofinterest.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PlacesOfInterestTest {
    @Test
    void testEquals() {
        PlacesOfInterest place1 = new PlacesOfInterest();
        PlacesOfInterest place2 = new PlacesOfInterest();
        PlacesOfInterest place3 = new PlacesOfInterest();

        assertEquals(place1, place2);
        assertEquals(place1, place3);
    }

    @Test
    void testHashCode() {
        PlacesOfInterest place1 = new PlacesOfInterest();
        PlacesOfInterest place2 = new PlacesOfInterest();

        assertEquals(place1.hashCode(), place2.hashCode());
    }

    @Test
    void testToString() {
        PlacesOfInterest place = new PlacesOfInterest();
        assertEquals("PlacesOfInterest(title=null, latitude=0.0, longitude=0.0, country=null)", place.toString());
    }

    @Test
    void testCanEqual() {
        PlacesOfInterest place1 = new PlacesOfInterest();
        PlacesOfInterest place2 = new PlacesOfInterest();
        PlacesOfInterest place3 = new PlacesOfInterest();

        assertTrue(place1.canEqual(place2));
        assertTrue(place1.canEqual(place3));
    }
}
