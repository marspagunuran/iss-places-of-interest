package org.mars.issplacesofinterest.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;



public class MediaWikiResponseTest {
    @Test
    void testEqualsAndHashCode() {
        // Create PlacesOfInterestResponse objects with the same Query objects
        MediaWikiResponse response1 = new MediaWikiResponse();
        MediaWikiResponse response2 = new MediaWikiResponse();

        // Verify that the objects are equal
        assertEquals(response1, response2);
        assertEquals(response2, response1);

        // Verify that the hash codes are equal
        assertEquals(response1.hashCode(), response2.hashCode());


    }

    @Test
    void testCanEqual() {
        // Create a PlacesOfInterestResponse object
        MediaWikiResponse response = new MediaWikiResponse();

        // Create another object of a different class
        Object obj = new Object();

        // Verify that canEqual returns true when passed another PlacesOfInterestResponse object
        assertTrue(response.canEqual(new MediaWikiResponse()));

        // Verify that canEqual returns false when passed an object of a different class
        assertFalse(response.canEqual(obj));
    }

    @Test
    void testGettersAndSetters() {
        // Create a Query object
        Query query = new Query();

        // Create a PlacesOfInterestResponse object
        MediaWikiResponse response = new MediaWikiResponse();

        // Set the Query object
        response.setQuery(query);

        // Verify the getter
        assertEquals(query, response.getQuery());
    }

    @Test
    void testToString() {
        // Create a MediaWikiResponse object
        MediaWikiResponse response = new MediaWikiResponse();

        // Verify the toString method
        assertEquals("MediaWikiResponse(query=null)", response.toString());
    }
}
