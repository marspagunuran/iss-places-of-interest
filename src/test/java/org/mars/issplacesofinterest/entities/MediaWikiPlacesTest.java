package org.mars.issplacesofinterest.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MediaWikiPlacesTest {
    @Test
    void testGettersAndSetters() {
        // Create a PlaceOfInterest object
        MediaWikiPlaces place = new MediaWikiPlaces();

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
        MediaWikiPlaces place = new MediaWikiPlaces();
        place.setTitle("Example Place");
        place.setLat(37.123);
        place.setLon(-122.456);
        place.setCountry("Country");

        // Verify the toString method
        assertEquals("MediaWikiPlaces(title=Example Place, lat=37.123, lon=-122.456, country=Country)", place.toString());
    }

    @Test
    void testHashCode() {
        // Create two PlaceOfInterest objects with the same attribute values
        MediaWikiPlaces place1 = new MediaWikiPlaces();
        place1.setTitle("Example Place");
        place1.setLat(37.123);
        place1.setLon(-122.456);
        place1.setCountry("Country");

        MediaWikiPlaces place2 = new MediaWikiPlaces();
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
        MediaWikiPlaces place1 = new MediaWikiPlaces();
        place1.setTitle("Example Place");
        place1.setLat(37.123);
        place1.setLon(-122.456);
        place1.setCountry("Country");

        MediaWikiPlaces place2 = new MediaWikiPlaces();
        place2.setTitle("Example Place");
        place2.setLat(37.123);
        place2.setLon(-122.456);
        place2.setCountry("Country");

        // Verify that objects with the same attribute values are equal
        assertEquals(place1, place2);

        // Create two PlaceOfInterest objects with different attribute values
        MediaWikiPlaces place3 = new MediaWikiPlaces();
        place3.setTitle("Different Place");
        place3.setLat(37.456);
        place3.setLon(-122.789);
        place3.setCountry("Other Country");

        // Verify that objects with different attribute values are not equal
        assertNotEquals(place1, place3);
    }

    @Test
    void testCanEqual() {
        // Create a PlaceOfInterest object
        MediaWikiPlaces place = new MediaWikiPlaces();

        // Create another object of a different class
        Object obj = new Object();

        // Verify that canEqual returns true when passed another PlaceOfInterest object
        assertTrue(place.canEqual(new MediaWikiPlaces()));

        // Verify that canEqual returns false when passed an object of a different class
        assertFalse(place.canEqual(obj));
    }
}
