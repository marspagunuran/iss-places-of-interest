package org.mars.issplacesofinterest.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ISSLocationDTOTest {
    @Test
    void testGettersAndSetters() {
        // Create a PlaceOfInterest object
        ISSLocationDTO issLocationDTO = new ISSLocationDTO(49.00, -122.456);

        // Verify values using getter methods
        assertEquals(49.00, issLocationDTO.getLatitude());
        assertEquals(-122.456, issLocationDTO.getLongitude());

    }

    @Test
    void testToString() {
        // Create a PlaceOfInterest object
        ISSLocationDTO issLocationDTO = new ISSLocationDTO(49.0, -122.456);


        // Verify the toString method
        assertEquals("ISSLocationDTO(latitude=49.0, longitude=-122.456)", issLocationDTO.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        // Create two ISSLocationDTO objects with the same attribute values
        ISSLocationDTO location1 = new ISSLocationDTO(37.123, -122.456);
        ISSLocationDTO location2 = new ISSLocationDTO(37.123, -122.456);

        // Verify that the objects are equal
        assertEquals(location1, location2);
        assertEquals(location2, location1);

        // Verify that the hash codes are equal
        assertEquals(location1.hashCode(), location2.hashCode());

        // Create two ISSLocationDTO objects with different attribute values
        ISSLocationDTO location3 = new ISSLocationDTO(37.456, -122.789);
        ISSLocationDTO location4 = new ISSLocationDTO(37.789, -122.456);

        // Verify that the objects are not equal
        assertNotEquals(location1, location3);
        assertNotEquals(location1, location4);
        assertNotEquals(location3, location4);

        // Verify that the hash codes are not equal
        assertNotEquals(location1.hashCode(), location3.hashCode());
        assertNotEquals(location1.hashCode(), location4.hashCode());
        assertNotEquals(location3.hashCode(), location4.hashCode());
    }

    @Test
    void testCanEqual() {
        // Create an ISSLocationDTO object
        ISSLocationDTO location = new ISSLocationDTO(37.123, -122.456);

        // Create another object of a different class
        Object obj = new Object();

        // Verify that canEqual returns true when passed another ISSLocationDTO object
        assertTrue(location.canEqual(new ISSLocationDTO(37.123, -122.456)));

        // Verify that canEqual returns false when passed an object of a different class
        assertFalse(location.canEqual(obj));
    }
}
