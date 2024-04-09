package org.mars.issplacesofinterest.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlaceOfInterestTest {
    @Test
    void testGettersAndSetters() {
        // Create a PlaceOfInterest object
        PlaceOfInterest place = new PlaceOfInterest();

        // Set values using setter methods
        place.setTitle("Example Place");
        place.setLat(37.123);
        place.setLon(-122.456);
        place.setCountry("Country");

        // Verify values using getter methods
        assertEquals("Example Place", place.getTitle());
        assertEquals(37.123, place.getLat());
        assertEquals(-122.456, place.getLon());
        assertEquals("Country", place.getCountry());
    }

    @Test
    void testToString() {
        // Create a PlaceOfInterest object
        PlaceOfInterest place = new PlaceOfInterest();
        place.setTitle("Example Place");
        place.setLat(37.123);
        place.setLon(-122.456);
        place.setCountry("Country");

        // Verify the toString method
        assertEquals("PlaceOfInterest(title=Example Place, lat=37.123, lon=-122.456, country=Country)", place.toString());
    }

    @Test
    void testHashCode() {
        // Create two PlaceOfInterest objects with the same attribute values
        PlaceOfInterest place1 = new PlaceOfInterest();
        place1.setTitle("Example Place");
        place1.setLat(37.123);
        place1.setLon(-122.456);
        place1.setCountry("Country");

        PlaceOfInterest place2 = new PlaceOfInterest();
        place2.setTitle("Example Place");
        place2.setLat(37.123);
        place2.setLon(-122.456);
        place2.setCountry("Country");

        // Verify that the hash codes are equal
        assertEquals(place1.hashCode(), place2.hashCode());
    }

    @Test
    void testEquals() {
        // Create two PlaceOfInterest objects with the same attribute values
        PlaceOfInterest place1 = new PlaceOfInterest();
        place1.setTitle("Example Place");
        place1.setLat(37.123);
        place1.setLon(-122.456);
        place1.setCountry("Country");

        PlaceOfInterest place2 = new PlaceOfInterest();
        place2.setTitle("Example Place");
        place2.setLat(37.123);
        place2.setLon(-122.456);
        place2.setCountry("Country");

        // Verify that objects with the same attribute values are equal
        assertTrue(place1.equals(place2));

        // Create two PlaceOfInterest objects with different attribute values
        PlaceOfInterest place3 = new PlaceOfInterest();
        place3.setTitle("Different Place");
        place3.setLat(37.456);
        place3.setLon(-122.789);
        place3.setCountry("Other Country");

        // Verify that objects with different attribute values are not equal
        assertFalse(place1.equals(place3));
    }

    @Test
    void testCanEqual() {
        // Create a PlaceOfInterest object
        PlaceOfInterest place = new PlaceOfInterest();

        // Create another object of a different class
        Object obj = new Object();

        // Verify that canEqual returns true when passed another PlaceOfInterest object
        assertTrue(place.canEqual(new PlaceOfInterest()));

        // Verify that canEqual returns false when passed an object of a different class
        assertFalse(place.canEqual(obj));
    }
}
