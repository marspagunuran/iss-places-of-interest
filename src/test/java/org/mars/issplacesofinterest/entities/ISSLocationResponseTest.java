package org.mars.issplacesofinterest.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class ISSLocationResponseTest {
    @Test
    void testEqualsAndHashCode() {
        // Create ISSPosition objects
        ISSPosition position1 = new ISSPosition();
        ISSPosition position2 = new ISSPosition();

        // Verify that the objects are equal
        assertEquals(position1, position2);
        assertEquals(position2, position1);

        // Create ISSLocationResponse objects with the same ISSPosition objects
        ISSLocationResponse response1 = new ISSLocationResponse();
        ISSLocationResponse response2 = new ISSLocationResponse();

        // Verify that the objects are equal
        assertEquals(response1, response2);
        assertEquals(response2, response1);

        // Verify that the hash codes are equal
        assertEquals(response1.hashCode(), response2.hashCode());



    }

    @Test
    void testCanEqual() {
        // Create an ISSLocationResponse object
        ISSLocationResponse response = new ISSLocationResponse();

        // Create another object of a different class
        Object obj = new Object();

        // Verify that canEqual returns true when passed another ISSLocationResponse object
        assertTrue(response.canEqual(new ISSLocationResponse()));

        // Verify that canEqual returns false when passed an object of a different class
        assertFalse(response.canEqual(obj));
    }
}
