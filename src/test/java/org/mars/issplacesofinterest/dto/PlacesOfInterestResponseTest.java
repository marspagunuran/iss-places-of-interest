package org.mars.issplacesofinterest.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class PlacesOfInterestResponseTest {
    @Test
    void testEqualsAndHashCode() {
        // Create Query objects
        Query query1 = new Query();
        Query query2 = new Query();

        // Create PlacesOfInterestResponse objects with the same Query objects
        PlacesOfInterestResponse response1 = new PlacesOfInterestResponse();
        PlacesOfInterestResponse response2 = new PlacesOfInterestResponse();

        // Verify that the objects are equal
        assertTrue(response1.equals(response2));
        assertTrue(response2.equals(response1));

        // Verify that the hash codes are equal
        assertEquals(response1.hashCode(), response2.hashCode());

        // Create a PlacesOfInterestResponse object with a different Query object
        PlacesOfInterestResponse response3 = new PlacesOfInterestResponse();

    }

    @Test
    void testCanEqual() {
        // Create a PlacesOfInterestResponse object
        PlacesOfInterestResponse response = new PlacesOfInterestResponse();

        // Create another object of a different class
        Object obj = new Object();

        // Verify that canEqual returns true when passed another PlacesOfInterestResponse object
        assertTrue(response.canEqual(new PlacesOfInterestResponse()));

        // Verify that canEqual returns false when passed an object of a different class
        assertFalse(response.canEqual(obj));
    }

    @Test
    void testGettersAndSetters() {
        // Create a Query object
        Query query = new Query();

        // Create a PlacesOfInterestResponse object
        PlacesOfInterestResponse response = new PlacesOfInterestResponse();

        // Set the Query object
        response.setQuery(query);

        // Verify the getter
        assertEquals(query, response.getQuery());
    }

    @Test
    void testToString() {
        // Create a Query object
        Query query = new Query();

        // Create a PlacesOfInterestResponse object
        PlacesOfInterestResponse response = new PlacesOfInterestResponse();

        // Verify the toString method
        assertEquals("PlacesOfInterestResponse(query=null)", response.toString());
    }
}
