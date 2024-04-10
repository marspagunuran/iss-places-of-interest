package org.mars.issplacesofinterest.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ISSPositionTest {
    @Test
    void testEqualsAndHashCode() {
        // Create ISSPosition objects with the same latitude and longitude
        ISSPosition position1 = new ISSPosition();
        ISSPosition position2 = new ISSPosition();

        // Verify that the objects are equal
        assertEquals(position1, position2);
        assertEquals(position2, position1);

        // Verify that the hash codes are equal
        assertEquals(position1.hashCode(), position2.hashCode());

    }

    @Test
    void testCanEqual() {
        // Create ISSPosition objects
        ISSPosition position = new ISSPosition();

        // Verify that canEqual returns true when passed another ISSPosition object
        assertTrue(position.canEqual(new ISSPosition()));

        // Verify that canEqual returns false when passed an object of a different class
        assertFalse(position.canEqual(new Object()));
    }

    @Test
    void testGettersAndSetters() {
        // Create an ISSPosition object
        ISSPosition position = new ISSPosition();

        // Set the latitude and longitude
        position.setLatitude(15.0);
        position.setLongitude(25.0);

        // Verify the getters
        assertEquals(15.0, position.getLatitude());
        assertEquals(25.0, position.getLongitude());
    }

    @Test
    void testToString() {
        // Create an ISSPosition object
        ISSPosition position = new ISSPosition();

        // Verify the toString method
        assertEquals("ISSPosition(latitude=0.0, longitude=0.0)", position.toString());
    }

}
