package org.mars.issplacesofinterest.entities;

import lombok.Data;

@Data
public class PlacesOfInterest {

    private String title;
    private double latitude;
    private double longitude;
    private String country;

}