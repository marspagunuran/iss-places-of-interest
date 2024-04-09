package org.mars.issplacesofinterest.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class QueryTest {
    @Test
    void testEqualsAndHashCode() {
        // Create Query objects with the same lists of PlaceOfInterest objects
        Query query1 = new Query();
        Query query2 = new Query();

        // Verify that the objects are equal
        assertTrue(query1.equals(query2));
        assertTrue(query2.equals(query1));

        // Verify that the hash codes are equal
        assertEquals(query1.hashCode(), query2.hashCode());



    }

    @Test
    void testCanEqual() {
        // Create a Query object
        Query query = new Query();

        // Create another object of a different class
        Object obj = new Object();

        // Verify that canEqual returns true when passed another Query object
        assertTrue(query.canEqual(new Query()));

        // Verify that canEqual returns false when passed an object of a different class
        assertFalse(query.canEqual(obj));
    }

    @Test
    void testGettersAndSetters() {
        // Create a list of PlaceOfInterest objects
        List<PlaceOfInterest> geosearch = new ArrayList<>();

        // Create a Query object
        Query query = new Query();

        // Set the list of PlaceOfInterest objects
        query.setGeosearch(geosearch);

        // Verify the getter
        assertEquals(geosearch, query.getGeosearch());
    }

    @Test
    void testToString() {
        // Create a Query object
        Query query = new Query();

        // Verify the toString method
        assertEquals("Query(geosearch=null)", query.toString());
    }
}
